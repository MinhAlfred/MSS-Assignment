package com.minhvq.accountservice.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Value("${server.port:8081}")
    private String serverPort;

    @Bean
    public OpenAPI accountServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Account Service API")
                        .version("1.0.0")
                        .description("Account Service for MSS301 Microservices Architecture")
                        .contact(new Contact()
                                .name("Account Service Team")
                                .email("account@mss301.com")))
                .servers(List.of(
                        new Server().url("http://localhost:" + serverPort).description("Account Service Server"),
                        new Server().url("http://localhost:8080/api/accounts").description("Via API Gateway")
                ));
    }
}
