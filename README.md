
<div align="center">
    <h2 >DaVinci Insights</h2>
</div>

API do Projeto DaVinci Insights - Processamento de dados para análise de feedbacks de clientes

## Integrantes
 
- RM550341 - Allef Santos (2TDSPV) 
- RM551491 - Cassio Yuji Hirassike Sakai 
- RM97836  - Debora Damasso Lopes 
- RM550323 - Paulo Barbosa Neto 
- RM552314 - Yasmin Araujo Santos Lopes

## Competência 

- Allef - Java Advanced

- Cassio Y.H. Sakai - Mobile Application Development

- Debora D. Lopes - Advanced Business Development With .NET e Disruptive Architectures: IOT, IOB & GENERATIVE IA 

- Paulo B. Neto - Mastering Relational AND Non-Relational DataBase

- Yasmin A. S. Lopes- Compliance, Quality Assurance & Tests e DevOps Tools & Cloud Computing


## Objetivo do Projeto

O Projeto tem como objetivo desenvolver um sistema avançado para análise de feedbacks de clientes. A proposta central é permitir que os clientes expressem suas opiniões sobre produtos específicos após a compra e, em seguida, integrar esses feedbacks com outros já existentes. Dessa forma, o sistema visa extrair insights valiosos para ajudar as empresas a compreenderem as razões subjacentes às avaliações, sejam elas positivas, negativas, neutras ou ausentes. Essa abordagem proporcionará uma compreensão mais profunda do que os clientes pensam e sentem sobre os produtos, possibilitando que as empresas ajam de forma mais informada e eficaz para atender às necessidades e expectativas do mercado.

O Público-alvo do Projeto DaVinci Insights visa atender empresas que buscam aprimorar seu relacionamento com os clientes, compreendendo suas percepções sobre produtos específicos. Ao entender essas opiniões, as empresas podem identificar e resolver os problemas que enfrentam no mercado, permitindo uma abordagem mais focada e eficaz para atender às necessidades do cliente.

## Vídeo da demonstração do Projeto
<p align="center"> <a href="https://youtube.com/playlist?list=PLtsjpUU5tm1qfAoukNWXR5zeW70yDuWuG&si=gn4i4eYxNMThi0nG">Vídeo da demonstração e testes do projeto</a></p>

# API-DAVINCI-INSIGHTS - Guia de Deploy

Este guia explica como clonar o repositório da API-DAVINCI-INSIGHTS, configurar o ambiente e fazer o deploy da aplicação no Azure. Ele também aborda possíveis erros que podem ocorrer durante o processo de build e como solucioná-los.

## Pré-requisitos

- Conta no GitHub
- Conta no Azure com permissões para criar e gerenciar Web Apps
- Maven instalado
- Java 17

## Clonando o Repositório

Primeiro, clone o repositório para a sua máquina local:

```bash
git clone https://github.com/yaslopesyweb/API-DAVINCI-INSIGHTS-teste.git
cd API-DAVINCI-INSIGHTS-teste
```

## Configurando o Build com Maven
Durante o processo de build, é possível que ocorra uma falha devido ao caminho do diretório onde o pom.xml está localizado. Certifique-se de adicionar as seguintes instruções ao arquivo de workflow do GitHub Actions (localizado em .github/workflows):

```yaml
// .github/workflows
# Mude para o diretório onde o pom.xml está localizado
- name: Build with Maven
  working-directory: 'Challenge_JavaV.3/insights'
  run: mvn clean install

# Verifique os arquivos gerados no diretório target
- name: List files in target directory
  run: ls -la Challenge_JavaV.3/insights/target

- name: Upload artifact for deployment job
  uses: actions/upload-artifact@v3
  with:
    name: java-app
    path: 'Challenge_JavaV.3/insights/target/*.jar'

    }
```
Essas instruções devem ser adicionadas logo após a configuração da versão do Java e distribuição, garantindo que o Maven execute o build corretamente no diretório certo.

## Configurando Variáveis do Azure no GitHub
Para que o GitHub Actions consiga se autenticar e realizar o deploy no Azure, é necessário configurar três variáveis de ambiente no repositório do GitHub: AZURE_CLIENT_ID, AZURE_TENANT_ID e AZURE_SUBSCRIPTION_ID.

