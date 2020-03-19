package com.desafio.vibe.desafio.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "visualizacoes")
@Getter
@Setter
public class Visualizacoes implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Id do parlamentar obrigatório")
    @Column(unique=true)
    private Long idParlamentar;

    @NotNull(message = "Quantidade de visualizações obrigatório")
    private Integer visualizacoes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visualizacoes that = (Visualizacoes) o;
        return id.equals(that.id) &&
                idParlamentar.equals(that.idParlamentar) &&
                visualizacoes.equals(that.visualizacoes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idParlamentar, visualizacoes);
    }
}
