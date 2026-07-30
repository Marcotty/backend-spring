package marcotty.softwares.technical_portfolio.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import marcotty.softwares.technical_portfolio.model.Projet;
import marcotty.softwares.technical_portfolio.repository.ProjetRepository;

@Component
public class DataLoader implements CommandLineRunner {

    private final ProjetRepository repository;

    public DataLoader(ProjetRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        repository.save(new Projet(
            "Java Mastery",
            "Plateforme d'apprentissage Java — concepts, exemples, annotations.",
            "Spring, JPA, Angular",
            "http://localhost:8081",
            "actif",
            "sparkline",
            "#4FD1C5",
            8081
        ));

        repository.save(new Projet(
            "Weather App",
            "Prévisions météo géolocalisées avec carte interactive.",
            "Spring, API REST",
            "http://localhost:8082",
            "actif",
            "map",
            "#E8A33D",
            8082
        ));

        repository.save(new Projet(
            "Chat Service",
            "Messagerie temps réel entre projets via WebSocket.",
            "Kafka, WebSocket",
            "http://localhost:8083",
            "charge",
            "sparkline",
            "#E8636B",
            8083
        ));
    }
}