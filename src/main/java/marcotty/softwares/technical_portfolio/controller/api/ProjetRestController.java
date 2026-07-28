package marcotty.softwares.technical_portfolio.controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import marcotty.softwares.technical_portfolio.model.Projet;
import marcotty.softwares.technical_portfolio.services.ProjetService;

@RestController
@RequestMapping("/api/projets")
public class ProjetRestController {

    private final ProjetService service;

    public ProjetRestController(ProjetService service) {
        this.service = service;
    }

    @GetMapping
    public List<Projet> getProjets() {
        return service.findAll();
    }
}