
src/main/java/com/example/estudo
├── controller
│   └── MedicoController.java
├── domain
│   └── Medico.java
├── dto
│   ├── MedicoDTO.java
│   ├── RespostaLista.java
│   ├── RespostaPadrao.java
│   └── RespostaUpdate.java
├── repository
│   └── MedicoRepository.java
├── services
│   └── MedicoService.java
├── config
│   └── WebSecurityConfig.java
├── exception
│   └── CustomExceptionHandler.java
└── util
    └── DateUtils.java

Descrição dos Pacotes
controller

Descrição: Contém as classes responsáveis pela exposição dos endpoints REST da aplicação. Essas classes são anotadas com @RestController ou @Controller e são responsáveis por lidar com as requisições HTTP e devolver as respostas apropriadas.
Exemplo: MedicoController.java
domain

Descrição: Armazena as entidades que representam as tabelas do banco de dados. Essas classes são anotadas com @Entity e definem a estrutura dos dados persistidos.
Exemplo: Medico.java
dto

Descrição: Contém os Data Transfer Objects (DTOs) usados para transportar dados entre as camadas da aplicação. DTOs ajudam a manter a separação entre a camada de apresentação e a camada de persistência.
Exemplos:
MedicoDTO.java - Dados do médico.
RespostaLista.java - Resposta contendo a lista de médicos.
RespostaPadrao.java - Resposta padrão com mensagem e status HTTP.
RespostaUpdate.java - Resposta para atualizações de dados.
repository

Descrição: Inclui os repositórios que são responsáveis pela interação com o banco de dados. Normalmente, essas classes são interfaces que estendem JpaRepository ou CrudRepository e fornecem métodos para operações CRUD e consultas personalizadas.
Exemplo: MedicoRepository.java
services

Descrição: Contém as classes de serviço que encapsulam a lógica de negócios da aplicação. Essas classes são anotadas com @Service e fazem a ponte entre os controladores e os repositórios, processando dados e aplicando regras de negócios.
Exemplo: MedicoService.java
config

Descrição: Inclui classes de configuração que definem as configurações específicas da aplicação, como segurança, configuração de beans e outras configurações necessárias.
Exemplo: WebSecurityConfig.java
exception

Descrição: Contém classes relacionadas ao tratamento de exceções. Pode incluir manipuladores globais de exceções que ajudam a centralizar e gerenciar erros de forma eficiente.
Exemplo: CustomExceptionHandler.java
util

Descrição: Armazena utilitários e helpers que são usados em diferentes partes da aplicação. Esses utilitários ajudam a evitar a duplicação de código e promovem a reutilização.
Exemplo: DateUtils.java
Como Contribuir
Se você deseja contribuir para este projeto, siga estas etapas:

Faça um fork do repositório.
Crie uma branch para suas alterações (git checkout -b minha-feature).
Faça as suas alterações e commit (git commit -am 'Adiciona nova feature').
Faça o push para a branch (git push origin minha-feature).
Abra um Pull Request.
Licença
Este projeto é licenciado sob a Licença MIT.
