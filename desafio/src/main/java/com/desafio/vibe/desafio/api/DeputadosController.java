package com.desafio.vibe.desafio.api;

import com.desafio.vibe.desafio.api.recursos.*;
import com.desafio.vibe.desafio.servicos.ServicoDeputados;
import com.desafio.vibe.desafio.util.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("deputados")
public class DeputadosController {

    private ServicoDeputados servico;

    private Response<RecursoDadosDespesa> response;

    @GetMapping("/lista")
    public ResponseEntity<List<RecursoDeputado>> getDeputados(@RequestParam(value = "paginaAtual") Integer paginaAtual, @RequestParam(value = "itensPorPagina") Integer itensPorPagina) {
        return new ResponseEntity<>(servico.obterTodosDeputados(paginaAtual, itensPorPagina), HttpStatus.OK);
    }

    @GetMapping("/detalhe")
    public ResponseEntity<RecursoDeputadoDetalhe> getDetalheDeputado(@RequestParam(value = "deputadoId") String deputadoId) {
        RestTemplate restTemplate = new RestTemplate();
        RecursoDeputadoDetalhe recursoDeputadoDetalhe = null;
        ResponseEntity<RecursoDetalhe> responseDetalhe = restTemplate.exchange("https://dadosabertos.camara.leg.br/api/v2/deputados/{id}", HttpMethod.GET, null, RecursoDetalhe.class, deputadoId);
        RecursoDetalhe body = responseDetalhe.getBody();
        if (body != null) {
            recursoDeputadoDetalhe = body.getRecursoDeputadoDetalhe();

            Map<String, String> urlParams = new HashMap<>();
            urlParams.put("id", deputadoId);

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dadosabertos.camara.leg.br/api/v2/deputados/{id}/despesas")
                    .queryParam("ano", getAnoAtual())
                    .queryParam("mes", getMesAtual())
                    .queryParam("mes", getMesAnterior())
                    .queryParam("ordem", "ASC")
                    .queryParam("ordenarPor", "dataDocumento");

            RecursoDadosDespesa recursoDespesa = response.requestParamsBuilder(urlParams, builder, RecursoDadosDespesa.class);
            if (recursoDespesa != null && !recursoDespesa.getDespesas().isEmpty()) {
                recursoDeputadoDetalhe.setDespesas(agruparDespesas(recursoDespesa));
            }
        }

        return new ResponseEntity<>(recursoDeputadoDetalhe, HttpStatus.OK);
    }

    private List<RecursoDespesa> agruparDespesas(RecursoDadosDespesa recursoDespesa) {
        TreeMap<RecursoDespesa, Integer> collect = recursoDespesa.getDespesas().stream().collect(Collectors.groupingBy(Function.identity(),
                () -> new TreeMap<>(Comparator.<RecursoDespesa, Integer>comparing(d -> d.mes)),
                Collectors.summingInt(d -> (int) Math.round(d.valorLiquido))));
        List<RecursoDespesa> recursos = new ArrayList<>();
        for (Map.Entry<RecursoDespesa, Integer> entry : collect.entrySet()) {
            RecursoDespesa recurso = new RecursoDespesa();
            recurso.setMes(entry.getKey().mes);
            recurso.setValorLiquido(Double.valueOf(entry.getValue()));
            recursos.add(recurso);
        }
        return recursos;
    }

    private int getMesAtual() {
        return LocalDate.now().getMonthValue();
    }

    private int getAnoAtual() {
        return LocalDate.now().getYear();
    }

    private int getMesAnterior() {
        return LocalDate.now().minusMonths(1).getMonthValue();
    }


}
