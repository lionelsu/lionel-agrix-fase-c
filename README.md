# AGRIX
Dois empreendedores preocupados com os impactos socioambientais do agronegócio decidiram criar a AgroTech, empresa especializada em tecnologias para melhorar a eficiência no cultivo de platações. Visando reduzir o desperdício de recursos e alimentos, fazendo um uso mais responsável da terra disponível para plantio. Seu primeiro produto foi nomeado de Agrix, um sistema que permitirá a gestão e o monitoramento das fazendas participantes. 

Esta é a etapa final e completa do projeto.

## Funcionalidades do Projeto
Neste projeto uma aplicação Web em Java com Spring Boot será desenvolvido, com funcionalidades para gestão de fazendas e plantios:
- Implementação o ecossistema Spring para desenvolvimento de rotas de API Rest.
- Aplicaçãoo de injeção de dependência para conectar camadas de controle, serviço e persistência.
- Aplicação do Spring Data JPA para implementar entidades e repositórios para a persistência em banco de dados.
- Implementação de gerenciamento de erros no Spring Web.
- Criação de um Dockerfile execução da aplicação conteinerizada
- Criação de testes unitários.
- Aplicar o Spring Security para adicionar autenticação ao projeto.

## Endpoints da API
Criação de uma API para controle de fazendas com as seguintes rotas:
* `POST /farms`: Cria fazenda.
* `GET /farms/{id}`: Retorna uma fazenda específica.
* `POST /farms/{farmId}/crops`: Cria plantação de uma fazenda específica.
* `GET /farms/{farmId}/crops`: Retorna uma lista de plantações de uma fazenda específica.
* `GET /crops/{id}`: Retorna uma plantação específica.
* `GET /crops/search?start=yyyy-MM-dd&end=yyyy-MM-dd`: Filtra uma plantação a partir da data de sua colheita.
* `POST /fertilizers`: Cria um novo fertilizante.
* `GET /fertilizers/{id}`: Retorna um fertilizante específico.
* `POST /crops/{cropId}/fertilizers/{fertilizerId}`: Associa uma plantação a um fertilizante.
* `GET /crops/{cropId}/fertilizers`: Retorna os fertilizantes associados a uma plantação especifica.

* `POST /persons`: Cria no banco de dados uma nova pessoa, no corpo da requisição deve ser especificado o nome de usuário, senha e papel.
* `POST /auth/login`: Cria um JWT para autenticação de acesso aos usuários do sistema. A requisição deve conter o nome de usuário e senha.
* `GET /farms`: Retorna todas as fazendas cadastradas. Acesso limitado, apenas as pessoas com os papeis USER, MANAGER ou ADMIN podem acessar.
* `GET /crops`: Retorna todas as plantações cadastradas. Acesso limitado, apenas as pessoas com os papeis MANAGER ou ADMIN podem acessar.
* `GET /fertilizers`: Retorna todos os fertilizantes cadastradas. Acesso limitado, apenas as pessoas com o papel ADMIN podem acessar.

## Como Executar o Projeto
1. Clone o repositório.
    ```bash
    git clone git@github.com:lionelsu/lionel-agrix-fase-c.git && cd lionel-agrix-fase-c
    ```

2. Suba o ambiente com Docker Compose.
    * Subir o ambiente:
    ```bash
    docker-compose up -d
    ```
    * Encerrar o ambiente:
    ```bash
    docker-compose down
    ```

3. Execute o Spring Boot.
    ```bash
    mvn spring-boot:run
    ```
