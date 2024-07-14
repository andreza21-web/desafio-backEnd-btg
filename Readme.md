
<p align="center" width="100%">
    <img width="50%" src="images/btg-logo.jpg"> 
</p>

<h3 align="center">
  Desafio Backend do BTG Pactual
</h3>

<p align="center">

  <img alt="License: MIT" src="https://img.shields.io/badge/license-MIT-%2304D361">
  <img alt="Language: Java" src="https://img.shields.io/badge/language-java-green">
  <img alt="Version: 1.0" src="https://img.shields.io/badge/version-1.0-yellowgreen">

</p>

##  Tecnologias utilizadas

* Java 21
* Spring Boot
* Spring Data MongoDB
* RabbitMQ
* Docker

## Ferramentas utilizadas
* Vscode extensão do MongoDb (Para visualizar a tabela criada)
* Insomnia 
* Intellij IDEA

### Como rodar o container

Entrar na pasta local `cd local` rodar o comando
` docker compose up`

## Enviando um pedido atraves do RabbitMq

Publicando uma mensagem para ser consumida atraves de um microsserviço

Acessando o site 

http://localhost:15672/#/queues/%2F/btg-pactual-order-created

Dentro de `Publish Message`, é feita a publicação de uma mensagem.

```json
   {
       "codigoPedido": 1002,
       "codigoCliente":1,
       "itens": [
           {
               "produto": "notebook",
               "quantidade": 1,
               "preco": 2300.10
           },
           {
               "produto": "mouse",
               "quantidade": 1,
               "preco": 250.00
           }
       ]
   }
```  
## Consumindo Serviço do RabbitMq
### Requisição

Para buscar informações de um usuário específico, faça uma requisição GET para:

 http://localhost:8080/customers/1/orders

### Retorno da chamada

 ```json
{
	"summary": {
		"totalOnOrders": 2670.10
	},
	"data": [
		{
			"orderId": 1001,
			"customerId": 1,
			"total": 120.00
		},
		{
			"orderId": 1002,
			"customerId": 1,
			"total": 2550.10
		}
	],
	"pagination": {
		"page": 0,
		"pageSize": 10,
		"totalElements": 2,
		"totalPages": 1
	}
}
```