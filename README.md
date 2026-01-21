# API de Cupons (Coupon API)

Este projeto é uma aplicação Spring Boot para gerenciamento de cupons de desconto. Abaixo estão as instruções para rodar o projeto em diferentes ambientes.

---

## 🚀 Rodando com Docker (Ambiente Persistente)

Para rodar esta aplicação via Docker, basta ter o **Docker** e o **Docker Compose** instalados em sua máquina.

1. No terminal, entre na raiz do projeto.
2. Execute o seguinte comando:
   ```bash
   docker-compose up --build
📍 Pontos de Acesso:
Documentação API (Swagger): http://localhost:8080/swagger-ui/index.html#/

Banco de Dados: Este ambiente utiliza um container PostgreSQL para armazenamento persistente dos dados.

🛠️ Desenvolvimento Local (Testes Rápidos)
Para testes mais rápidos ou desenvolvimento sem a necessidade de subir containers, você pode utilizar o banco de dados em memória.

Execute a classe Main do projeto através da sua IDE (IntelliJ, Eclipse, VS Code).

O projeto utilizará o perfil padrão com banco de dados H2.

📍 Pontos de Acesso:
Documentação API (Swagger): http://localhost:8081/swagger-ui/index.html#/

Console H2: http://localhost:8081/h2-console
