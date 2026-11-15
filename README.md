# Projet Gestion de Commandes
## Deuxième Travail Pratique pour le cours de CAR - MIAGE M1 U-Lille

Application web de gestion de commandes : un client se connecte (ou s'inscrit), crée ses commandes, y ajoute des articles, puis peut imprimer la liste des articles d'une commande.

## Technologies

- Java 17
- Spring Boot 3.4.2 (Spring Web MVC, Spring Data JPA, Thymeleaf)
- Base de données H2 en mémoire
- Gradle (wrapper inclus)

## Lancer le projet

Prérequis : JDK 17.

```bash
./gradlew bootRun        # Windows : gradlew.bat bootRun
```

Puis ouvrir [http://localhost:8080/store/home](http://localhost:8080/store/home).

- Console H2 : [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (URL JDBC : `jdbc:h2:mem:test`, utilisateur `sa`, mot de passe vide).
- Les données sont en mémoire : elles sont perdues à chaque redémarrage.

## Architecture

Le code est découpé par domaine (un package par entité), avec la séparation contrôleur / service / repository :

```
src/main/java/com/example/gestioncom/
├── clients/     Client, ClientController, ClientService, ClientRepository
├── commandes/   Commande, CommandeController, CommandeService, CommandeRepository
└── articles/    Article, ArticleService, ArticleRepository
src/main/resources/templates/
├── login.html, home.html, commandeinfo.html, impressioncommande.html, error403.html
```

Les routes sont les suivantes :

| Méthode | Route | Rôle |
|---|---|---|
| GET | `/store/home` | Page de connexion, ou accueil du client connecté avec ses commandes |
| POST | `/store/login` | Connexion (email + mot de passe) |
| POST | `/store/signup` | Inscription |
| POST | `/store/logout` | Déconnexion |
| POST | `/store/commande/creation` | Création d'une commande |
| GET | `/commande/{id}` | Détail d'une commande et de ses articles |
| POST | `/commande/{id}/ajouter` | Ajout d'un article à la commande |
| POST | `/commande/{id}/effacer/{articleId}` | Suppression d'un article |
| POST | `/commande/{id}/imprimer` | Page d'impression des articles |

## Avancement des itérations

1. **Gestion des Clients : fait (version de base)**
   - Inscription, connexion et déconnexion, avec l'état stocké dans la session HTTP.
   - Limite : le contrôle de doublon porte sur l'e-mail **et** le mot de passe, donc un même e-mail peut être inscrit plusieurs fois avec des mots de passe différents.
   - Limite : les mots de passe sont stockés en clair et les formulaires utilisent des champs texte visibles.

2. **Gestion des Commandes : fait**
   - Création d'une commande liée au client connecté.
   - Affichage de la liste des commandes du client sur l'accueil.
   - Accès au détail d'une commande, refusé (page 403) si elle n'appartient pas au client connecté.

3. **Gestion des Articles sur chaque Commande : fait**
   - Ajout et suppression d'articles sur une commande.
   - Limite : la suppression et l'ajout ne vérifient pas encore que la commande appartient au client connecté (voir l'itération 5).

4. **Impression des Articles : partiel**
   - Route `POST /commande/{id}/imprimer` et gabarit `impressioncommande.html` qui affiche la liste des articles.
   - Limite : le gabarit est minimal (pas de mise en page d'impression, pas d'en-tête avec la commande ni le client).

5. **Protection des Routes : partiel**
   - Seule la consultation d'une commande (`GET /commande/{id}`) vérifie le propriétaire.
   - À faire : vérifier le propriétaire sur l'ajout, la suppression et l'impression d'articles. Ces routes acceptent aujourd'hui un identifiant de commande quelconque.
   - À faire : vérifier que l'utilisateur est connecté sur toutes les routes protégées, idéalement avec Spring Security.

6. **Démonstration du Fonctionnement : à faire**

## Limites connues

- Pas de hachage des mots de passe.
- Pas de validation des formulaires (champs vides, e-mail invalide).
- Un identifiant de commande inexistant provoque une erreur côté serveur au lieu d'une réponse propre.
- Les erreurs de connexion ou d'inscription redirigent simplement vers l'accueil, sans message à l'utilisateur.
- Tests : seul le test de chargement du contexte Spring existe (`GestioncomApplicationTests`).
