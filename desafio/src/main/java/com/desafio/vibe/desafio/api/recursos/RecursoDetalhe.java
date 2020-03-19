package com.desafio.vibe.desafio.api.recursos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecursoDetalhe implements Serializable {

    @JsonProperty("dados")
    private RecursoDeputadoDetalhe recursoDeputadoDetalhe;
}