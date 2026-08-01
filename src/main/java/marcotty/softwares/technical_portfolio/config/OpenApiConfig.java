package marcotty.softwares.technical_portfolio.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI technicalPortfolioOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Technical Portfolio API")
                .description("API du site vitrine — projets, historique de requêtes, diagnostic serveur.")
                .version("1.0"));
    }
}