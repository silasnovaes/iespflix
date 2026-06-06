package br.uniesp.si.iespflix.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI iespflixOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("IESPFLIX API")
                        .description("Documentação oficial e interativa da API RESTful do sistema de simulação de streaming IESPFLIX.")
                        .version("1.0.0"));
    }
}