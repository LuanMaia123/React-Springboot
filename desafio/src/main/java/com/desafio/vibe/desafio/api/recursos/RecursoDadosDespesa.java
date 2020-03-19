package com.desafio.vibe.desafio.api.recursos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecursoDadosDespesa {
    @JsonProperty("dados")
    private List<RecursoDespesa> despesas;
}
