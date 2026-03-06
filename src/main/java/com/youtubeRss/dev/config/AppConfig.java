package com.youtubeRss.dev.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = "com.youtubeRss.dev")
@EnableScheduling
public class AppConfig {

    // 외부(유튜브, Discord)와 통신할 때 쓸 도구
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
