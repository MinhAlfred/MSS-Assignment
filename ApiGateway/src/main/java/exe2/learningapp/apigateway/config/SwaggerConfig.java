//package exe2.learningapp.apigateway.config;
//
//import io.swagger.v3.oas.models.OpenAPI;
//import io.swagger.v3.oas.models.info.Info;
//import io.swagger.v3.oas.models.info.Contact;
//import io.swagger.v3.oas.models.servers.Server;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import org.springframework.web.reactive.function.server.RequestPredicates;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//import reactor.core.publisher.Mono;
//import org.springframework.http.MediaType;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.context.annotation.Primary;
//
//import java.util.List;
//
//@Configuration
//public class SwaggerConfig {
//
//    @Value("${server.port:8888}")
//    private String serverPort;
//
//    private final WebClient webClient = WebClient.builder().build();
//
//    @Bean
//    @Primary
//    public OpenAPI customOpenAPI() {
//        return new OpenAPI()
//                .info(new Info()
//                        .title("API Gateway Documentation")
//                        .version("1.0.0")
//                        .description("API Gateway for MSS301 Microservices - Aggregated Documentation")
//                        .contact(new Contact()
//                                .name("MSS301 Team")
//                                .email("support@mss301.com")))
//                .servers(List.of(
//                        new Server().url("http://localhost:" + serverPort).description("API Gateway Server")
//                ));
//    }
//
//    @Bean
//    public RouterFunction<ServerResponse> swaggerRoutes() {
//        return RouterFunctions
//                // Main API docs endpoint that aggregates all services
//                .route(RequestPredicates.GET("/v3/api-docs").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
//                        request -> ServerResponse.ok()
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .body(Mono.just(getAggregatedApiDocs()), String.class))
//
//                // Individual service API docs
//                .andRoute(RequestPredicates.GET("/v3/api-docs/account-service"),
//                        request -> fetchServiceApiDocs("http://localhost:8081/v3/api-docs"))
//                .andRoute(RequestPredicates.GET("/v3/api-docs/orchid-service"),
//                        request -> fetchServiceApiDocs("http://localhost:8082/v3/api-docs"))
//                .andRoute(RequestPredicates.GET("/v3/api-docs/order-service"),
//                        request -> fetchServiceApiDocs("http://localhost:8083/v3/api-docs"))
//
//                // Legacy routes for compatibility
//                .andRoute(RequestPredicates.GET("/api-docs").and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
//                        request -> ServerResponse.ok()
//                                .contentType(MediaType.APPLICATION_JSON)
//                                .body(Mono.just(getAggregatedApiDocs()), String.class))
//
//                // Swagger UI redirects
//                .andRoute(RequestPredicates.GET("/swagger-ui.html"),
//                        request -> ServerResponse.temporaryRedirect(java.net.URI.create("/swagger-ui/index.html")).build())
//                .andRoute(RequestPredicates.GET("/swagger-ui"),
//                        request -> ServerResponse.temporaryRedirect(java.net.URI.create("/swagger-ui/index.html")).build());
//    }
//
//    private Mono<ServerResponse> fetchServiceApiDocs(String serviceUrl) {
//        return webClient.get()
//                .uri(serviceUrl)
//                .retrieve()
//                .bodyToMono(String.class)
//                .flatMap(body -> ServerResponse.ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(Mono.just(body), String.class))
//                .onErrorResume(error -> ServerResponse.ok()
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(Mono.just("{\"error\": \"Service unavailable\"}"), String.class));
//    }
//
//    private String getAggregatedApiDocs() {
//        return """
//                {
//                  "openapi": "3.0.1",
//                  "info": {
//                    "title": "API Gateway Documentation",
//                    "description": "API Gateway for MSS301 Microservices - Aggregated Documentation",
//                    "version": "1.0.0",
//                    "contact": {
//                      "name": "MSS301 Team",
//                      "email": "support@mss301.com"
//                    }
//                  },
//                  "servers": [
//                    {
//                      "url": "http://localhost:8888",
//                      "description": "API Gateway Server"
//                    }
//                  ],
//                  "tags": [
//                    {
//                      "name": "Account Service",
//                      "description": "Account management operations"
//                    },
//                    {
//                      "name": "Order Service",
//                      "description": "Order management operations"
//                    },
//                    {
//                      "name": "Orchid Service",
//                      "description": "Orchid catalog operations"
//                    },
//                    {
//                      "name": "Gateway Management",
//                      "description": "API Gateway monitoring and management"
//                    }
//                  ],
//                  "paths": {
//                    "/api/accounts/{id}": {
//                      "get": {
//                        "tags": ["Account Service"],
//                        "summary": "Get account by ID",
//                        "description": "Retrieve account details by account ID",
//                        "parameters": [
//                          {
//                            "name": "id",
//                            "in": "path",
//                            "required": true,
//                            "schema": {
//                              "type": "string"
//                            }
//                          }
//                        ],
//                        "responses": {
//                          "200": {
//                            "description": "Account found",
//                            "content": {
//                              "application/json": {
//                                "schema": {
//                                  "$ref": "#/components/schemas/ApiResponse"
//                                }
//                              }
//                            }
//                          },
//                          "404": {
//                            "description": "Account not found"
//                          }
//                        }
//                      }
//                    },
//                    "/api/accounts": {
//                      "get": {
//                        "tags": ["Account Service"],
//                        "summary": "Get all accounts",
//                        "description": "Retrieve all accounts",
//                        "responses": {
//                          "200": {
//                            "description": "Accounts retrieved successfully",
//                            "content": {
//                              "application/json": {
//                                "schema": {
//                                  "$ref": "#/components/schemas/ApiResponse"
//                                }
//                              }
//                            }
//                          }
//                        }
//                      },
//                      "post": {
//                        "tags": ["Account Service"],
//                        "summary": "Create new account",
//                        "description": "Create a new account",
//                        "responses": {
//                          "201": {
//                            "description": "Account created successfully"
//                          }
//                        }
//                      }
//                    },
//                    "/api/orders/{id}": {
//                      "get": {
//                        "tags": ["Order Service"],
//                        "summary": "Get order by ID",
//                        "description": "Retrieve order details by order ID",
//                        "parameters": [
//                          {
//                            "name": "id",
//                            "in": "path",
//                            "required": true,
//                            "schema": {
//                              "type": "string"
//                            }
//                          }
//                        ],
//                        "responses": {
//                          "200": {
//                            "description": "Order found",
//                            "content": {
//                              "application/json": {
//                                "schema": {
//                                  "$ref": "#/components/schemas/ApiResponse"
//                                }
//                              }
//                            }
//                          },
//                          "404": {
//                            "description": "Order not found"
//                          }
//                        }
//                      }
//                    },
//                    "/api/orders": {
//                      "get": {
//                        "tags": ["Order Service"],
//                        "summary": "Get all orders",
//                        "description": "Retrieve all orders",
//                        "responses": {
//                          "200": {
//                            "description": "Orders retrieved successfully",
//                            "content": {
//                              "application/json": {
//                                "schema": {
//                                  "$ref": "#/components/schemas/ApiResponse"
//                                }
//                              }
//                            }
//                          }
//                        }
//                      },
//                      "post": {
//                        "tags": ["Order Service"],
//                        "summary": "Create new order",
//                        "description": "Create a new order",
//                        "responses": {
//                          "201": {
//                            "description": "Order created successfully"
//                          }
//                        }
//                      }
//                    },
//                    "/api/orchids/{id}": {
//                      "get": {
//                        "tags": ["Orchid Service"],
//                        "summary": "Get orchid by ID",
//                        "description": "Retrieve orchid details by orchid ID",
//                        "parameters": [
//                          {
//                            "name": "id",
//                            "in": "path",
//                            "required": true,
//                            "schema": {
//                              "type": "string"
//                            }
//                          }
//                        ],
//                        "responses": {
//                          "200": {
//                            "description": "Orchid found",
//                            "content": {
//                              "application/json": {
//                                "schema": {
//                                  "$ref": "#/components/schemas/ApiResponse"
//                                }
//                              }
//                            }
//                          },
//                          "404": {
//                            "description": "Orchid not found"
//                          }
//                        }
//                      }
//                    },
//                    "/api/orchids": {
//                      "get": {
//                        "tags": ["Orchid Service"],
//                        "summary": "Get all orchids",
//                        "description": "Retrieve all orchids",
//                        "responses": {
//                          "200": {
//                            "description": "Orchids retrieved successfully",
//                            "content": {
//                              "application/json": {
//                                "schema": {
//                                  "$ref": "#/components/schemas/ApiResponse"
//                                }
//                              }
//                            }
//                          }
//                        }
//                      },
//                      "post": {
//                        "tags": ["Orchid Service"],
//                        "summary": "Create new orchid",
//                        "description": "Create a new orchid",
//                        "responses": {
//                          "201": {
//                            "description": "Orchid created successfully"
//                          }
//                        }
//                      }
//                    },
//                    "/gateway/health": {
//                      "get": {
//                        "tags": ["Gateway Management"],
//                        "summary": "Gateway health check",
//                        "description": "Check if the API Gateway is running",
//                        "responses": {
//                          "200": {
//                            "description": "Gateway is healthy",
//                            "content": {
//                              "application/json": {
//                                "schema": {
//                                  "$ref": "#/components/schemas/ApiResponse"
//                                }
//                              }
//                            }
//                          }
//                        }
//                      }
//                    }
//                  },
//                  "components": {
//                    "schemas": {
//                      "ApiResponse": {
//                        "type": "object",
//                        "properties": {
//                          "success": {
//                            "type": "boolean",
//                            "description": "Indicates if the operation was successful"
//                          },
//                          "message": {
//                            "type": "string",
//                            "description": "Response message"
//                          },
//                          "data": {
//                            "type": "object",
//                            "description": "Response data"
//                          },
//                          "timestamp": {
//                            "type": "string",
//                            "format": "date-time",
//                            "description": "Response timestamp"
//                          }
//                        }
//                      }
//                    }
//                  }
//                }
//                """.replace("http://localhost:8080", "http://localhost:8888");
//    }
//}
