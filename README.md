# Spring Quiz Microservices Project

This is a hobby project designed to explore **Spring Boot microservices** deployed with **Kubernetes**, orchestrated via **Skaffold**, with the future potential to integrate **Spring AI**.  

The primary goals of this project are:
- To learn modern Spring Boot microservice architecture.
- To gain hands-on experience with Kubernetes manifests and Skaffold for local orchestration.
- To leave space for future AI integration using Spring AI.

---

## Repository Structure

```
spring-microservices/
│
├── pom.xml               # Parent POM (dependency + plugin management)
│
├── question-service/      # Microservice 1: handles questions
│   └── pom.xml
│
├── quiz-service/          # Microservice 2: handles quizzes
│   └── pom.xml
│
└── k8s/                   # Kubernetes manifests
    ├── question-deployment.yaml
    ├── question-service.yaml
    ├── quiz-deployment.yaml
    ├── quiz-service.yaml
    ├──....
    ├──jobs
        ├──(jobs)
```

---

## Skaffold Configuration

I used Skaffold to manage local Kubernetes builds and deployments.  
This allows quick iteration without needing to manually build, push, and deploy images.

[skaffold.yaml](././skaffold.yaml)

---

## Why This Setup? — Thought Process & Decisions

This project was designed with **learning and experimentation in mind** rather than production readiness.  
Here’s the reasoning behind the main decisions:

### Monorepo with Modules
- **Decision**: Keep all services in a single repository with a parent Maven POM.
- **Reasoning**:  
  - Easier for a hobby project to manage versions and dependencies in one place.  
  - Shared code (DTOs, utilities, security logic) is centralized in the `common/` module. -> Still TODO 
  - Avoids the complexity of multi-repo setups while still practicing modular design.

### Skaffold + Kubernetes
- **Decision**: Use Skaffold for Kubernetes orchestration.
- **Reasoning**:  
  - Skaffold removes friction by automating builds and deployments.  
  - Perfect for local development with Minikube or Docker Desktop.  
  - Enables live reloading of services after code changes.  
  - Keeps me close to production-like workflows without a full CI/CD pipeline yet.

### Two Initial Services
- **Decision**: Start with `question-service` and `quiz-service`.
- **Reasoning**:  
  - Small, clear domain boundaries to explore microservices interactions.  
  - `question-service` handles CRUD for questions.  
  - `quiz-service` consumes questions to create quizzes.  
  - Allows practicing RESTful communication and potentially service discovery.

### Future: Spring AI Integration
- **Decision**: Leave a placeholder for an `service-ai/` module.
- **Reasoning**:  
  - AI integration is a later goal.  
  - Designing early for modular extension avoids major refactoring later.  
  - Keeps the project motivating and future-proof for experimentation.
---

## Running the Project Locally

- prerequisites and assumptions:
  - docker and kubectl are running on your machine
  - skaffold is installed on your machine

1. **Run the build script**
   ```bash
   /bin/bash ./build.sh
   ```

2. **Access services**
   - Use `kubectl get svc` to get the NodePort or LoadBalancer URLs.


3. **Running the kubernetes jobs**
   ```bash
   # Deleting all data from the data base
   kubectl apply -f k8s/jobs/questiondb-wipe-job.yaml
   # Inserting some questions into the database
   kubectl apply -f k8s/jobs/questiondb-seed-job.yaml
   
   # Checking job logs:
   # kubectl logs job/questiondb-wipe
   # kubectl logs job/questiondb-seed      
   ```
---

## License
This project is for educational purposes and is not intended for production use.
