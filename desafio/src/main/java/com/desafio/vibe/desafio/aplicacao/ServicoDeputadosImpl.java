package com.desafio.vibe.desafio.aplicacao;

import com.desafio.vibe.desafio.api.recursos.RecursoDeputado;
import com.desafio.vibe.desafio.api.recursos.RecursosDeputados;
import com.desafio.vibe.desafio.dominio.Visualizacoes;
import com.desafio.vibe.desafio.dominio.dados.RepositorioVisualizacoes;
import com.desafio.vibe.desafio.dominio.servicos.ServicoDeputados;
import com.desafio.vibe.desafio.util.Response;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ServicoDeputadosImpl implements ServicoDeputados {


    private RepositorioVisualizacoes repositorioVisualizacoes;

    private Response<RecursosDeputados> response;

    private final String ORDENACAO_ASC = "ASC";

    @Override
    public List<RecursoDeputado> obterTodosDeputados(Integer paginaAtual, Integer itensPorPagina) {

        List<RecursoDeputado> deputadoList = new ArrayList<>();

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://dadosabertos.camara.leg.br/api/v2/deputados")
                .queryParam("pagina", paginaAtual)
                .queryParam("itens", itensPorPagina)
                .queryParam("ordem", ORDENACAO_ASC);

        RecursosDeputados recursosDeputados = response.requestParamsBuilder(builder, RecursosDeputados.class);
        if (recursosDeputados != null) {
            deputadoList = recursosDeputados.getDados();
            populaVisualizacoes(deputadoList);
        }

        return deputadoList;
    }

    private void populaVisualizacoes(List<RecursoDeputado> deputadoList) {
        deputadoList.forEach(d -> {
            Visualizacoes visualizacoesPorParlamentar = repositorioVisualizacoes.getVisualizacoesPorParlamentar(Long.valueOf(d.getId()));
            d.setVisualizacoes(visualizacoesPorParlamentar != null ? visualizacoesPorParlamentar.getVisualizacoes() : 0);
        });
    }
}