### Passo a Passo para Configurar Variáveis
No GitHub, vá para o repositório onde a aplicação está hospedada.

Acesse Settings > Secrets and variables > Actions.
Clique em New repository secret.
Adicione as seguintes variáveis com os respectivos valores do Azure:
#### AZURE_CLIENT_ID: O ID do aplicativo registrado no Azure AD.
#### AZURE_TENANT_ID: O ID do diretório (tenant) do Azure AD.
#### AZURE_SUBSCRIPTION_ID: O ID da assinatura do Azure onde o serviço será implantado.
Salve cada variável após inserir os valores.

## Deploy da API
Esta aplicação é uma API e, após o deploy, você pode acessar a documentação e realizar testes utilizando o Swagger. Para isso, utilize o seguinte caminho: /swagger-ui/index.html no final de seu domínio criado na azure.

## Exemplo do domínio com o Deploy já efetuado:
https://api-davinci-insights.azurewebsites.net/swagger-ui/index.html 


## Requisitos

- [ ] CRUD Cliente
- [ ] CRUD Opnião
- [ ] CRUD Produto 


## Documentação da API

### Endpoints

- [Registar Cliente](#cadastrar-cliente)
- [Login Cliente](...)
---

- [Cadastrar Opnião](#cadastrar-opnião)
- [Cadastrar Produto](#cadastrar-produto)
---
- [Listar Clientes](#listar-clientes)
- [Listar Opniões](#listar-opniões)
- [Listar Produtos](#listar-produtos)
---
- [Detalhar Cliente](#detalhar-cliente)
- [Detalhar Opnião](#detalhar-opnião)
- [Detalhar Produto](#detalhar-produto)


#### Códigos de Status [status code]

|código|descrição
|------|---------
|200|Autenticação bem sucedida
|404|Produto não encontrado

----------------------------------------

### Registar Cliente

`POST` /auth/register

registar um cliente com os dados enviados no corpo da requisição.

#### Corpo da Requisição

| campo |tipo|obrigatório|descrição
|-------|----|:-----------:|---------
| nome  |String|✅| nome do Cliente
| login |Integer|✅| login do Cliente
| password |String|✅| password do Cliente

---

#### Exemplo de Requisição
```js
// Post /auth/register
    {
        "nome": "Yasmin",
        "login": "yasmn.22@gmail.com",
        "password": "12345678"
    }
```

#### Exemplo de Resposta
```js
{
        "id": 3102,
        "nome": "Yasmin",
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJsb2dpbi1hdXRoLWFwaSIsInN1YiI6Inlhcy4" +
                  "yMkBnbWFpbC5jb20iLCJleHAiOjE3MjYyOTEwNDd9.t63kToZPBhOLMG3XDnhEG3TthCC3E8EuK7DzG9DzCBE"
}
}
```

#### Código de Status

|código|descrição
|------|---------
|201| Cliente cadastrado com sucesso
|400| Validação falhou. Verifique o corpo da requisição
----------------------------------------
### Login Cliente

`POST` auth/login

registar um cliente com os dados enviados no corpo da requisição.

#### Corpo da Requisição

| campo |tipo|obrigatório|descrição
|-------|----|:-----------:|---------
| nome  |String|✅| nome do Cliente
| login |Integer|✅| login do Cliente
| password |String|✅| password do Cliente

---

#### Exemplo de Requisição
```js
// Post auth/login
    {
        "login": "yas.22@gmail.com",
        "password": "12345678"
    }
```

#### Exemplo de Resposta
```js
{

        "id": 3102,
        "nome": "Yasmin",
        "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9." +
    "eyJpc3MiOiJsb2dpbi1hdXRoLWFwaSIsInN1YiI6Inlhcy" +
    "4yMkBnbWFpbC5jb20iLCJleHAiOjE3MjYyOTEwNjF9." +
    "S6-iuzVcyQ6hExBbtFQ_iBfhjfwqFFRdDvLVw5XLKz4"
}
```

#### Código de Status

|código|descrição
|------|---------
|201| Cliente cadastrado com sucesso
|400| Validação falhou. Verifique o corpo da requisição
----------------------------------------

### Cadastrar Opnião

`POST` /opiniao

Cadastre uma opinião com os dados enviados no corpo da requisição.

#### Corpo da Requisição

|campo|tipo|obrigatório|descrição
|-----|----|:-----------:|---------
|comentario|String|✅| Opinião do Cliente

---

#### Exemplo de Requisição
```js
// Post /opiniao
    {
        "comentario": "Excelente tecido!"
    }
```

#### Exemplo de Resposta
```js
{
    "id": 1,
    "comentario": "Excelente tecido!"
}
```

#### Código de Status

|código|descrição
|------|---------
|201| Opnião cadastrada com sucesso
|400| Validação falhou. Verifique o corpo da requisição

----------------------------------------

### Cadastrar Produto

`POST` /produto

Cadastre um produto com os dados enviados no corpo da requisição.

#### Corpo da Requisição

|campo|tipo|obrigatório|descrição
|-----|----|:-----------:|---------
|nome|String|✅| Nome do produto
|valor|BigDecimal|✅| Valor do produto

---

#### Exemplo de Requisição
```js
// Post /produto
    {
        "nome": "Camisa",
        "valor": 29.90
    }
```

#### Exemplo de Resposta
```js
{
    "id": 1,
    "nome": "Camisa",
    "valor": 29.90
}
```

#### Código de Status

|código|descrição
|------|---------
|201| Produto cadastrado com sucesso
|400| Validação falhou. Verifique o corpo da requisição

----------------------------------------
### Listar Clientes

`GET` /cliente : Retorna os dados do cliente

#### Exemplo de Resposta

```js
[
    {
        "id": 3102,
        "nome": "Yasmin",
        "login": "yas.22@gmail.com",
        "password": "$2a$10$52f7oCVImUDm91AqQoOoyeRW0Ge29AIkOo2d6nBdRo.SAq5Yx6qqO"
    }
    }
]
```
#### Códigos de Status [status code]

|código|descrição
|------|---------
|200|Autenticação bem sucedida
|404|Cliente não encontrado

----------------------------------------

### Listar Opniões

`GET`/opiniao : Retorna o comentario do cliente sobre o Produto

#### Exemplo de Resposta

```js
[
    {
        "id": 53,
        "comentario": "Excelente tecido!"
    }
]
```
#### Códigos de Status [status code]

|código|descrição
|------|---------
|200|Autenticação bem sucedida
|404|Opnião não encontrado

----------------------------------------

### Listar Produtos

`GET`/Produto : Informações do produto

#### Exemplo de Resposta

```js
[
    {
        "id": 1,
        "nome": "Camisa",
        "valor": 29.90
    }
]

```


----------------------------------------

### Detalhar Cliente

`GET` /cliente/`{id}`

Retorna os detalhes do cliente com o `id` informado no path.

#### Exemplo de Resposta
```js
// GET /cliente/3102
{
    "id": 3102,
    "nome": "Yasmin",
    "login": "yas.22@gmail.com",
    "password": "$2a$10$52f7oCVImUDm91AqQoOoyeRW0Ge29AIkOo2d6nBdRo.SAq5Yx6qqO"
}
}
```

#### Códigos de Status

|código|descrição
|------|---------
|200| Cliente retornado com sucesso
|404| Não existe cliente com o `id` informado

---

### Detalhar Opnião

`GET` /opiniao/`{id}`

Retorna os detalhes da opinião com o `id` informado no path.

#### Exemplo de Resposta
```js
// GET /opiniao/53
{
    "id": 53,
    "comentario": "Excelente tecido!"
}
```

#### Códigos de Status

|código|descrição
|------|---------
|200| Opnião retornada com sucesso
|404| Não existe opnião com o `id` informado

---

### Detalhar Produto

`GET` /produto/`{id}`

Retorna os detalhes do produto com o `id` informado no path.

#### Exemplo de Resposta
```js
// GET /produto/1
{
    "id": 1,
    "nome": "Camisa",
    "valor": 29.90
}
```

#### Códigos de Status

|código|descrição
|------|---------
|200| Produto retornado com sucesso
|404| Não existe produto com o `id` informado

---
