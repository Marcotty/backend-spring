package marcotty.softwares.technical_portfolio.controller.web;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import marcotty.softwares.technical_portfolio.diagnostic.DatabaseContext;
import marcotty.softwares.technical_portfolio.diagnostic.RequestLogService;

@Controller
public class DatabaseSwitchController {

    private final DatabaseContext databaseContext;
    private final RequestLogService requestLogService;
    private final DataSource h2DataSource;
    private final DataSource postgresDataSource;

    public DatabaseSwitchController(DatabaseContext databaseContext,
                                     RequestLogService requestLogService,
                                     @Qualifier("h2DataSource") DataSource h2DataSource,
                                     @Qualifier("postgresDataSource") DataSource postgresDataSource) {
        this.databaseContext = databaseContext;
        this.requestLogService = requestLogService;
        this.h2DataSource = h2DataSource;
        this.postgresDataSource = postgresDataSource;
    }

    // Pattern Post-Redirect-Get : on redirige après le POST plutôt que de renvoyer
    // directement une vue, pour éviter qu'un rafraîchissement de page ne renvoie
    // accidentellement le même formulaire (vu dans la partie MVC du crash course).
    @PostMapping("/diagnostic/basculer-db")
    public String basculer(@RequestParam String base) {
        String baseActuelle = databaseContext.getBaseActive();

        requestLogService.enregistrer("SYSTEM", "Demande de bascule : " + baseActuelle + " → " + base, "Panneau de diagnostic");

        if (baseActuelle.equals(base)) {
            requestLogService.enregistrer("SYSTEM", "Bascule ignorée (déjà sur " + base + ")", "Panneau de diagnostic");
            return "redirect:/";
        }

        DataSource cible = base.equals(DatabaseContext.POSTGRES) ? postgresDataSource : h2DataSource;

        // On teste la connexion AVANT de basculer réellement, pour ne jamais laisser
        // l'application dans un état cassé si PostgreSQL n'est pas démarré par exemple.
        try (Connection connexionTest = cible.getConnection()) {
            requestLogService.enregistrer("SYSTEM", "Connexion à " + base + " testée avec succès", "Panneau de diagnostic");
        } catch (SQLException e) {
            requestLogService.enregistrer("SYSTEM", "Échec de connexion à " + base + " — bascule annulée (" + e.getMessage() + ")", "Panneau de diagnostic");
            return "redirect:/";
        }

        databaseContext.setBaseActive(base);
        requestLogService.enregistrer("SYSTEM", "Bascule effective vers " + base, "Panneau de diagnostic");

        return "redirect:/";
    }
}