package marcotty.softwares.technical_portfolio.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import marcotty.softwares.technical_portfolio.diagnostic.DatabaseContext;
import marcotty.softwares.technical_portfolio.diagnostic.ModuleStatus;
import marcotty.softwares.technical_portfolio.diagnostic.RequestLogService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Système", description = "État en temps réel des modules du site (pour la carte de diagnostic V2)")
@RestController
@RequestMapping("/api/system-status")
public class SystemStatusController {

    private final DatabaseContext databaseContext;
    private final RequestLogService requestLogService;
    private final DataSource h2DataSource;
    private final DataSource postgresDataSource;
    // Réutilisation directe du repository Mongo existant, pour tester sa disponibilité
    private final marcotty.softwares.technical_portfolio.diagnostic.RequestLogMongoRepository mongoRepository;

    public SystemStatusController(DatabaseContext databaseContext,
                                   RequestLogService requestLogService,
                                   @Qualifier("h2DataSource") DataSource h2DataSource,
                                   @Qualifier("postgresDataSource") DataSource postgresDataSource,
                                   marcotty.softwares.technical_portfolio.diagnostic.RequestLogMongoRepository mongoRepository) {
        this.databaseContext = databaseContext;
        this.requestLogService = requestLogService;
        this.h2DataSource = h2DataSource;
        this.postgresDataSource = postgresDataSource;
        this.mongoRepository = mongoRepository;
    }

    @Operation(summary = "État de tous les modules du système", description = "Backend, base active, MongoDB, historique — testés en direct à chaque appel.")
    @GetMapping
    public List<ModuleStatus> getStatuts() {
        List<ModuleStatus> statuts = new ArrayList<>();

        // --- Backend lui-même : si ce code s'exécute, il est nécessairement "ok" ---
        long uptimeMs = ManagementFactory.getRuntimeMXBean().getUptime();
        statuts.add(new ModuleStatus(
            "backend", "Spring Boot", "ok",
            "Uptime : " + (uptimeMs / 1000) + "s",
            (double) uptimeMs / 1000
        ));

        // --- Base de données active (H2 ou PostgreSQL selon le contexte) ---
        String baseActive = databaseContext.getBaseActive();
        DataSource cible = baseActive.equals(DatabaseContext.POSTGRES) ? postgresDataSource : h2DataSource;
        statuts.add(testerConnexionSql(baseActive.equals("postgres") ? "PostgreSQL" : "H2", baseActive, cible));

        // --- MongoDB ---
        try {
            long total = mongoRepository.count();
            statuts.add(new ModuleStatus("mongodb", "MongoDB", "ok",
                total + " logs historisés", (double) total));
        } catch (Exception e) {
            statuts.add(new ModuleStatus("mongodb", "MongoDB", "down",
                "Injoignable : " + e.getMessage(), null));
        }

        // --- GraphQL : même processus que le backend REST, donc "ok" si le backend l'est ---
        statuts.add(new ModuleStatus("graphql", "GraphQL", "ok", "Endpoint /graphql actif", null));

        return statuts;
    }

    private ModuleStatus testerConnexionSql(String nomAffiche, String id, DataSource ds) {
        try (Connection c = ds.getConnection()) {
            return new ModuleStatus(id, nomAffiche, "ok", "Connexion établie", null);
        } catch (Exception e) {
            return new ModuleStatus(id, nomAffiche, "down", "Injoignable : " + e.getMessage(), null);
        }
    }
}