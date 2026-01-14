# challenge-forum-hub
Projeto desenvolvido no desafio Alura + Oracle One com o objetivo de praticar o desenvolvimento de APIs REST em Java utilizando Spring Boot, aplicando boas práticas, persistência com banco de dados relacional, autenticação e autorização com Spring Security e JWT.
### O que o projeto faz?
Este projeto implementa uma API REST de um fórum online, permitindo o cadastro e gerenciamento de tópicos de discussão, com controle de acesso baseado em autenticação via JWT.
Ele permite:
- Cadastrar novos tópicos (título, mensagem, status, autor e curso).
- Listar todos os tópicos cadastrados.
- Consultar os detalhes de um tópico específico pelo ID.
- Atualizar tópicos existentes.
- Excluir tópicos existentes.
- Registrar usuários.
- Autenticar usuários.
- Gerar e validar tokens JWT.
- Proteger endpoints com Spring Security.
A interação com a API é feita via ferramentas como Postman ou Insomnia, utilizando requisições HTTP.
### Como rodar o projeto
**1.** Pré-requisitos
- Java JDK 17 ou superior.
- Maven 4 ou superior.
- MySQL 8 ou superior.
- IDE ou editor de código (VS Code, IntelliJ).
**2.** Configuração do banco de dados
Crie o banco de dados no MySQL:
```text
CREATE DATABASE forumhub;
```
No arquivo src/main/resources/application.properties, configure:
```text
spring.datasource.url=jdbc:mysql://localhost:3306/forumhub?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=SUA_SENHA

spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

spring.flyway.enabled=true

jwt.secret=forumhub-secret-123
jwt.expiration=3600000
```
Substitua SUA_SENHA pela senha do seu MySQL.
Altere jwt.secret se desejar uma chave diferente para o JWT.

**3.** Estrutura do projeto

Certifique-se de que o projeto está organizado assim:
```text
forumhub
├── pom.xml
│
└── src
    └── main
        ├── java
        │   └── com
        │       └── forumhub
        │           ├── ForumhubApplication.java
        │           │
        │           ├── config
        │           │   ├── SecurityConfigurations.java
        │           │   └── JWTFilter.java
        │           │
        │           ├── controller
        │           │   ├── TopicoController.java
        │           │   └── AuthController.java
        │           │
        │           ├── dto
        │           │   ├── TopicoDTO.java
        │           │   └── LoginDTO.java
        │           │
        │           ├── model
        │           │   ├── Topico.java
        │           │   └── Usuario.java
        │           │
        │           ├── repository
        │           │   ├── TopicoRepository.java
        │           │   └── UsuarioRepository.java
        │           │
        │           └── service
        │               └── TokenService.java
        │
        └── resources
            ├── application.properties
            └── db
                └── migration
                    ├── V1__create_table_topico.sql
                    └── V2__create_table_usuario.sql
```
**4.** Executando o projeto
No terminal, dentro da pasta raiz do projeto, execute:
```text
mvn clean compile
mvn spring-boot:run
```
A aplicação iniciará o servidor Spring Boot em http://localhost:8080.
### Observações
- Senhas de usuários são armazenadas de forma segura utilizando BCrypt.
- Todos os dados são validados antes da persistência.
- O projeto foi desenvolvido utilizando Spring Boot 3.
- O banco de dados utilizado é MySQL, mas a aplicação pode ser adaptada para outros bancos relacionais, como PostgreSQL.
- O projeto segue o padrão REST e boas práticas de organização de código.
