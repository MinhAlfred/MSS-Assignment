package com.minhvq.orchidservice.configs;

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

    @Value("${server.port:8082}")
    private String serverPort;

    @Bean
    public OpenAPI orchidServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Orchid Service API")
                        .version("1.0.0")
                        .description("Orchid Service for MSS301 Microservices Architecture")
                        .contact(new Contact()
                                .name("Orchid Service Team")
                                .email("orchid@mss301.com")))
                .servers(List.of(
                        new Server().url("http://localhost:" + serverPort).description("Orchid Service Server"),
                        new Server().url("http://localhost:8080/api/orchids").description("Via API Gateway")
                ));
    }
}
