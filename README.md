# 💳 API de Pagamentos - ToolsChallenge

![Java](https://img.shields.io/badge/Java-11-orange?style=for-the-badge&logo=java)
![Spring](https://img.shields.io/badge/Spring_Framework-4.0.5-green?style=for-the-badge&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-42335B?style=for-the-badge&logo=postgresql)

Esta é uma API RESTful robusta desenvolvida para o processamento, estorno e gestão de transações financeiras. O projeto foi construído focando em **extensibilidade**, **testabilidade** e **limpeza de código**, servindo como demonstração de boas práticas em Java.

---

## 🛠️ Tecnologias e Ferramentas

* **Core**: Java 21 & Spring Framework 4.0.5
* **Persistência**: Spring Data JPA com PostgreSQL
* **Produtividade**: Lombok
* **Documentação**: Swagger (OpenAPI 3)
* **Qualidade**: JUnit 4 & Mockito (Testes Unitários e de Integração)
* **Serialização**: Jackson (com suporte para Java 8 Time API)

---

## 🏗️ Arquitetura e Design Patterns

A aplicação aplica princípios do **SOLID** e padrões de projeto para garantir um sistema escalável:

* **Strategy Pattern (Processadores de Pagamento)**: Utilizado para gerir diferentes tipos de transações (`AVISTA`, `PARCELADO_LOJA`, etc.). Isso permite adicionar novos métodos de pagamento criando apenas novas classes de regra de negócio, sem alterar o código core (Princípio Aberto/Fechado).
* **Camada de DTOs**: Isolamento entre a camada de persistência (Entity) e a camada de exposição (Request/Response), garantindo segurança e flexibilidade contratual.
* **Tratamento Global de Exceções**: Uso de `@RestControllerAdvice` para padronizar os retornos de erro HTTP, facilitando a integração com o front-end.
* **Injeção de Dependências**: Implementada via construtores para facilitar o teste de unidade e garantir a integridade dos serviços.

---

## 🚀 Endpoints Principais

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/pagamentos/autorizar` | Processa e autoriza um novo pagamento. |
| `GET` | `/pagamentos/{id}/estorno` | Realiza o estorno de uma transação autorizada. |
| `GET` | `/pagamentos/buscar/{id}` | Consulta os detalhes de um pagamento específico. |
| `GET` | `/pagamentos/listartodos` | Lista o histórico completo de transações. |

---

## ⚙️ Como Executar

### Pré-requisitos
* Java 8 ou superior
* Maven 3.x
* PostgreSQL rodando localmente ou em container

### Passo a Passo
1. **Clone o repositório**:
   ```bash
   git clone [https://github.com/robertThallisson/ToolsChallenge](https://github.com/robertThallisson/ToolsChallenge)
