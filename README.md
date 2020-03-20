# Desafio vibe tecnologia

### Tecnologias ultilizadas
- Spring Boot 2
- Project Lombok
- ReactJs
- React Bootstrap
- React Fontawesome
- Java
- Maven
- Postgresql

Olá, obrigado pela oportunidade de mostrar um pouco do meu conhecimento.

Essa aplicação, é um desafio proposto pela Vibe Tecnologia, mas vai além disso, pois, eu me dafiei também.
Nessa aplicação eu usei a tecnologia ReactJs para fazer as telas, eu comecei a estudar ReactJS dia 12 desse mês e resolvi aplicar o que estou estudando neste desafio, estou acompanhando o curso da RocketSeat, e até o momento é um curso muito bom. Apliquei os conhecimentos que tinha e fui a traz dos que eu não tinha para fazer o front-end da aplicação nessa tecnologia, ainda estou estudando, então é provável que daqui a uma semana eu veja muita coisa que fiz e que posso melhorar.

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
