**Utilização de IA:**
Foi utilizado o ChatGPT (GPT-5.3 OpenAI) para sanar algumas dúvidas

---

 **Prompt 1**

<ins>Pergunta:</ins>  
"Preciso de ajuda para elaborar ideias de um projeto de programação orientada a objetos. O trabalho precisa ser focado na área de engenharia biomédica, com os seguintes critérios: herança, polimorfismo, interfaces e classes abstratas, tratamento de erros, arquivos e threads. Você pode me dar sugestões de ideias que se encaixem nos critérios."

<ins>Resposta:</ins> 
"Claro. Seguem algumas sugestões de projetos de Programação Orientada a Objetos voltados à Engenharia Biomédica, contemplando herança, polimorfismo, interfaces, classes abstratas, tratamento de erros, arquivos e threads.
1. Sistema de Banco de Sangue
Controle de doadores, pacientes, bolsas de sangue e transfusões. Permite usar herança, interfaces, arquivos e threads para simular atendimentos simultâneos.
2. Sistema de Monitoramento de Sinais Vitais
Monitora frequência cardíaca, pressão e temperatura de pacientes em tempo real. Threads podem simular sensores funcionando ao mesmo tempo.
3. Sistema de Controle de Equipamentos Biomédicos
Gerencia equipamentos hospitalares, manutenção e status de funcionamento, como respiradores e monitores cardíacos.
4. Sistema de Triagem Hospitalar
Organiza pacientes por nível de urgência e controla atendimentos médicos. Threads podem simular múltiplos pacientes chegando simultaneamente.
5. Sistema de Prontuário Eletrônico
Armazena consultas, exames, diagnósticos e prescrições de pacientes em arquivos digitais.
6. Sistema de Doação e Transplante de Órgãos
Controla doadores, receptores, compatibilidade e fila de espera para transplantes.
7. Sistema de Controle de Medicamentos
Gerencia prescrições, estoque, validade e horários de medicação dos pacientes.
8. Sistema de Agendamento de Exames
Controla agendamento de exames laboratoriais e de imagem, evitando conflitos de horário e equipamentos."

 **Prompt 2**

<ins>Pergunta:</ins>
"O trabalho que faremos será um o gerenciamento de banco de sangue. Selecionamos as seguintes classes: pessoa, doador, paciente, funcionário, administrador, biomédico, enfermeiro, doação, banco sangue, solicitação sangue, bolsa sangue, algumas exceptions, uma classe para administrar os arquivos e uma interface para identificar a compatibilidade. Pode separá-las em pacotes para ficar melhor organizadas?"

<ins>Resposta:</ins>
"Sim. Uma organização adequada seria separar as classes conforme suas responsabilidades dentro do sistema:
```java
br.inatel.bancodesangue

├── pessoas
│   ├── Pessoa
│   ├── Doador
│   └── Paciente
│
├── funcionarios
│   ├── Funcionario
│   ├── Administrador
│   ├── Biomedico
│   └── Enfermeiro
│
├── sangue
│   ├── BolsaSangue
│   ├── BancoSangue
│   ├── Doacao
│   └── SolicitacaoSangue
│
├── interfaces
│   └── Compativel
│
├── arquivos
│   └── GerenciadorArquivo
│
├── excecoes
│   ├── DoadorInaptoException
│   ├── BolsaIndisponivelException
│   ├── TipoSanguineoIncompativelException
│   └── ArquivoException
│
└── principal
    └── Main
```
Essa divisão permite que o projeto fique mais organizado e facilita a manutenção do código. O pacote pessoas reúne as classes relacionadas aos indivíduos atendidos pelo sistema. O pacote funcionarios concentra os profissionais responsáveis pelas operações do banco de sangue. O pacote sangue agrupa as classes diretamente ligadas às doações, bolsas, solicitações e controle de estoque. Já os pacotes interfaces, arquivos e excecoes separam recursos auxiliares importantes para a aplicação, como contratos de comportamento, persistência de dados e tratamento de erros."

**Prompt 3**

<ins>Pergunta:</ins>  
"Quais seriam as relações de dependência ideal entre as classes?"

<ins>Resposta:</ins>
"As relações de dependência ideais seriam:
Doador, Paciente e Funcionario possuem herança com Pessoa. Já Administrador, Biomedico e Enfermeiro possuem herança com Funcionario.
```java
Doador 1 -------- 0..* Doacao
Enfermeiro 1 ---- 0..* Doacao
Doacao 1 -------- 1 BolsaSangue
```
Uma doação pertence a um doador, é registrada por um enfermeiro e gera uma bolsa de sangue.
```java
BancoSangue 1 -------- 0..* BolsaSangue
Administrador -------- BancoSangue
```
O banco de sangue armazena várias bolsas, e o administrador gerencia o estoque.
```java
Paciente 1 -------- 0..* SolicitacaoSangue
SolicitacaoSangue -------- BolsaSangue
SolicitacaoSangue - - -▷ Compativel
```
O paciente realiza solicitações de sangue. A solicitação usa uma bolsa para verificar compatibilidade e implementa a interface Compativel.
```java
Biomedico -------- BolsaSangue
Biomedico -------- SolicitacaoSangue
```
O biomédico analisa a solicitação e libera ou rejeita a bolsa.
Essa estrutura mantém o sistema organizado e coerente com as responsabilidades de cada classe."

**Prompt 4**

<ins>Pergunta:</ins>  
"A classe SolicitaçaoSangue implementa a interface Compativel para chamar o método verificarCompatibilidade. Seria correto que a classe Biomédico também a implementasse?"

<ins>Resposta:</ins>
"Não necessariamente.

Do ponto de vista da modelagem orientada a objetos, a interface Compativel deve ser implementada pelas classes que realmente possuem a responsabilidade de verificar a compatibilidade sanguínea.

Se a interface foi definida como:
```java
public interface Compativel {
    boolean verificarCompatibilidade(BolsaSangue bolsa);
}
```
e a classe SolicitacaoSangue é responsável por comparar os dados do paciente com os dados da bolsa, então faz sentido que apenas ela implemente a interface.
```java
SolicitacaoSangue - - -▷ Compativel
```
Nesse cenário, o fluxo seria:
```java
Paciente realiza uma solicitação
↓
SolicitacaoSangue verifica a compatibilidade
↓
Biomedico analisa o resultado
↓
Biomedico libera ou rejeita a bolsa
```
Por outro lado, seria justificável que o Biomedico implementasse a interface apenas se ele também fosse responsável por executar sua própria verificação de compatibilidade.

Por exemplo:
```java
public class Biomedico extends Funcionario implements Compativel
```
Nesse caso, o método poderia representar uma segunda análise realizada pelo profissional antes da liberação da bolsa.

Entretanto, considerando a divisão atual de responsabilidades do projeto, a alternativa mais coerente é:

SolicitacaoSangue implementa Compativel.
Biomedico utiliza o resultado da compatibilidade para tomar a decisão de liberar ou não a bolsa.

Essa abordagem evita duplicação de responsabilidades e segue melhor o princípio da responsabilidade única (Single Responsibility Principle), tornando o projeto mais organizado e fácil de manter."
