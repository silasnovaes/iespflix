# 🎬 IESPFLIX API - Plataforma de Streaming

![Java](https://img.shields.io/badge/Java-21-007396?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2+-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2_Database-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black)

O **IESPFLIX** é uma API RESTful de alta performance desenvolvida para gerenciar o back-end de uma plataforma de streaming de vídeos. O projeto adota princípios rigorosos de **Clean Code**, **Fail-Fast Validations** e arquitetura em camadas orientada ao domínio, garantindo resiliência, escalabilidade e manutenibilidade.

---

## 🏛️ Arquitetura e Padrões de Projeto

A solução foi projetada com foco no isolamento de responsabilidades e proteção do estado da aplicação:

* **Blindagem de Entrada (DTOs & Records):** Uso intensivo de `records` nativos do Java 21 para garantir a imutabilidade no tráfego de dados (Request/Response).
* **Mapeamento Automatizado:** Tradução de DTOs para Entidades delegada ao **MapStruct**, garantindo alta performance em tempo de compilação sem reflexão excessiva (Zero Magic).
* **Validações Fortes (Custom Constraints):** Implementação de regras matemáticas e de domínio através de anotações próprias (`@CpfCnpjValid`, `@EnumSubset`), interceptando anomalias antes do processamento.
* **Tratamento de Exceções Global:** Implementação rigorosa da **RFC 7807 (Problem Details)** via `@RestControllerAdvice`, padronizando as respostas de erro para os consumidores da API.
* **Integração Externa Resiliente:** Consumo de APIs governamentais abstraído através do **Spring Cloud OpenFeign**.

---

## 🚀 Funcionalidades Principais

* **Catálogo de Conteúdo:** Gerenciamento completo (CRUD) de filmes e séries, com carga inicial automatizada via script SQL.
* **Gestão de Usuários:** Cadastro blindado com validação de unicidade (E-mail, CPF/CNPJ) e criptografia simulada de senhas (BCrypt).
* **Enriquecimento de Endereço Automático:**
  * Prioridade 1: Autopreenchimento de logradouro e cidade via CEP (Integração **ViaCEP**).
  * Prioridade 2: Descoberta de Estado e Região com base no DDD (Integração **BrasilAPI**).
* **Assinaturas e Pagamentos:** Relacionamento transacional entre métodos de pagamento tokenizados e planos de assinatura ativos.
* **Engajamento:** Sistema de marcação e listagem de Filmes Favoritos por usuário utilizando chaves compostas no banco de dados.

---

## 🛠️ Stack Tecnológica

| Componente | Tecnologia / Biblioteca |
| :--- | :--- |
| **Linguagem Core** | Java 21 LTS |
| **Framework Base** | Spring Boot (Web, Data JPA, Validation) |
| **Banco de Dados** | H2 Database (In-Memory) para homologação ágil |
| **Integrações (REST Client)** | Spring Cloud OpenFeign |
| **Mapeamento de Objetos** | MapStruct |
| **Documentação da API** | Springdoc OpenAPI 3 (Swagger UI) |
| **Utilitários** | Lombok |

---

## 💻 Como Executar o Projeto Localmente

### Pré-requisitos
* **JDK 21** instalado e configurado nas variáveis de ambiente.
* Porta `8080` liberada no seu sistema operacional.
* O processo de build está configurado para o ambiente local (IntelliJ IDEA recomendado).

### Passo a Passo

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/iespflix.git](https://github.com/seu-usuario/iespflix.git)
   cd iespflix

   Limpe e compile a aplicação:
(Este comando destrava diretórios e recompila os mappers gerados pelo MapStruct)

Bash
./gradlew clean build -x test
Inicie o Servidor:

Bash
./gradlew bootRun
📚 Documentação Interativa e Testes
A API é 100% autodocumentada. Após iniciar a aplicação, acesse a interface do Swagger pelo navegador para explorar os endpoints, visualizar os schemas JSON esperados e executar requisições em tempo real:

👉 Acesso ao Swagger UI: http://localhost:8080/swagger-ui.html

Carga de Dados Inicial
A aplicação está configurada com defer-datasource-initialization: true. Ao iniciar, o script data.sql injeta 10 títulos no catálogo automaticamente. Você pode verificar isso executando a rota GET /api/v1/filmes no Swagger.

🗄️ Acesso ao Banco de Dados (Console H2)
Para inspecionar as tabelas, relacionamentos e os dados gravados pelas integrações externas em tempo real:

Acesse: http://localhost:8080/h2-console

Certifique-se de preencher a URL de conexão corretamente:

JDBC URL: jdbc:h2:mem:iespflixdb

User Name: sa

Password: password

Clique em Connect e utilize comandos SQL padrão para explorar os dados (Ex: SELECT * FROM endereco;).

# IESPFLIX Web + API

- Repositório: https://github.com/silasnovaes/iespflix.git
- Apresentador principal: Silas Novaes de Luna Gomes

## Integrantes
- Integrante 1: Silas Novaes de Luna Gomes — Usuários e autenticação
- Integrante 2: Silas Novaes de Luna Gomes — Conteúdos e filtros
- Integrante 3: Silas Novaes de Luna Gomes — Favoritos e assinaturas
- Integrante 4: Silas Novaes de Luna Gomes — Integrações externas e validações
- Integrante 5: Silas Novaes de Luna Gomes — Frontend, documentação e apresentação
