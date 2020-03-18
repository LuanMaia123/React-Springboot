package com.desafio.vibe.desafio.aplicacao;

import com.desafio.vibe.desafio.api.recursos.RecursoDeputado;
import com.desafio.vibe.desafio.api.recursos.RecursosDeputados;
import com.desafio.vibe.desafio.servicos.ServicoDeputados;
import com.desafio.vibe.desafio.util.VariaveisAplicacao;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ServicoDeputadosImpl implements ServicoDeputados {

    private VariaveisAplicacao variaveisAplicacao;

    @Override
    public List<RecursoDeputado> obterTodosDeputados() {
        RestTemplate restTemplate = new RestTemplate();
        List<RecursoDeputado> deputadoList = new ArrayList<>();
        ResponseEntity<RecursosDeputados> rateResponse =
                restTemplate.exchange(variaveisAplicacao.getUrlApiDeputados(),
                        HttpMethod.GET, null, RecursosDeputados.class);
        RecursosDeputados body = rateResponse.getBody();
        if (body != null) {
            deputadoList = body.getDados();
        }
        return deputadoList;
    }
}
