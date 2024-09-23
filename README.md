
# Venda ingressos

Um projeto de microserviço simulando um projeto de vendas

## SWAGGER

Para utilizar o swager, basta utilizar essa url http://localhost:8080/swagger-ui/index.html

## API Reference

#### Post comprando ingresso.

```http
  POST /sales
```

| Parameter         | Type     | Description                                         |
|:------------------|:---------|:----------------------------------------------------|
| `nameOfSession`   | `string` | **Required**. Nome da sessão que irá assistir       |
| `clients`         | `List`   | **Required**. Dados do(s) cliente(s)                |
| `numberOfTickets`          | `string` | **Required**. Quantidade de tickets que irá comprar |

#### Get que busca todas as vendas já feitas, de acordo com a quantidade passada.

```http
  GET /sales
```

| Parameter | Type   | Description                                                                          |
|:----------|:-------|:-------------------------------------------------------------------------------------|
| `size`    | `Int`  | **Required**. Quantidade de vendas que irá trazer.                                   |
| `offset`  | `Int` | **Required**. Qual a pagina que vai estar, ex: 10 vendas pagina 1, 10 vendas pagina 2. |


#### Post cadastra a sessão que será vendida.

```http
  POST /sessions
```

| Parameter         | Type     | Description                                                            |
|:------------------|:---------|:-----------------------------------------------------------------------|
| `name`   | `string` | **Required**. Nome da sessão que irá assistir.                         |
| `quantityTickets`         | `List`   | **Required**. Quantidade de tickets que estaram a venda para a sessão. |
| `datePresentation`          | `string` | **Required**. Data da apresentação                                     |
| `valueOfTickets`          | `string` | **Required**. Valor dos tickets                                        |


#### Get que busca todas as sessões já cadastradas.

```http
  GET /sessions
```

| Parameter | Type   | Description                                                                          |
|:----------|:-------|:-------------------------------------------------------------------------------------|
| `size`    | `Int`  | **Required**. Quantidade de sessões que irá trazer.                                   |
| `offset`  | `Int` | **Required**. Qual a pagina que vai estar, ex: 10 sessões pagina 1, 10 sessões pagina 2. |


#### Get que busca todos os clientes já cadastrados.

```http
  GET /clients
```

| Parameter | Type   | Description                                                                          |
|:----------|:-------|:-------------------------------------------------------------------------------------|
| `size`    | `Int`  | **Required**. Quantidade de clientes que irá trazer.                                   |
| `offset`  | `Int` | **Required**. Qual a pagina que vai estar, ex: 10 clientes pagina 1, 10 clientes pagina 2. |

## Installation

Para utilizar o projeto, deverá ter o kafka instalado na maquina: [Download](https://kafka.apache.org/downloads)

Após instalar o kafka, suba o zookeeper e o kafka utilizando os seguintes comandos no TERMINAL do windows.

Iniciando o zookeeper
```bash
  zookeeper-server-start.bat C:\kafka\config\zookeeper.properties
```
Iniciando o kafka
```bash
  kafka-server-start.bat C:\kafka\config\server.properties
```
Após essas etapas, crie um tópico com o nome 'venda-ingressos-teste'

Criação do tópico
```bash
  kafka-topics.bat --bootstrap-server localhost:9092 --create --topic venda-ingressos
```