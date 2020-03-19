package com.desafio.vibe.desafio.dominio.servicos;

import com.desafio.vibe.desafio.api.recursos.RecursoDeputado;

import java.util.List;

public interface ServicoDeputados {

    List<RecursoDeputado> obterTodosDeputados(Integer paginaAtual, Integer itensPorPagina);
}
