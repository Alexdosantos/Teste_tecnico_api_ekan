# TESTE TECNICO API 

## Para iniciar a API basta 

Clone o repositório e em seguida execute o projeto. 

## Rodar localmente
Para rodar Locamente sem docker , você deve ter o postgresql instalado na sua maquina e as variaveis de ambiente configuradas no arquivo application.properties. 
Versao do Java : 17,
Postgres : 16



## Para rodar a API com Docker
Para iniciar a API basta executar o comando:
```
docker-compose up -d
```

## Swagger
Para acessar a documentação da API, basta acessar o endereço:
http://localhost:8080/api/v1/swagger-ui/index.html

## STMP Ethereal
O SMTP foi configurado para enviar e-mails automaticamente ao paciente.
Durante a criação de um novo atendimento, o sistema envia um e-mail de confirmação do agendamento normalmente.

Além disso, o envio de email também foi configurado para, ao realizar a pesquisa do último atendimento do paciente, enviar o agendamento convertido para o padrão FHIR R5 (recurso Encounter).

Para que o envio de e-mails funcione corretamente, é necessário possuir uma conta no Ethereal (para testes) ou utilizar qualquer outro servidor SMTP de sua preferência.

```
 https://ethereal.email/create
```
após criar a conta no ethereal, voce deve adicionar as variaveis de ambiente no arquivo application.properties se for rodar localmente ou no docker-compose.yml se for rodar com docker.






