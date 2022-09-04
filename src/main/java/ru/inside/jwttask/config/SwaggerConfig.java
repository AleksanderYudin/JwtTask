package ru.inside.jwttask.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi publicUserApi() {
        return GroupedOpenApi.builder()
                .group("users")
                .pathsToMatch("/v1/**")
                .build();
    }

    @Bean
    public OpenAPI customOpenApi(@Value("${description}")String appDescription,
                                 @Value("${version}")String appVersion) {
        return new OpenAPI().info(new Info().title("JwtTask-application API")
                        .version(appVersion)
                        .description(appDescription)
                        .contact(new Contact().name("Alex")
                                .email("example@gmail.com")))
                .servers(new ArrayList<>(Collections.singletonList(new Server().url("http://localhost:8080")
                        .description("JwtTask-service"))));
    }
}
