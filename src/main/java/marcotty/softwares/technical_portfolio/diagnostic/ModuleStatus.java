package marcotty.softwares.technical_portfolio.diagnostic;

// record : classe immuable concise (vue dans le crash course Java) — parfait pour
// un simple objet de transfert comme celui-ci, pas besoin de getters/setters manuels.
public record ModuleStatus(
    String id,          // identifiant stable, utilisé côté Angular pour positionner le nœud
    String nom,
    String statut,      // "ok" | "warn" | "down"
    String detail,       // texte libre affiché au clic sur le nœud
    Double metrique       // valeur numérique optionnelle (ex: nombre de requêtes), nullable
) {}