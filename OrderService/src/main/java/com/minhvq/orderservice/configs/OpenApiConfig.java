package com.minhvq.orderservice.configs;

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

    @Value("${server.port:8083}")
    private String serverPort;

    @Bean
    public OpenAPI orderServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order Service API")
                        .version("1.0.0")
                        .description("Order Service for MSS301 Microservices Architecture")
                        .contact(new Contact()
                                .name("Order Service Team")
                                .email("order@mss301.com")))
                .servers(List.of(
                        new Server().url("http://localhost:" + serverPort).description("Order Service Server"),
                        new Server().url("http://localhost:8080/api/orders").description("Via API Gateway")
                ));
    }
}
