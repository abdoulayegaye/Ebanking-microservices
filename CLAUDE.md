# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Application e-banking basée sur une architecture microservices Spring Boot. Le projet gère les comptes bancaires, clients et opérations financières.

**Group ID :** `com.xoslu.tech`
**Stack :** Java 17, Spring Boot 3.5.13, Spring Cloud 2025.0.2, Maven

## Build & Run

```bash
# Compiler tous les services
./mvnw clean install

# Compiler un service spécifique (sans tests)
./mvnw clean install -DskipTests -pl customer-service

# Lancer un service
./mvnw spring-boot:run -pl customer-service

# Lancer tous les tests
./mvnw test

# Lancer les tests d'un service spécifique
./mvnw test -pl customer-service

# Lancer un test précis
./mvnw test -pl customer-service -Dtest=CustomerServiceApplicationTests
```

## Architecture des Services

Le projet contient 7 microservices indépendants, chacun avec son propre `pom.xml` et son répertoire `src/` :

| Service | Rôle | Base de données |
|---------|------|-----------------|
| `discovery-service` | Registre Eureka (Netflix) | — |
| `config-service` | Serveur de configuration Spring Cloud | — |
| `gateway-service` | API Gateway (Spring Cloud Gateway WebMVC) | — |
| `auth-service` | Authentification/Autorisation (OAuth2 Resource Server) | — |
| `customer-service` | Gestion des clients | PostgreSQL |
| `account-service` | Gestion des comptes bancaires | PostgreSQL |
| `operation-service` | Gestion des opérations/transactions | MySQL |

## Ordre de Démarrage

Les services doivent être démarrés dans cet ordre :

1. `discovery-service` — doit être disponible en premier (registre Eureka)
2. `config-service` — fournit la configuration aux autres services
3. `auth-service`, `customer-service`, `account-service`, `operation-service` — dans n'importe quel ordre
4. `gateway-service` — point d'entrée unique pour les appels API

## Flux de Communication

```
Client → gateway-service → (découverte via Eureka) → service métier
                              ↑
                        discovery-service
                              ↑
                        config-service (configuration centralisée)
```

- Les services métier s'enregistrent sur Eureka au démarrage
- Le gateway résout les routes dynamiquement via Eureka
- La configuration est externalisée dans `config-service`

## Prérequis Bases de Données

PostgreSQL et MySQL doivent être actifs avant de démarrer les services de données. Les connexions sont configurées dans les fichiers `application.properties` de chaque service (`customer-service`, `account-service`, `operation-service`).

Il n'existe pas de migration automatique (pas de Flyway/Liquibase) — la création des schémas est gérée par Spring Data JPA (via `spring.jpa.hibernate.ddl-auto`).

## Structure des Fichiers de Configuration

- Services utilisant YAML : `discovery-service`, `config-service`, `gateway-service`, `auth-service`
- Services utilisant properties : `customer-service`, `account-service`, `operation-service`
- Chemin : `[service]/src/main/resources/application.yaml` ou `application.properties`
