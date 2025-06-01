package com.example.SunriseSunset.configuration;

import com.example.SunriseSunset.cache.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SunriseSunsetConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Cache sunriseSunsetCache() {
        return new Cache();
    }
}