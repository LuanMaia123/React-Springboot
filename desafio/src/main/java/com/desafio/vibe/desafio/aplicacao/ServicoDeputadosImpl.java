package com.desafio.vibe.desafio.aplicacao;

import com.desafio.vibe.desafio.api.recursos.RecursoDeputado;
import com.desafio.vibe.desafio.api.recursos.RecursosDeputados;
import com.desafio.vibe.desafio.servicos.ServicoDeputados;
import com.desafio.vibe.desafio.util.VariaveisAplicacao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ServicoDeputadosImpl implements ServicoDeputados {

    private VariaveisAplicacao variaveisAplicacao;

    @Override
    public List<RecursoDeputado> obterTodosDeputados(Integer paginaAtual, Integer itensPorPagina) {
        RestTemplate restTemplate = new RestTemplate();
        List<RecursoDeputado> deputadoList = new ArrayList<>();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(variaveisAplicacao.getUrlApiDeputados())
                .queryParam("pagina", paginaAtual)
                .queryParam("itens", itensPorPagina)
                .queryParam("ordem", "ASC");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        ResponseEntity<RecursosDeputados> rateResponse = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, RecursosDeputados.class);
        RecursosDeputados body = rateResponse.getBody();
        if (body != null) {
            deputadoList = body.getDados();
        }
        return deputadoList;
    }
}
