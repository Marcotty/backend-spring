package marcotty.softwares.technical_portfolio;

import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import marcotty.softwares.technical_portfolio.model.Projet;
import marcotty.softwares.technical_portfolio.repository.ProjetRepository;

// Test D'INTÉGRATION : @SpringBootTest démarre tout le contexte Spring (vraie config,
// vrai passage par le DispatcherServlet), sur la base H2 en mémoire — pas besoin de
// PostgreSQL pour faire tourner ce test.
@SpringBootTest
@AutoConfigureMockMvc
class ProjetRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProjetRepository repository;

    // Important : le DataLoader (CommandLineRunner) insère déjà 3 projets au démarrage
    // du contexte Spring, y compris pendant les tests. On nettoie explicitement avant
    // chaque test pour partir d'un état connu, plutôt que de dépendre de ce qui a été
    // inséré ailleurs.
    @BeforeEach
    void nettoyerLaBase() {
        repository.deleteAll();
    }

    @Test
    void getProjets_retourne200EtLaListeAuFormatJson() throws Exception {
        // Arrange
        repository.save(new Projet(
            "Test Projet", "Description de test", "Spring",
            "http://localhost:9999", "actif", "sparkline", "#4FD1C5", 9999
        ));

        // Act + Assert
        mockMvc.perform(get("/api/projets"))
            .andExpect(status().isOk())
            .andExpect(content().contentType("application/json"))
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].nom").value("Test Projet"))
            .andExpect(jsonPath("$[0].statut").value("actif"));
    }

    @Test
    void getProjets_baseVide_retourneListeVide() throws Exception {
        mockMvc.perform(get("/api/projets"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(0)));
    }
}