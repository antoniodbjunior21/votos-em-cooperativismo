# Sistema de votação em cooperativismo
Projeto criado para teste técnico.

Criado para fornecer respostas em JSON, para montagem das telas na aplicação mobile. 

- Base em Spring Boot
- Banco de dados: Postgres
- Serviço online da API Backend: http://votos.api.antoniodbjunior.com.br/api/v1/
- Serviço online da aplicação fontend na nuvem para demonstração: http://votos.antoniodbjunior.com.br

Observações importantes:
- Para testes em localhost, altere a propriedade spring.profiles.active=prod para spring.profiles.active=dev, no arquivo application.properties.
- A aplicação frontend para demonstração foi criada para testar as requisições e simular possibilidades de erros de CORS e outros tipos de erros.
- O serviço de validação remota de CPF requerido como tarefa bonus, parece estar desativado, ou por falta de documentação, não foi especificado o real status desse serviço. O link provido para este serviço é: https://user-info.herokuapp.com/users/{cpf}
Este serviço foi implementado, porém está comentado, para checagem descomentar as linhas 42 a 47 da classe AssociadoServiceImpl.
