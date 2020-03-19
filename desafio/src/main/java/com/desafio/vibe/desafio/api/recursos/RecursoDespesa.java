package com.desafio.vibe.desafio.api.recursos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecursoDespesa implements Serializable {

    @JsonProperty("mes")
    public Integer mes;
    @JsonProperty("tipoDespesa")
    public String tipoDespesa;
    @JsonProperty("tipoDocumento")
    public String tipoDocumento;
    @JsonProperty("dataDocumento")
    public String dataDocumento;
    @JsonProperty("valorLiquido")
    public Double valorLiquido;

}
