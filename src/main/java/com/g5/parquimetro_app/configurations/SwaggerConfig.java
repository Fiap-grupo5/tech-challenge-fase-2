package com.g5.parquimetro_app.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .openapi("3.0.1")
                .info(new Info()
                        .title("Parquimetro API")
                        .version("1.0")
                        .description("Documentação da API do Parquimetro")
                        .contact(new Contact()
                                .name("Suporte")
                                .email("suporte@parquimetro.com")));
    }
}