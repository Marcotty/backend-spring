package marcotty.softwares.technical_portfolio.diagnostic;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class RequestLogService {

    // Nombre maximum d'entrées gardées en mémoire, pour éviter une croissance infinie
    private static final int TAILLE_MAX = 30;

    // CopyOnWriteArrayList : thread-safe, adapté ici puisqu'on a beaucoup plus de
    // lectures (affichage) que d'écritures (une requête à la fois), sans synchronisation manuelle
    private final List<RequestLogEntry> entrees = new CopyOnWriteArrayList<>();

    public void enregistrer(String methode, String chemin, String origine) {
        entrees.add(0, new RequestLogEntry(methode, chemin, origine)); // le plus récent en premier
        while (entrees.size() > TAILLE_MAX) {
            entrees.remove(entrees.size() - 1);
        }
    }

    public List<RequestLogEntry> getEntreesRecentes() {
        return Collections.unmodifiableList(entrees);
    }
}