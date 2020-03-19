package com.desafio.vibe.desafio.api.recursos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecursoDeputado implements Serializable {

    @JsonProperty("email")
    private String email;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("idLegislatura")
    private Integer idLegislatura;
    @JsonProperty("nome")
    private String nome;
    @JsonProperty("siglaPartido")
    private String siglaPartido;
    @JsonProperty("siglaUf")
    private String siglaUf;
    @JsonProperty("uri")
    private String uri;
    @JsonProperty("uriPartido")
    private String uriPartido;
    @JsonProperty("urlFoto")
    private String urlFoto;
    @JsonProperty("visualizacoes")
    private Integer visualizacoes;

}
