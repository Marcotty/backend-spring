package marcotty.softwares.technical_portfolio.controller.graphql;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import marcotty.softwares.technical_portfolio.model.Projet;
import marcotty.softwares.technical_portfolio.repository.ProjetRepository;

// @Controller (pas @RestController) : Spring GraphQL a son propre mécanisme de mapping,
// via @QueryMapping plutôt que @GetMapping. C'est une TROISIÈME façade, au même niveau
// que ConceptRestController (REST) et DiagnosticController (MVC), toutes branchées sur
// le même ProjetRepository — exactement le principe de découplage vu depuis le début.
@Controller
public class ProjetGraphQLController {

    private final ProjetRepository repository;

    public ProjetGraphQLController(ProjetRepository repository) {
        this.repository = repository;
    }

    // Correspond à "projets: [Projet]" dans schema.graphqls
    @QueryMapping
    public List<Projet> projets() {
        return repository.findAll();
    }

    // Correspond à "projet(id: ID!): Projet" dans schema.graphqls
    @QueryMapping
    public Projet projet(@Argument Long id) {
        return repository.findById(id).orElse(null);
    }
}