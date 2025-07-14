# 🚀 ForumHub

![Badge Spring](https://img.shields.io/badge/Spring%20Boot-3.0.0-brightgreen?logo=springboot)
![Badge Java](https://img.shields.io/badge/Java-17-orange?logo=java)
![Badge Build](https://img.shields.io/badge/build-passing-brightgreen)
![Badge Status](https://img.shields.io/badge/status-Em%20desenvolvimento-yellow)
![Badge Coverage](https://img.shields.io/badge/coverage-100%25-success)

## 💬 Sobre o projeto

O **ForumHub** é uma API REST desenvolvida em Java com Spring Boot, que simula o backend de um fórum online, permitindo a criação, atualização, listagem e exclusão de tópicos.  
O projeto utiliza autenticação JWT para garantir segurança e segue as boas práticas de arquitetura.  

Projeto desenvolvido no curso **Java e Spring Framework G8** pelo programa Oracle Next Education (ONE) em parceria com a Alura.

---

## ✨ Funcionalidades

✅ Cadastro de usuários  
✅ Login e autenticação via JWT  
✅ Criação de tópicos  
✅ Atualização e exclusão de tópicos (apenas pelo autor)  
✅ Listagem de tópicos com detalhes  
✅ Relacionamento com cursos  
✅ Segurança baseada em tokens  
✅ Migrações automatizadas com Flyway  

---

## ⚙️ Tecnologias e ferramentas

- **Java 17**
- **Spring Boot 3**
- Spring Security
- Spring Data JPA
- JWT (JSON Web Token)
- Flyway
- MySQL
- Maven
- Insomnia/Postman
- IntelliJ IDEA

---

## 🔒 Segurança

A API utiliza autenticação baseada em JWT, garantindo que apenas usuários autenticados possam interagir com as rotas protegidas.  
O token é gerado ao realizar login e deve ser enviado no header `Authorization` nas demais requisições.

---

## 🗄️ Migrations

O projeto utiliza Flyway para controle de versionamento do banco.  
As migrations criam as tabelas `usuarios`, `topicos` e `cursos`, além de gerenciar alterações de colunas ao longo do tempo.

---

## 🚩 Como rodar localmente

```bash
# Clone o repositório
git clone https://github.com/JuRSouza/forumhub.git

# Acesse a pasta do projeto
cd forumhub

# Configure seu banco de dados no application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/forum_hub
spring.datasource.username=SEU_USER
spring.datasource.password=SUA_SENHA

# Rode as migrations e inicie a aplicação
./mvnw spring-boot:run

```
---

| Método | Endpoint        | Descrição                           |
| ------ | --------------- | ----------------------------------- |
| POST   | `/login`        | Autentica o usuário e retorna token |
| POST   | `/topicos`      | Cria um novo tópico                 |
| GET    | `/topicos`      | Lista os tópicos cadastrados        |
| PUT    | `/topicos/{id}` | Atualiza um tópico específico       |
| DELETE | `/topicos/{id}` | Remove um tópico específico         |

---

## ⚠️ Para acessar as rotas protegidas, enviar o header:
Authorization: Bearer SEU_TOKEN

---

## ✍️ Autora
Desenvolvido com ❤️ por Juliana Rodrigues de Souza.




