package com.desafio.vibe.desafio.util;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
import java.util.logging.Logger;

@Component
public class Response<T> {

    private final java.util.logging.Logger LOG = Logger.getLogger(this.getClass().getName());


    public T requestParamsBuilder(Map<String, String> urlParams, UriComponentsBuilder builder, Class<T> objeto) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

        try {
            ResponseEntity<T> exchange = restTemplate.exchange(builder.buildAndExpand(urlParams).toUriString(), HttpMethod.GET, null, objeto);
            return exchange.getBody();
        } catch (Exception e) {
            LOG.severe(e.getMessage());
        }
        return null;
    }

    public T requestParamsBuilder(UriComponentsBuilder builder, Class<T> objeto) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate
                .getMessageConverters()
                .add(new MappingJackson2HttpMessageConverter());

        try {
            ResponseEntity<T> exchange = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, null, objeto);
            return exchange.getBody();
        } catch (Exception e) {
            LOG.severe(e.getMessage());
        }
        return null;
    }
}
