package marcotty.softwares.technical_portfolio.diagnostic;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// @Document est l'équivalent MongoDB de @Entity en JPA : ça dit à Spring Data que
// cette classe correspond à une "collection" Mongo (l'équivalent NoSQL d'une table).
// Contrairement à JPA, pas besoin de définir un schéma à l'avance — MongoDB est
// "schema-less" : chaque document peut avoir une structure légèrement différente.
@Document(collection = "request_logs")
public class RequestLogDocument {

    @Id
    private String id; // MongoDB génère un ObjectId automatiquement, exposé ici en String

    private String methode;
    private String chemin;
    private String origine;
    private LocalDateTime horodatage;

    public RequestLogDocument() {}

    public RequestLogDocument(String methode, String chemin, String origine) {
        this.methode = methode;
        this.chemin = chemin;
        this.origine = origine;
        this.horodatage = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getMethode() { return methode; }
    public String getChemin() { return chemin; }
    public String getOrigine() { return origine; }
    public LocalDateTime getHorodatage() { return horodatage; }
}