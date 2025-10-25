package com.workflex.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Workflex API")
                        .version("1.0")
                        .description("Backend API for the coding challenge")
                        .contact(new Contact()
                                .name("Mostafa Tavassoli")
                                .email("mostafa@example.com")));
    }
}
