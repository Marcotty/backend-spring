package marcotty.softwares.technical_portfolio.diagnostic;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RequestLogService {

    private static final Logger log = LoggerFactory.getLogger(RequestLogService.class);
    private static final int TAILLE_MAX_MEMOIRE = 30;

    private final List<RequestLogEntry> entreesMemoire = new CopyOnWriteArrayList<>();
    private final RequestLogMongoRepository mongoRepository;

    public RequestLogService(RequestLogMongoRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    public void enregistrer(String methode, String chemin, String origine) {
        // 1) Affichage rapide : liste en mémoire, jamais bloquant
        entreesMemoire.add(0, new RequestLogEntry(methode, chemin, origine));
        while (entreesMemoire.size() > TAILLE_MAX_MEMOIRE) {
            entreesMemoire.remove(entreesMemoire.size() - 1);
        }

        // 2) Persistance MongoDB — ENTOURÉE d'un try/catch volontairement : si MongoDB
        // est indisponible, l'application doit continuer à fonctionner normalement
        // (juste sans historique durable), plutôt que de planter TOUTES les requêtes.
        try {
            mongoRepository.save(new RequestLogDocument(methode, chemin, origine));
        } catch (Exception e) {
            log.warn("Impossible d'écrire le log dans MongoDB (base indisponible ?) : {}", e.getMessage());
        }
    }

    public List<RequestLogEntry> getEntreesRecentes() {
        return Collections.unmodifiableList(entreesMemoire);
    }

    public long getTotalHistorique() {
        try {
            return mongoRepository.count();
        } catch (Exception e) {
            log.warn("Impossible de lire le total MongoDB : {}", e.getMessage());
            return -1; // -1 = signal visuel qu'il y a un souci de connexion, à afficher côté template
        }
    }

    // Utilisé par le nouvel endpoint de debug, testable depuis Postman
    public List<RequestLogDocument> getHistoriqueComplet() {
        return mongoRepository.findAllByOrderByHorodatageDesc();
    }
}