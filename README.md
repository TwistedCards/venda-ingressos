
# Venda ingressos

Um projeto de microserviço simulando um cinema tendo venda de ingressos

## SWAGGER

Para utilizar o swager, basta utilizar essa url http://localhost:8080/swagger-ui/index.html

## DER
![img.png](docs/der.PNG)

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