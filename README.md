# Ebanking-microservices

Ce projet permet la gestion des comptes bancaires (clients, comptes & opérations) avec une architecture microservices.

## Microservices

### discovery-service
- **Port :** non configuré (défaut Spring Boot : 8080)
- **Dépendances :**
  - `spring-cloud-starter-netflix-eureka-server`
  - `spring-cloud-starter-config`
  - `spring-boot-starter-actuator`

### config-service
- **Port :** non configuré (défaut Spring Boot : 8080)
- **Dépendances :**
  - `spring-cloud-config-server`

### gateway-service
- **Port :** non configuré (défaut Spring Boot : 8080)
- **Dépendances :**
  - `spring-cloud-starter-gateway-server-webmvc`
  - `spring-cloud-starter-netflix-eureka-client`
  - `spring-cloud-starter-config`
  - `spring-boot-starter-actuator`

### auth-service
- **Port :** non configuré (défaut Spring Boot : 8080)
- **Dépendances :**
  - `spring-boot-starter-security-oauth2-resource-server`
  - `spring-boot-starter-webmvc`

### customer-service
- **Port :** non configuré (défaut Spring Boot : 8080)
- **Dépendances :**
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-webmvc`
  - `spring-cloud-starter-netflix-eureka-client`
  - `spring-cloud-starter-config`
  - `spring-boot-starter-actuator`
  - `postgresql` (runtime)
  - `lombok`

### account-service
- **Port :** non configuré (défaut Spring Boot : 8080)
- **Dépendances :**
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-webmvc`
  - `spring-cloud-starter-netflix-eureka-client`
  - `spring-cloud-starter-config`
  - `spring-boot-starter-actuator`
  - `postgresql` (runtime)
  - `lombok`

### operation-service
- **Port :** non configuré (défaut Spring Boot : 8080)
- **Dépendances :**
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-webmvc`
  - `spring-cloud-starter-netflix-eureka-client`
  - `spring-cloud-starter-config`
  - `spring-boot-starter-actuator`
  - `mysql-connector-j` (runtime)
  - `lombok`
