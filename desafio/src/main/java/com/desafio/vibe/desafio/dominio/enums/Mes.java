package com.desafio.vibe.desafio.dominio.enums;

public enum Mes {

    JANEIRO(1, "Janeiro"),
    FEVEREIRO(2, "Fevereiro"),
    MARCO(3, "Março"),
    ABRIL(4, "Abril"),
    MAIO(5, "Maio"),
    JUNHO(6, "Junho"),
    JULHO(7, "Julho"),
    AGOSTO(8, "Agosto"),
    SETEMBRO(9, "Setembro"),
    OUTUBRO(10, "Outubro"),
    NOVEMBRO(11, "Novembro"),
    DEZEMBRO(12, "Dezembro");


    private Integer codigo;
    private String descricao;

    Mes(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public static Mes recuperarStatusPeloCodigo(Integer codigo) {
        for (Mes m : Mes.values()) {
            if (m.codigo.equals(codigo)) {
                return m;
            }
        }
        return null;
    }

}