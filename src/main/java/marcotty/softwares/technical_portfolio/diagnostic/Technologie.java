package marcotty.softwares.technical_portfolio.diagnostic;

public class Technologie {

    public enum Statut {
        IMPLEMENTE, EN_COURS, A_FAIRE
    }

    private final String nom;
    private final Statut statut;
    private final String description;
    private final String exemple;
    private final String lienUrl;    // nullable — pas toutes les technos n'ont un lien pertinent
    private final String lienLabel;  // texte du bouton, ex: "Ouvrir Swagger UI"

    // Constructeur historique (sans lien) — conservé pour ne pas casser les entrées existantes
    public Technologie(String nom, Statut statut, String description, String exemple) {
        this(nom, statut, description, exemple, null, null);
    }

    // Nouveau constructeur, avec lien optionnel
    public Technologie(String nom, Statut statut, String description, String exemple,
                        String lienUrl, String lienLabel) {
        this.nom = nom;
        this.statut = statut;
        this.description = description;
        this.exemple = exemple;
        this.lienUrl = lienUrl;
        this.lienLabel = lienLabel;
    }

    public String getNom() { return nom; }
    public Statut getStatut() { return statut; }
    public String getDescription() { return description; }
    public String getExemple() { return exemple; }
    public String getLienUrl() { return lienUrl; }
    public String getLienLabel() { return lienLabel; }

    public boolean isAvecLien() { return lienUrl != null && !lienUrl.isBlank(); }

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