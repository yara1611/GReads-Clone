package com.example.GReads_Clone;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient webClient(WebClient.Builder builder){
        return builder
                .baseUrl("https://www.googleapis.com/books/v1/volumes")
                .build();
    }
}
