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
O STMP foi configurado para enviar emails para o email do paciente, para que ele possa receber o agendamento convertido para FHIR R5 (recurso Encounter).
Para que o STMP funcione, voce deve ter uma conta no Ethereal ou usar outro STMP de sua preferencia.
```
 https://ethereal.email/create
```
após criar a conta no ethereal, voce deve adicionar as variaveis de ambiente no arquivo application.properties se for rodar localmente ou no docker-compose.yml se for rodar com docker.






