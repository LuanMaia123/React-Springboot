package com.desafio.vibe.desafio.dominio.servicos;

import com.desafio.vibe.desafio.api.recursos.RecursoDeputado;
import com.desafio.vibe.desafio.api.recursos.RecursoDeputadoDetalhe;
import com.desafio.vibe.desafio.api.recursos.RecursoDespesa;

import java.util.List;

public interface ServicoDeputados {

    List<RecursoDeputado> obterTodosDeputados(Integer paginaAtual, Integer itensPorPagina);

    RecursoDeputadoDetalhe obterDetalhesDeputado(String deputadoId, boolean detalhada);

    List<RecursoDespesa> obterDetalhesMes(String deputadoId, String mes);
}
