package com.desafio.vibe.desafio.dominio.dados;

import com.desafio.vibe.desafio.dominio.Visualizacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioVisualizacoes extends JpaRepository<Visualizacoes, Long> {

    @Query(" select v from Visualizacoes v where v.idParlamentar= :idParlamentar")
    Visualizacoes getVisualizacoesPorParlamentar(Long idParlamentar);
}
