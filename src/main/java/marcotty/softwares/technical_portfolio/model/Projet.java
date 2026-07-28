package marcotty.softwares.technical_portfolio.model;

import  jakarta.persistence.*;

@Entity
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private String technologies;
    private String url;
    private String statut;

    public Projet() {}

    public Projet(String nom, String description, String technologies, String url, String statut) {
        this.nom = nom;
        this.description = description;
        this.technologies = technologies;
        this.url = url;
        this.statut = statut;
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
}