package br.com.challenge.caju.transaction.service.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "transaction-service",
                version = "1.0",
                description = "Transaction Service"
        )
)
@Configuration
public class SwaggerConfig {
}
