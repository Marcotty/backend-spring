package marcotty.softwares.technical_portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;

    // Stocké comme une simple chaîne séparée par des virgules (ex: "Spring, JPA, Angular").
    // Le découpage en tableau de tags se fait côté Angular, dans le service.
    private String technologies;

    private String url;

    // Valeurs attendues côté frontend : "actif", "charge", "arrete"
    private String statut;

    // Détermine quel widget afficher dans la carte : "sparkline" ou "map"
    private String widget;

    // Couleur d'accent hexadécimale utilisée par le widget (ex: "#4FD1C5")
    private String couleur;

    // Port sur lequel le conteneur du projet est exposé (purement informatif pour l'instant)
    private Integer port;

    public Projet() {}

    public Projet(String nom, String description, String technologies, String url,
                  String statut, String widget, String couleur, Integer port) {
        this.nom = nom;
        this.description = description;
        this.technologies = technologies;
        this.url = url;
        this.statut = statut;
        this.widget = widget;
        this.couleur = couleur;
        this.port = port;
    }

    public Long getId() { return id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getTechnologies() { return technologies; }
    public void setTechnologies(String technologies) { this.technologies = technologies; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getWidget() { return widget; }
    public void setWidget(String widget) { this.widget = widget; }

    public String getCouleur() { return couleur; }
    public void setCouleur(String couleur) { this.couleur = couleur; }

    public Integer getPort() { return port; }
    public void setPort(Integer port) { this.port = port; }
}