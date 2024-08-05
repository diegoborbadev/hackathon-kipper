<br>

<p align="center">
  <h1 align="center">🌿 Projeto Eco Amigos (Backend)</h1>
  <p align="center">Idealizado e implementado no <i><a href="https://github.com/omidnikrah/github-readme-stackoverflow">Hackathon KipperDev 2024</a></i></p>
</p>

<br>

# Projeto
## Proposta
Desenvolvimento de um aplicativo voltado para crianças a partir de 8 anos, utilizando a familiaridade delas com dispositivos eletrônicos, especialmente celulares. O objetivo do aplicativo é conscientizar sobre os efeitos climáticos, reciclagem, sustentabilidade e outros temas ambientais.

Mais informações: [Documentação do Projeto](https://docs.google.com/document/d/e/2PACX-1vQtuBMFqQ1U8NtH_M-_C7r8FRSy3qpbJkfUGd2VsyXW8csGsKTK8a3OunEjvIfYevETNVbWGCOLy8JD/pub)

## Desenvolvimento
Está é uma *API* desenvolvida utilizando *Java 17* com o framework *SpringBoot 3.3.2* e build da aplicação utilizando *Apache Maven 3.8.7*. 

Para a camada de dados foi utulizado o *PostgreSQL 14.2*, e *H2 Database* para testes unitários. 

E por fim, para a documentação da API foi utilizado o *Swagger 3*, inclusive, o [*Swagger de produção pode ser acessado*](http://165.227.218.157:8080/swagger-ui/index.html#/).

# Executando o projeto
## Pré-requisitos

Para executar o projeto é necessário ter instalado:

Apache Maven:
```bash	
sudo apt install maven
```

e o Java 17:
```bash
sudo apt install openjdk-17-jdk
```

## Clonando o repositório

Primeiro, é preciso clonar o repositório:
```bash
git clone https://github.com/diegoborbadev/hackathon-kipper.git
```
Agora, acessar a pasta do projeto:
```bash
cd hackathon-kipper
```

## Instalando dependências, compilando e executando

Para instalar as dependências, basta executar o comando:
```bash
mvn install
```

Para compilar e executar o projeto:
```bash
mvn spring-boot:run
```

Pronto! A *API* estará disponível em `http://localhost:8080` e o painel do Swagger em `http://localhost:8080/swagger-ui/index.html` 🎉🎉.

Também é possível executar os testes unitários com o comando:
```bash
mvn test
```
