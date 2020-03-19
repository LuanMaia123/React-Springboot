package com.desafio.vibe.desafio.api.recursos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecursoDadosPartido implements Serializable {

    @JsonProperty("siglaPartido")
    private String siglaPartido;
    @JsonProperty("urlFoto")
    private String urlFoto;
}