package marcotty.softwares.technical_portfolio.diagnostic;

import java.util.List;

import org.springframework.stereotype.Service;

import static marcotty.softwares.technical_portfolio.diagnostic.Technologie.Statut.A_FAIRE;
import static marcotty.softwares.technical_portfolio.diagnostic.Technologie.Statut.EN_COURS;
import static marcotty.softwares.technical_portfolio.diagnostic.Technologie.Statut.IMPLEMENTE;

@Service
public class TechnologieWatchlistService {

    private static final String GITHUB_REPO_URL = "https://github.com/Marcotty/backend-spring";

    // Objectif : garder une trace visible de ce qui reste à explorer, avec un exemple concret
    // à reproduire plus tard dans le projet Java Mastery pour chaque techno.
    private final List<Technologie> technologies = List.of(

        new Technologie(
            "Spring Boot / API REST",
            IMPLEMENTE,
            "Framework backend principal du projet. Contrôleurs REST, injection de dépendances, auto-configuration.",
            "ProjetRestController expose déjà GET /api/projets — c'est la base déjà en place."
        ),

        new Technologie(
            "Tests unitaires & d'intégration (JUnit + Mockito)",
            IMPLEMENTE,
            "Cycle Red-Green-Refactor appliqué : test unitaire du Service (repository mocké) et test d'intégration du contrôleur REST.",
            "ProjetServiceTest (Mockito) + ProjetRestControllerTest (@SpringBootTest, MockMvc, base H2 nettoyée avant chaque test)."
        ),

        new Technologie(
            "CI/CD",
            IMPLEMENTE,
            "Pipeline GitHub Actions : lance les tests à chaque push, construit l'image Docker uniquement si les tests passent.",
            ".github/workflows/ci.yml — deux jobs, dépendance explicite via 'needs: test'. mvnw rendu exécutable en Git et en CI.",
            GITHUB_REPO_URL + "/actions",
            "Voir les runs sur GitHub"
        ),

        new Technologie(
            "Base de données relationnelle (PostgreSQL)",
            IMPLEMENTE,
            "PostgreSQL tourne en conteneur Docker. Bascule H2 ↔ PostgreSQL possible à chaud via AbstractRoutingDataSource.",
            "Panneau ⚙ sur la page de diagnostic : teste la connexion avant bascule, log chaque étape (RoutingDataSource + DatabaseContext)."
        ),

        new Technologie(
            "Base de données NoSQL (MongoDB)",
            IMPLEMENTE,
            "Le journal des requêtes (RequestLogEntry) persiste maintenant dans MongoDB plutôt qu'en mémoire — survit aux redémarrages.",
            "RequestLogDocument + RequestLogMongoRepository. La liste en mémoire reste pour l'affichage rapide (30 dernières), Mongo garde tout l'historique.",
            "/api/logs/historique",
            "Voir l'historique (JSON)"
        ),

        new Technologie(
            "POSTMAN",
            IMPLEMENTE,
            "Outil pour tester les API REST et GraphQL.",
            "Collection Postman contenant des requêtes pour tester GET /api/projets et autres endpoints."
        ),

        new Technologie(
            "SWAGGER / OpenAPI",
            IMPLEMENTE,
            "Documentation interactive générée automatiquement depuis les annotations des contrôleurs REST.",
            "springdoc-openapi-starter-webmvc-ui — interface testable sur /swagger-ui.html, JSON brut sur /v3/api-docs.",
            "/swagger-ui.html",
            "Ouvrir Swagger UI"
        ),

        new Technologie(
            "GraphQL",
            IMPLEMENTE,
            "Endpoint /graphql en plus de REST — le client choisit précisément les champs qu'il veut recevoir.",
            "ProjetGraphQLController + schema.graphqls. Testé via Postman (POST avec query/variables) et GraphiQL.",
            "/graphiql",
            "Ouvrir GraphiQL"
        ),

        new Technologie(
            "Architecture microservices",
            EN_COURS,
            "Chaque projet du portfolio tourne dans son propre conteneur, indépendant, mais la communication entre eux n'est pas encore formalisée.",
            "Amorcé via la séparation backend/frontend en conteneurs distincts ; reste à faire : un vrai " +
            "appel inter-services (ex: le site vitrine qui interroge l'API du projet météo)."
        ),

        new Technologie(
            "Docker",
            IMPLEMENTE,
            "Backend packagé en image Docker (Cloud Native Buildpacks) et exécuté en conteneur, aux côtés de PostgreSQL et MongoDB.",
            "./mvnw spring-boot:build-image, puis docker run — l'app tourne entièrement conteneurisée, plus besoin de VS Code/mvnw pour la lancer."
        ),

        new Technologie(
            "Kubernetes",
            A_FAIRE,
            "Orchestrateur de conteneurs à grande échelle — gère le déploiement, la montée en charge et la résilience automatiquement.",
            "À explorer une fois plusieurs projets conteneurisés : déployer le portfolio complet sur un " +
            "cluster local (ex: Minikube ou Docker Desktop Kubernetes intégré)."
        ),

        new Technologie(
            "Cloud (AWS / Azure / GCP)",
            A_FAIRE,
            "Hébergement en production — actuellement tout tourne en local uniquement.",
            "À faire, en piste future : déployer une image Docker construite plus haut sur un service " +
            "managé simple (ex: AWS App Runner ou Azure Container Apps) pour voir le portfolio en ligne."
        ),

        new Technologie(
            "Git",
            IMPLEMENTE,
            "Dépôt versionné sur GitHub, avec permissions d'exécution correctement gérées (mvnw) pour un fonctionnement fiable en CI.",
            "git update-index --chmod=+x mvnw — pour que le bit d'exécution survive au checkout, peu importe l'OS source.",
            GITHUB_REPO_URL,
            "Voir le dépôt"
        ),

        new Technologie(
            "Méthodologie Agile (Scrum / Kanban)",
            EN_COURS,
            "Théorie vue (rôles, cérémonies, niveau expert), mais jamais appliquée formellement sur ce projet solo.",
            "À faire, en piste future : tenir un tableau Kanban personnel (Trello, GitHub Projects) avec " +
            "cette liste même de technologies comme colonnes 'à faire / en cours / fait'."
        )
    );

    public List<Technologie> getTechnologies() {
        return technologies;
    }
}