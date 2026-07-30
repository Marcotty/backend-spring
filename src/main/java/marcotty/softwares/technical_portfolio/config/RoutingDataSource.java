package marcotty.softwares.technical_portfolio.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import marcotty.softwares.technical_portfolio.diagnostic.DatabaseContext;

// AbstractRoutingDataSource est une classe Spring standard, utilisée en entreprise pour
// le multi-tenancy ou la séparation lecture/écriture. Son unique rôle : à chaque demande
// de connexion, elle appelle determineCurrentLookupKey() pour savoir vers QUELLE
// DataSource concrète (parmi celles enregistrées) elle doit rediriger l'appel.
public class RoutingDataSource extends AbstractRoutingDataSource {

    private final DatabaseContext databaseContext;

    public RoutingDataSource(DatabaseContext databaseContext) {
        this.databaseContext = databaseContext;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return databaseContext.getBaseActive(); // "h2" ou "postgres"
    }
}