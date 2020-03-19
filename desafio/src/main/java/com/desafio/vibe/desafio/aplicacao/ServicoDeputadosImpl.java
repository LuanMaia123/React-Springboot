package com.desafio.vibe.desafio.aplicacao;

import com.desafio.vibe.desafio.api.recursos.*;
import com.desafio.vibe.desafio.dominio.Visualizacoes;
import com.desafio.vibe.desafio.dominio.dados.RepositorioVisualizacoes;
import com.desafio.vibe.desafio.dominio.servicos.ServicoDeputados;
import com.desafio.vibe.desafio.util.Response;
import com.desafio.vibe.desafio.util.VariaveisAplicacao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ServicoDeputadosImpl implements ServicoDeputados {


    private RepositorioVisualizacoes repositorioVisualizacoes;

    private Response<RecursosDeputados> response;

    private Response<RecursoDadosDespesa> responseDespesa;

    private Response<RecursoDetalhe> responseDetalhe;

    private VariaveisAplicacao variaveisAplicacao;

    private final String ORDENACAO_ASC = "ASC";
    private final String ORDENACAO_DESC = "DESC";

    @Override
    public List<RecursoDeputado> obterTodosDeputados(Integer paginaAtual, Integer itensPorPagina) {

        List<RecursoDeputado> deputadoList = new ArrayList<>();
        UriComponentsBuilder builder = getUriComponentsBuilderDeputados(paginaAtual, itensPorPagina);

        RecursosDeputados recursosDeputados = response.requestParamsBuilder(builder, RecursosDeputados.class);
        if (recursosDeputados != null) {
            deputadoList = recursosDeputados.getDados();
            populaVisualizacoes(deputadoList);
        }

        return deputadoList;
    }

    private UriComponentsBuilder getUriComponentsBuilderDeputados(Integer paginaAtual, Integer itensPorPagina) {
        return UriComponentsBuilder.fromHttpUrl("https://dadosabertos.camara.leg.br/api/v2/deputados")
                .queryParam("pagina", paginaAtual)
                .queryParam("itens", itensPorPagina)
                .queryParam("ordem", ORDENACAO_ASC);
    }

    private void populaVisualizacoes(List<RecursoDeputado> deputadoList) {
        deputadoList.forEach(d -> {
            Visualizacoes visualizacoesPorParlamentar = repositorioVisualizacoes.getVisualizacoesPorParlamentar(Long.valueOf(d.getId()));
            d.setVisualizacoes(visualizacoesPorParlamentar != null ? visualizacoesPorParlamentar.getVisualizacoes() : 0);
        });
    }

    public RecursoDeputadoDetalhe obterDetalhesDeputado(String deputadoId, boolean detalhada) {
        RecursoDeputadoDetalhe recursoDeputadoDetalhe = null;
        UriComponentsBuilder detalheBuilder = UriComponentsBuilder.fromHttpUrl(variaveisAplicacao.getUrlDetalheDeputado());
        Map<String, String> urlParamsd = getUrlParamsMap(deputadoId);
        RecursoDetalhe recursoDetalhe = responseDetalhe.requestParamsBuilder(urlParamsd, detalheBuilder, RecursoDetalhe.class);

        if (recursoDetalhe != null) {
            recursoDeputadoDetalhe = recursoDetalhe.getRecursoDeputadoDetalhe();

            RecursoDadosDespesa recursoDespesa = responseDespesa.requestParamsBuilder(urlParamsd, getPathParamsBuilder(), RecursoDadosDespesa.class);
            if (recursoDespesa != null && !recursoDespesa.getDespesas().isEmpty()) {
                if (!detalhada) {
                    recursoDeputadoDetalhe.setDespesas(agruparDespesas(recursoDespesa));
                } else {
                    recursoDeputadoDetalhe.setDespesas(recursoDespesa.getDespesas());
                }

            }
        }
        verificaInteraVisualizacao(deputadoId);
        return recursoDeputadoDetalhe;
    }

    private UriComponentsBuilder getPathParamsBuilder() {
        return UriComponentsBuilder.fromHttpUrl(variaveisAplicacao.getUrlDespesas())
                .queryParam("ano", getAnoAtual())
                .queryParam("mes", getMesAtual())
                .queryParam("mes", getMesAnterior())
                .queryParam("ordem", ORDENACAO_DESC)
                .queryParam("ordenarPor", "dataDocumento");
    }

    private Map<String, String> getUrlParamsMap(String deputadoId) {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", deputadoId);
        return urlParams;
    }

    private void verificaInteraVisualizacao(String deputadoId) {
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
