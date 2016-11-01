Relatório de Desenvolvimento - Pixeon G+ Chat Room
================================

## Informações

O G+ Chat Room, foi desenvolvido como prova de conceitos a fim de medir os conhecimentos de programação, modelagem de dados e engenharia de software.
O aplicativo consiste na execução de uma sala de chat para usuários do Google+ e está disponível o acesso através do link: [Acesse](http://gplus-chat-room.pixeon.s3-website-us-west-2.amazonaws.com).

## Arquitetura

Está prova de conceitos foi implementada de forma desacoplada, assim temos a divisão entre: Front-End e Back-End de forma natural. Para desenvolvimento do Fron-End foi utiliado o framework AngularJs e a construção do aplicativo foi realizada como Single Page Application. O Back-End foi desenvolvido em Java, utilizando o Play Framework, sua distribuição é feita de maneira Stand-Alone e projetada para ser escalável e flexível.

### Single Page Application (SPA)

Com a nova Web moderna, tornar os aplicativos mais fluidos e flexiveis é um requisito para colocar se neste mercaod. O modelo SPA, vem de encontro a este requisitos, eliminar atualizações desnecessárias e a quebra de fluxo é uma das principais caracteristicas deste modelo. 

Quando trabalhamos com o modelo SPA, passamos a delegar mais do que propriamente fazer. O conteúdo visual da aplicação que antes era inteiramente processado no servidor passa a ser de responsabilidade do cliente, este tem a responsabilidade de interagir com o usuário e solicitar os dados propriamente ditos para o servidor. Este, agora desacoplado, tem a possibilidade de gastar sua energia apenas com regras de negócio e orquestração dos serviços.

### RESTFUL Services

Desacomplamos nossa aplicação, e agora? Quem deverá processar e trabalhar com nossas informações?

Contruimos uma API de serviços para realizar está tarefa. Para isso, utilizamos o estitulo arquitetural Representational State Transfer (REST). Um modelo que através de suas interfaces bem definidas possibilida que suas implementações ofereçam: performance, escalabilidade e flexibilidade. Alguns principios fazemo com que o RESTFul alcance os itens mencionados, como a: Identificação para acesso de Recursos através da URI. E ainda um modelo uniforme através dos métodos http para manutenção e manipulação de dados. Através deste modelo, é possível fazer nossa aplicação escalar e ser robusta ao mesmo tempo.


## Hospedagem e Deploy

A hospedagem do nosso aplicativo foi feita através da Amazon Web Services (AWS), para obter o máximo de recursos e o menor custo a segregação dos ambientes é muito importante.

Todo o conteúdo estático foi distribuido através do S3, serviço de armazenamento que garante disponibilidade e baixa latência, ao mesmo tempo o baixo custo deste armazenamento também é essencial. Já a distribuição do nosso serviço, foi feito em uma instância EC2 com Docker, através do container, otimizamos nossa aplicação e controlamos os recursos de infra estrutura de maneira mais eficiente.

## Desenvolvimento

### Escolhas importantes

Neste exemplo, contruimos uma aplicação utilizando um "monolito", isto é, um unico serviço responsável por todas as necessidade do nosso aplicativo. Não houve a necessidade de maior desacomplamento, visto que qualquer coisa diferente to modelo escolhido poderia aumentar o custo do aplicativo. A plataforma de deploy, também foi essencial para a entrega, minimizando os custos e aumentado a confiabilidade de distribuição e disponibilidade.

Outra escolha importante foi o provedor de autenticação, para está prova de conceitos utilizei o provedor Auth0. Este teve a responsabilidade de prover a autenticação e orquestração de oAuth com o Google+.

### Facilidades e Dificuldades

Primeiro contato com o Play Framework, podendo enumerar qualidades e defeitos deste. O modelo Fat-Jar é muito interessante para a construção de um aplicativo simples, em contra partida o artefato final contou com muitas bibliotecas desnecessárias. Constatei uma dificuldade inicial para montar o ambiente, algumas funcionalidades do framework ainda não estão totalmente maduras, tal como a implementação de CORS, foi necessário um pequeno Hack na implementação do Framework para utilizar o componente nativo. (Item já em análise no Issue Tracker do Play).

O tempo de desenvolvimento foi agradavél, cerca de 6 horas de implementação e testes. Já estava familiarizado com o modelo SPA e RESTFul, não tendo dificuldades neste aspecto.

## Guia do Software

### Repositório

* Bitbucket: [Acesse](https://bitbucket.org/pflima92/chat-room-poc)

### Artefatos

* chat-room-static = Front-End
* chat-room-svc = Back-End

### Instalação

``` bash

// Git clone
$ git clone git@bitbucket.org:pflima92/chat-room-poc.git

// Build chat-room-static 
$ cd chat-room-static
$ npm install
$ bower install
$ gulp serve

// Build chat-room-static 
$ cd chat-room-svc
$ activator compile run

```

Sua aplicação deverá ser iniciada no endereço: http://localhost:3000

A api será inicializada através do Activator no endereço: http://localhost:9000


## Fontes e Referências

http://docs.oracle.com/javaee/6/tutorial/doc/gijqy.html