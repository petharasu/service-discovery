package com.springservicediscovery.myrestapp.bean;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import com.springservicediscovery.myrestapp.dto.OpenAiProperties;

import jakarta.ws.rs.core.HttpHeaders;

@Configuration
@EnableConfigurationProperties(OpenAiProperties.class)
public class OpenAiConfig {
	@Bean
    public RestClient openAiRestClient(
            OpenAiProperties properties) {
		IO.println("bear token "+properties.getApiKey() +"url  "+properties.getBaseUrl());
        return RestClient.builder()
                .baseUrl(properties.getBaseUrl())
                .defaultHeader(
                        HttpHeaders.AUTHORIZATION,
                        "Bearer " + properties.getApiKey()
                )
                .defaultHeader(
                        HttpHeaders.CONTENT_TYPE,
                        MediaType.APPLICATION_JSON_VALUE
                )
                .build();
    }
}
