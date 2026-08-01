package marcotty.softwares.technical_portfolio.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import marcotty.softwares.technical_portfolio.diagnostic.RequestLogDocument;
import marcotty.softwares.technical_portfolio.diagnostic.RequestLogService;

@Tag(name = "Logs", description = "Inspection de l'historique des requêtes (persisté en MongoDB)")
@RestController
@RequestMapping("/api/logs")
public class LogsApiController {

    private final RequestLogService requestLogService;

    public LogsApiController(RequestLogService requestLogService) {
        this.requestLogService = requestLogService;
    }

    @Operation(summary = "Historique complet des requêtes", description = "Toutes les requêtes jamais journalisées, triées de la plus récente à la plus ancienne.")
    @GetMapping("/historique")
    public List<RequestLogDocument> getHistoriqueComplet() {
        return requestLogService.getHistoriqueComplet();
    }

    @Operation(summary = "Nombre total de requêtes journalisées", description = "Retourne -1 si MongoDB est injoignable.")
    @GetMapping("/total")
    public long getTotal() {
        return requestLogService.getTotalHistorique();
    }
}