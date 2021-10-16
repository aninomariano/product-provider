package com.backend.productorderwrapper.binding;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Binding {

    @Bean
    public RestTemplate restTemplate(final RestTemplateBuilder builder) {
        return builder.errorHandler(new RestTemplateErrorHandler()).build();
    }
}
