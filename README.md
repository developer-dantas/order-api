Aqui está um exemplo de README.md que explica como rodar o seu projeto:

markdown
Copiar código
# Projeto de Pedidos com RabbitMQ e MongoDB

Este projeto é uma aplicação em Java 17 que utiliza RabbitMQ para o envio de mensagens e MongoDB como banco de dados. O ambiente de desenvolvimento é configurado usando Docker Compose, facilitando a inicialização do banco de dados e do RabbitMQ.

## Requisitos

- Java 17
- Docker e Docker Compose
- RabbitMQ (localmente)

## Como rodar o projeto

### Passo 1: Clonar o repositório

Clone o repositório para a sua máquina local:

git clone https://github.com/developer-dantas/order-api.git
cd repository

### Passo 2: Iniciar os containers com Docker Compose
Na raiz do projeto, existe um arquivo docker-compose.yml que configura o MongoDB e o RabbitMQ. Para iniciar os containers, execute o seguinte comando:


docker-compose up
Isso irá:

Inicializar o MongoDB com o banco de dados ORDER_DB.
Inicializar o RabbitMQ.
Observação: Certifique-se de que o RabbitMQ está funcionando corretamente antes de iniciar a aplicação.

### Passo 3: Acessar o RabbitMQ
Para interagir com o RabbitMQ, acesse a interface de administração no navegador, indo até:

Copiar código
http://localhost:15672
O usuário e senha padrão são:

Usuário: guest
Senha: guest

### Passo 4: Publicar uma mensagem na fila order
Antes de iniciar a aplicação, você precisa publicar uma mensagem na fila order do RabbitMQ. Para isso, crie uma mensagem com o seguinte payload:

     {
         "name": "Pedido Rafael",
           "status": "PENDING",
          "products": [
            {
              "name": "Skol",
              "amount": 20,
              "price": 5.5
            },
            {
              "name": "Brahma",
              "amount": 16,
              "price": 6.0
            },
            {
              "name": "Guaraná Antarctica",
              "amount": 10,
              "price": 4.5
            },
            {
              "name": "H2OH",
              "amount": 10,
              "price": 3.5
            },
            {
              "name": "Corona",
              "amount": 25,
              "price": 8.0
            }
          ]
    }

Publique a mensagem na fila order.

### Passo 5: Rodar a aplicação
Agora que o RabbitMQ está configurado e a mensagem foi publicada na fila, você pode rodar a aplicação. Certifique-se de que o ambiente Docker está ativo e execute o seguinte comando para rodar o projeto:

./mvnw spring-boot:run
Ou, caso esteja utilizando Maven de forma global:

mvn spring-boot:run
A aplicação agora está ouvindo a fila order no RabbitMQ. Ela irá processar as mensagens publicadas e interagir com o MongoDB.

### Passo 6: Verificar o MongoDB
O MongoDB foi configurado para criar automaticamente um banco de dados chamado ORDER_DB quando o container for iniciado. Você pode acessar o MongoDB para verificar as informações:

docker exec -it <mongodb_container_id> mongo ORDER_DB
Observação: Substitua <mongodb_container_id> pelo ID ou nome do seu container MongoDB, que pode ser obtido com o comando docker ps.

Parar os containers
Para parar os containers quando terminar, execute o seguinte comando:

docker-compose down
Contribuindo
Sinta-se à vontade para abrir uma issue ou enviar um pull request caso tenha sugestões de melhorias ou correções para o projeto.

### Tecnologias utilizadas:

#### Java 17
#### Spring Boot
#### RabbitMQ
#### MongoDB
#### Docker e Docker Compose
