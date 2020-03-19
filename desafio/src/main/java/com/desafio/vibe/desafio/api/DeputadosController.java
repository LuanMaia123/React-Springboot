package com.desafio.vibe.desafio.api;

import com.desafio.vibe.desafio.api.recursos.*;
import com.desafio.vibe.desafio.dominio.Visualizacoes;
import com.desafio.vibe.desafio.dominio.dados.RepositorioVisualizacoes;
import com.desafio.vibe.desafio.dominio.servicos.ServicoDeputados;
import com.desafio.vibe.desafio.util.Response;
import com.desafio.vibe.desafio.util.VariaveisAplicacao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    private Response<RecursoDadosDespesa> responseDespesa;

    private Response<RecursoDetalhe> responseDetalhe;

    private RepositorioVisualizacoes repositorioVisualizacoes;

    private VariaveisAplicacao variaveisAplicacao;

    @GetMapping("/lista")
    public ResponseEntity<List<RecursoDeputado>> getDeputados(@RequestParam(value = "paginaAtual") Integer paginaAtual, @RequestParam(value = "itensPorPagina") Integer itensPorPagina) {
        return new ResponseEntity<>(servico.obterTodosDeputados(paginaAtual, itensPorPagina), HttpStatus.OK);
    }

    @GetMapping("/detalhe")
    public ResponseEntity<RecursoDeputadoDetalhe> getDetalheDeputado(@RequestParam(value = "deputadoId") String deputadoId) {

        RecursoDeputadoDetalhe recursoDeputadoDetalhe = null;
        UriComponentsBuilder detalheBuilder = UriComponentsBuilder.fromHttpUrl(variaveisAplicacao.getUrlDetalheDeputado())
                .queryParam("id", deputadoId);
        RecursoDetalhe recursoDetalhe = responseDetalhe.requestParamsBuilder(detalheBuilder, RecursoDetalhe.class);

        if (recursoDetalhe != null) {
            recursoDeputadoDetalhe = recursoDetalhe.getRecursoDeputadoDetalhe();

            Map<String, String> urlParams = new HashMap<>();
            urlParams.put("id", deputadoId);

            UriComponentsBuilder despesasbuilder = UriComponentsBuilder.fromHttpUrl(variaveisAplicacao.getUrlDespesas())
                    .queryParam("ano", getAnoAtual())
                    .queryParam("mes", getMesAtual())
                    .queryParam("mes", getMesAnterior())
                    .queryParam("ordem", "ASC")
                    .queryParam("ordenarPor", "dataDocumento");

            RecursoDadosDespesa recursoDespesa = responseDespesa.requestParamsBuilder(urlParams, despesasbuilder, RecursoDadosDespesa.class);
            if (recursoDespesa != null && !recursoDespesa.getDespesas().isEmpty()) {
                recursoDeputadoDetalhe.setDespesas(agruparDespesas(recursoDespesa));
            }
        }
        Visualizacoes visualizacoesPorParlamentar = repositorioVisualizacoes.getVisualizacoesPorParlamentar(Long.valueOf(deputadoId));
        if (visualizacoesPorParlamentar != null) {
            visualizacoesPorParlamentar.setVisualizacoes(visualizacoesPorParlamentar.getVisualizacoes() + 1);
            repositorioVisualizacoes.save(visualizacoesPorParlamentar);
        } else {
            visualizacoesPorParlamentar = new Visualizacoes();
            visualizacoesPorParlamentar.setIdParlamentar(Long.valueOf(deputadoId));
            visualizacoesPorParlamentar.setVisualizacoes(1);
            repositorioVisualizacoes.save(visualizacoesPorParlamentar);
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
