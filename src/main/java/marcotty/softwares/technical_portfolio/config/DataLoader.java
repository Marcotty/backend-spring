package marcotty.softwares.technical_portfolio.config;

import marcotty.softwares.technical_portfolio.model.Projet;
import marcotty.softwares.technical_portfolio.repository.ProjetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProjetRepository repository;

    public DataLoader(ProjetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        repository.save(new Projet("Java Mastery", "Plateforme d'apprentissage Java", "Spring, JPA", "http://localhost:8081", "actif"));
        repository.save(new Projet("Portfolio Hub", "Ce site lui-même", "Spring, Angular, Docker", "http://localhost:8080", "actif"));
    }
}  