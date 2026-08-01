package marcotty.softwares.technical_portfolio.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import marcotty.softwares.technical_portfolio.model.Projet;
import marcotty.softwares.technical_portfolio.services.ProjetService;

// @Tag regroupe visuellement les endpoints dans Swagger UI — utile dès que tu as
// plusieurs contrôleurs (ProjetRestController, LogsApiController...), pour ne pas
// avoir une liste plate de 10 endpoints mélangés.
@Tag(name = "Projets", description = "Gestion des projets affichés sur le site vitrine")
@RestController
@RequestMapping("/api/projets")
public class ProjetRestController {

    private final ProjetService service;

    public ProjetRestController(ProjetService service) {
        this.service = service;
    }

    @Operation(summary = "Liste tous les projets", description = "Retourne l'ensemble des projets stockés, tous statuts confondus.")
    @GetMapping
    public List<Projet> getProjets() {
        return service.findAll();
    }
}