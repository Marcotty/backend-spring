package marcotty.softwares.technical_portfolio;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import marcotty.softwares.technical_portfolio.model.Projet;
import marcotty.softwares.technical_portfolio.repository.ProjetRepository;
import marcotty.softwares.technical_portfolio.services.ProjetService;

// Test UNITAIRE : le repository est mocké, on ne touche à aucune vraie base de données.
// Objectif : vérifier que le Service délègue correctement au Repository, rien de plus.
@ExtendWith(MockitoExtension.class)
class ProjetServiceTest {

    @Mock
    private ProjetRepository repository;

    @InjectMocks
    private ProjetService service;

    @Test
    void findAll_delegueAuRepository_etRetourneLaListe() {
        // Arrange : on prépare ce que le repository mocké doit retourner
        Projet projetMock = new Projet(
            "Java Mastery", "Plateforme d'apprentissage", "Spring, JPA",
            "http://localhost:8081", "actif", "sparkline", "#4FD1C5", 8081
        );
        when(repository.findAll()).thenReturn(List.of(projetMock));

        // Act : on appelle la méthode réellement testée
        List<Projet> resultat = service.findAll();

        // Assert : on vérifie le résultat ET que le repository a bien été sollicité une seule fois
        assertThat(resultat).hasSize(1);
        assertThat(resultat.get(0).getNom()).isEqualTo("Java Mastery");
        verify(repository).findAll();
    }

    @Test
    void findAll_repositoryVide_retourneListeVide() {
        when(repository.findAll()).thenReturn(List.of());

        List<Projet> resultat = service.findAll();

        assertThat(resultat).isEmpty();
    }
}