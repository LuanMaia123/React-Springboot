### Tecnologias ultilizadas
- Spring Boot 2
- Project Lombok
- ReactJs
- React Bootstrap
- React Fontawesome
- Java
- Maven
- Postgresql

# Como executar

## Postgres:
Criar um banco local seguindo esses dados:
- Host:localhost
- Database:postgres
- username=postgres
- password=admin

No application.properties foi deixado o dll como update para criação da tabela. Vale ressaltar que para uma aplicação em produção isso não deve ser usado, normalmente qualquer coisa feita no banco é na mão, ou com migrations como Flyway. Aqui para este desafio mantive desta forma para facilitar os testes.

## Eclipse ou Intellij:
- Importar o projeto maven.
- mvn clean.
- mvn install.
- Verificar se a porta 8080 não esá em uso.
- Rodar a aplicação.

## React:
No terminal deve-se ir até a pasta webapp\front-desafio do projeto e rodar os seguintes comandos:
- Rodar o comando npm install.
- Rodar o comando npm start.

## O que poderia ter sido feito a mais:

- Docker compose para subir tudo de forma automática.
- Migrations para criação da tabela de visualizações.
- Testes Unitários
- Usado redux para controle de estado
- Melhorar a interface e load entre carregamentos
