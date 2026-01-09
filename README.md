# challenge-forum-hub
Projeto desenvolvido no desafio Alura + Oracle One para praticar desenvolvimento de APIs REST em Java com Spring Boot.
### O que o projeto faz?
Este projeto é um fórum online para cadastro e gerenciamento de tópicos de discussão, com autenticação de usuários e controle de acesso via JWT.
Ele permite:
- Cadastrar novos tópicos (título, mensagem, autor e curso).
- Listar todos os tópicos cadastrados.
- Consultar detalhes de um tópico específico pelo ID.
- Atualizar tópicos existentes.
- Excluir tópicos existentes.
- Autenticar usuários para acessar a API.
- Gerar tokens JWT para autenticação e autorização.
O usuário interage com a API utilizando ferramentas como Postman ou Insomnia, enviando requisições HTTP.
### Como rodar o projeto
**1.** Pré-requisitos
- Java 17+ instalado.
- Maven 4+ instalado.
- MySQL 8+ instalado e rodando.
**2.** Configuração do banco
No arquivo src/main/resources/application.properties, configure:
```text
spring.datasource.url=jdbc:mysql://localhost:3306/forumhub?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true

server.port=8080

jwt.secret=UmaSenhaSuperSecreta
jwt.expiration=3600000

logging.level.org.springframework=INFO
logging.level.com.example=DEBUG
```
Substitua sua_senha e UmaSenhaSuperSecreta pelos dados do seu MySQL e chave desejada para geração e validação do token JWT.

**3.** Estrutura do projeto

Certifique-se de que o projeto está organizado assim:
```text
forumhub/
├─ src/main/java/com/forumhub/
│   ├─ ForumHubApplication.java
│   ├─ model/
│   │   ├─ Topico.java
│   │   └─ Usuario.java
│   ├─ dto/
│   │   ├─ LoginDTO.java
│   │   └─ TokenDTO.java
│   ├─ repository/
│   │   ├─ TopicoRepository.java
│   │   └─ UsuarioRepository.java
│   ├─ service/
│   │   ├─ TokenService.java
│   │   └─ UsuarioService.java
│   ├─ controller/
│   │   ├─ TopicoController.java
│   │   └─ AutenticacaoController.java
│   └─ security/
│       └─ SecurityConfigurations.java
├─ src/main/resources/
│   ├─ application.properties
│   └─ db/migration/
│       ├─ V1__Create_Topicos.sql
│       └─ V2__Create_Usuarios.sql
└─ pom.xml
```
**4.** Executando o projeto
No terminal, dentro da pasta do projeto:
```text
mvn clean compile
mvn spring-boot:run
```
A aplicação iniciará o servidor Spring Boot em http://localhost:8080.
Você poderá enviar requisições HTTP para os endpoints da API.
### Observações
- Todos os endpoints de tópicos exigem autenticação via JWT.
- Senhas de usuários são armazenadas de forma segura no banco de dados.
- Migrations SQL estão na pasta src/main/resources/db/migration e serão executadas automaticamente pelo Flyway.
- Teste as funcionalidades utilizando Postman ou Insomnia, enviando o token JWT no cabeçalho Authorization: Bearer <token>.
- O projeto utiliza MySQL, mas pode ser adaptado para outro banco relacional, como PostgreSQL.
- O Spring Boot DevTools está habilitado para hot reload durante o desenvolvimento.
