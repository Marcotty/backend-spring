package marcotty.softwares.technical_portfolio.diagnostic;

import java.util.List;

import org.springframework.stereotype.Service;

import static marcotty.softwares.technical_portfolio.diagnostic.Technologie.Statut.A_FAIRE;
import static marcotty.softwares.technical_portfolio.diagnostic.Technologie.Statut.EN_COURS;
import static marcotty.softwares.technical_portfolio.diagnostic.Technologie.Statut.IMPLEMENTE;

@Service
public class TechnologieWatchlistService {

    // Liste extraite d'une offre d'emploi "Backend Developer" — filtrée sur la stack Java.
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
            "Tests d'intégration avec @SpringBootTest et @AutowiredMockMVC sur le contrôleur REST",
            "ProjetServiceTest teste le service avec un repository mocké, sans toucher à la vraie base de données."
        ),

        new Technologie(
            "CI/CD",
            IMPLEMENTE,
            "Build + tests automatisés à chaque push, pour détecter les régressions sans lancer les tests à la main.",
            "Pipeline GitHub Actions simple qui lance `mvnw test` à chaque push, " +
            "puis ajoute `./mvnw spring-boot:build-image` une fois les tests passés pour construire l'image Docker."
        ),

        new Technologie(
            "Base de données relationnelle (PostgreSQL)",
            IMPLEMENTE,
            "Base relationnelle principale du projet, avec JPA/Hibernate pour la persistance.",
            "ProjetRepository est un JpaRepository standard, avec PostgreSQL en production et H2 pour les tests."
        ),

        new Technologie(
            "Base de données NoSQL (MongoDB)",
            A_FAIRE,
            "Utile pour des données non structurées ou évolutives (ex: logs, historique d'activité).",
            "À faire, en piste future : stocker le journal des requêtes (RequestLogEntry) dans MongoDB " +
            "plutôt qu'en mémoire, pour qu'il survive à un redémarrage."
        ),

        new Technologie(
            "GraphQL",
            IMPLEMENTE,
            "Alternative à REST où le client choisit précisément les champs qu'il veut recevoir.",
            "GET /api/projets en GraphQL avec `spring-boot-starter-graphql`, " +
            "ProjetGraphQLController et schema.graphqls déjà en place."
        ),

        new Technologie(
            "POSTMAN",
            IMPLEMENTE,
            "Outil pour tester les API REST et GraphQL.",
            "Collection Postman contenant des requêtes pour tester GET /api/projets et autres endpoints."
        ),

        new Technologie(
            "SWAGGER / OpenAPI",
            A_FAIRE,
            "Outil pour documenter et tester les API REST.",
            "Documentation Swagger générée automatiquement, avec possibilité de tester les endpoints directement depuis l'interface."
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
            "Docker Compose Support ajouté au projet, et packaging en image et run dans un container.",
            "."
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
            "Gestion de version - Historique des commits, branches, merges, tags, etc.",
            "Le projet est versionné sur GitHub, avec des commits réguliers et des branches pour les fonctionnalités."
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