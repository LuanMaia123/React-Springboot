package com.desafio.vibe.desafio.api.recursos;

        import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
        import com.fasterxml.jackson.annotation.JsonProperty;
        import lombok.Data;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecursoDeputadoDetalhe implements Serializable {

    @JsonProperty("dataNascimento")
    private String dataNascimento;
    @JsonProperty("nomeCivil")
    private String nomeCivil;
    @JsonProperty("sexo")
    private String sexo;
    @JsonProperty("ultimoStatus")
    private RecursoDadosPartido dadosPartido;
    @JsonProperty("despesas")
    private List<RecursoDespesa> despesas = new ArrayList<>();

}
