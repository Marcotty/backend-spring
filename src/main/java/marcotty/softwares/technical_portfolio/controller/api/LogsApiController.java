package marcotty.softwares.technical_portfolio.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marcotty.softwares.technical_portfolio.diagnostic.RequestLogDocument;
import marcotty.softwares.technical_portfolio.diagnostic.RequestLogService;

// Endpoint purement diagnostique : permet d'inspecter le contenu réel de MongoDB
// depuis Postman (ou n'importe quel client HTTP), sans passer par Compass ou mongosh.
@RestController
@RequestMapping("/api/logs")
public class LogsApiController {

    private final RequestLogService requestLogService;

    public LogsApiController(RequestLogService requestLogService) {
        this.requestLogService = requestLogService;
    }

    @GetMapping("/historique")
    public List<RequestLogDocument> getHistoriqueComplet() {
        return requestLogService.getHistoriqueComplet();
    }

    @GetMapping("/total")
    public long getTotal() {
        return requestLogService.getTotalHistorique();
    }
}