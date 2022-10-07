# Desafio Spring

<br />

## Objetivo 🚀

O objetivo deste sprint é aplicar os conteúdos fornecidos até o momento durante o BOOTCAMP MeLi (Git, Java e Spring), para poder implementar uma API REST a partir de uma declaração proposta, uma especificação de requisitos e documentação anexada.

<br />

## Funcionalidades 🔧

1. Cadastrar uma lista de produtos.
2. Retornar uma lista de todos os produtos disponíveis.
3. Retornar uma lista de produtos filtrados por diferentes tipos de filtros.
4. Enviar pedido de compra.
5. Criar um carrinho de compras.
6. Cadastrar novos clientes.
7. Listar todos os clientes.
8. Filtrar clientes por Estado.

<br />

## End-Points ➡️
***URL BASE:*** ```/api/v1```

<br />

### Produtos GET

| Ação | End-Point |
|---------|---------|
| Listar todos os produtos  |  ```/articles``` |
| Listar produtos ordenados pelo **menor** preço  |  ```/articles?order=3``` |
| Listar produtos ordenados pelo **maior** preço  |  ```/articles?order=2``` |
| Listar produtos por **ordem alfabética**  |  ```/articles?order=0``` |
| Listar produtos por **ordem alfabética reversa**  |  ```/articles?order=1``` |
| Filtrar produtos por **categoria** e **frete** | ```/articles?category=CategoryName&freeShipping=true``` |
| Filtrar produtos por **categoria** e **frete** ordenados por **ordem alfabética** | ```/articles?category=CategoryName&freeShipping=true&order=0``` |
| Filtrar produtos por **categoria** e **frete** ordenados por **ordem alfabética reversa** | ```/articles?category=CategoryName&freeShipping=true&order=1``` |
| Filtrar produtos por categoria e frete ordenados pelo maior preço | ```/articles?category=CategoryName&freeShipping=true&order=2``` |
| Filtrar produtos por categoria e frete ordenados pelo menor preço | ```/articles?category=CategoryName&freeShipping=true&order=3``` |
| Filtrar produtos por frete e avaliação | ```/articles?freeShipping=true&prestige=****``` |

<br />

### Produtos POST

Para cadastrar uma lista de produtos: ```/insert-articles-request```

<br />

*Deve ser enviado no corpo da requisição um payload de acordo com o exemplo abaixo:*

```
[
    {
        "productId":1,
        "name":"Serra de Bancada",
        "category":"Ferramentas",
        "brand":"FORTGPRO",
        "price":1800.00,
        "quantity":5,
        "freeShipping":true,
        "prestige":"****"
    },
    {
        "productId":2,
        "name":"Furadeira",
        "category":"Ferramentas",
        "brand":"Black & Decker",
        "price":500.00,
        "quantity":7,
        "freeShipping":true,
        "prestige":"****"
    }
]
```

<br />

### Clientes GET

| Ação | End-Point |
|---------|---------|
| Listar todos os clientes  |  ```/clients``` |
| Filtrar clientes por Estado  |  ```/clients?state=ce``` |

<br />

### Clientes POST

Para cadastrar um novo cliente: ```/clients```

<br />

*Deve ser enviado no corpo da requisição um payload de acordo com o exemplo abaixo:*

```
{
    "name" : "João",
    "birthDate" : "22/02/1992",
    "email" : "joao@gmail.com",
    "address" : {
        "street" : "Rua Avenida das Ruas",
        "city" : "Matipó",
        "state" : "SP"
    }
}
```

<br />

### Requisição de Compra

Para fazer uma nova requisição de compra, deve-se fazer uma requisição do tipo POST para o End-Point: ```/purchase-request```

<br />

*Deve ser enviado no corpo da requisição um payload de acordo com o exemplo abaixo:*
```
[
    {
        "productId": 1,
        "quantity": 5
    },
    {
        "productId": 2,
        "quantity": 7
    }
]
```
