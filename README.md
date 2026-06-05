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
