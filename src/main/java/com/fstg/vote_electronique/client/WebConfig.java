package com.fstg.vote_electronique.client;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("*")   // autorise tout les contorolleur
                .allowedOrigins("http://localhost:4200/")  // autoriser n'import qu'elle client pour *
                .allowedMethods("*"); // alouer tout les method http
    }
}
