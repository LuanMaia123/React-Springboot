package com.desafio.vibe.desafio.util;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class VariaveisAplicacao {

    private Environment env;

    public String getConfigValue(String configKey) {
        return env.getProperty(configKey);
    }

    public String getUrlDeputados() {
        return getConfigValue("url.deputados");
    }

    public String getUrlDetalheDeputado() {
        return getConfigValue("url.deputado.detalhe");
    }

    public String getUrlDespesas() {
        return getConfigValue("url.despesas");
    }
}