package marcotty.softwares.technical_portfolio.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariDataSource;

import marcotty.softwares.technical_portfolio.diagnostic.DatabaseContext;
import static marcotty.softwares.technical_portfolio.diagnostic.DatabaseContext.H2;
import static marcotty.softwares.technical_portfolio.diagnostic.DatabaseContext.POSTGRES;

@Configuration
public class DataSourceConfig {

    @Value("${app.datasource.h2.url}") private String h2Url;
    @Value("${app.datasource.h2.driver-class-name}") private String h2Driver;
    @Value("${app.datasource.h2.username}") private String h2User;
    @Value("${app.datasource.h2.password}") private String h2Password;

    @Value("${app.datasource.postgres.url}") private String pgUrl;
    @Value("${app.datasource.postgres.driver-class-name}") private String pgDriver;
    @Value("${app.datasource.postgres.username}") private String pgUser;
    @Value("${app.datasource.postgres.password}") private String pgPassword;

    @Bean
    public DataSource h2DataSource() {
        return DataSourceBuilder.create()
                .url(h2Url).driverClassName(h2Driver).username(h2User).password(h2Password)
                .build();
    }

    @Bean
    public DataSource postgresDataSource() {
        HikariDataSource ds = (HikariDataSource) DataSourceBuilder.create()
                .url(pgUrl).driverClassName(pgDriver).username(pgUser).password(pgPassword)
                .build();
        // -1 : ne PAS tester la connexion à la création du bean (comportement par défaut
        // de Hikari). Sans ça, l'application entière refuse de démarrer si PostgreSQL
        // n'est pas joignable — alors qu'on veut justement pouvoir tourner sur H2 seul.
        ds.setInitializationFailTimeout(-1);
        return ds;
    }

    // @Primary : c'est CETTE DataSource que Spring Data JPA utilisera pour construire
    // l'EntityManagerFactory — les deux DataSource ci-dessus ne sont que des "cibles"
    // enregistrées à l'intérieur, jamais utilisées directement par JPA.
    @Bean
    @Primary
    public DataSource routingDataSource(DatabaseContext databaseContext) {
        RoutingDataSource routingDataSource = new RoutingDataSource(databaseContext);

        Map<Object, Object> cibles = new HashMap<>();
        cibles.put(H2, h2DataSource());
        cibles.put(POSTGRES, postgresDataSource());

        routingDataSource.setTargetDataSources(cibles);
        routingDataSource.setDefaultTargetDataSource(h2DataSource());
        routingDataSource.afterPropertiesSet(); // requis par AbstractRoutingDataSource pour finaliser sa config

        return routingDataSource;
    }
}