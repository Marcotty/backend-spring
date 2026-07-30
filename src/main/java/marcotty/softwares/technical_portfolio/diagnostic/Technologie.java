package marcotty.softwares.technical_portfolio.diagnostic;

public class Technologie {

    public enum Statut {
        IMPLEMENTE, EN_COURS, A_FAIRE
    }

    private final String nom;
    private final Statut statut;
    private final String description;
    private final String exemple;

    public Technologie(String nom, Statut statut, String description, String exemple) {
        this.nom = nom;
        this.statut = statut;
        this.description = description;
        this.exemple = exemple;
    }

    public String getNom() { return nom; }
    public Statut getStatut() { return statut; }
    public String getDescription() { return description; }
    public String getExemple() { return exemple; }

    // Utilisé par Thymeleaf pour styliser le badge selon le statut
    public String getStatutClasse() {
        return switch (statut) {
            case IMPLEMENTE -> "ok";
            case EN_COURS -> "warn";
            case A_FAIRE -> "todo";
        };
    }

    public String getStatutLabel() {
        return switch (statut) {
            case IMPLEMENTE -> "implémenté";
            case EN_COURS -> "en cours";
            case A_FAIRE -> "à faire";
        };
    }
}