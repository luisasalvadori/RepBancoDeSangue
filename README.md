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

**Prompt 5**

<ins>Pergunta:</ins>  
"Para atribuição dos tipos de sangue, utilizamos String. Para não ter nenhum tipo de erro, como posso fazer a verificação de letras maíusculas ou algum tipo de sangue não existente?"

<ins>Resposta:</ins>
"Como vocês já decidiram utilizar String, o ideal é criar uma validação para garantir que apenas tipos sanguíneos válidos sejam aceitos.

Uma abordagem simples seria utilizar o método toUpperCase() para converter qualquer entrada para letras maiúsculas e, em seguida, verificar se ela pertence ao conjunto de tipos permitidos.

Exemplo:
```java
@Override
public boolean validarDados() {

    tipoS = tipoS.toUpperCase();

    if (!(tipoS.equals("A+") ||
          tipoS.equals("A-") ||
          tipoS.equals("B+") ||
          tipoS.equals("B-") ||
          tipoS.equals("AB+") ||
          tipoS.equals("AB-") ||
          tipoS.equals("O+") ||
          tipoS.equals("O-"))) {

        return false;
    }

    return true;
}
```
Dessa forma:
```java
a+  → A+
ab+ → AB+
o-  → O-
```
serão aceitos corretamente.
Outra possibilidade interessante para o projeto seria lançar uma exceção personalizada:
```java
if (!validarTipoSanguineo()) {
    throw new IllegalArgumentException("Tipo sanguíneo inválido.");
}
```
Como vocês já estão utilizando tratamento de exceções, essa solução demonstra um uso mais completo dos conceitos de POO e validação de dados."

**Prompt 6**

<ins>Pergunta:</ins>  
"Estou utilizando ArrayList na classe Banco de Sangue. O mais correto seria utilizá-la como um parâmetro no construtor da classe ou colocar o new ArrayList dentro do construtor?"

<ins>Resposta:</ins>
"Para o contexto do projeto de vocês, o mais recomendado é instanciar o ArrayList dentro do construtor da classe BancoSangue, e não recebê-lo como parâmetro.

Por exemplo:
```java
public class BancoSangue {

    private String nome;
    private ArrayList<BolsaSangue> estoque;

    public BancoSangue(String nome) {
        this.nome = nome;
        this.estoque = new ArrayList<>();
    }
}
```
Essa abordagem é mais adequada porque o estoque faz parte da estrutura interna do banco de sangue. Quando um objeto BancoSangue é criado, é esperado que ele já possua um estoque inicial vazio, pronto para receber bolsas.

Além disso, essa solução:
- reduz a quantidade de parâmetros do construtor;
- evita que alguém crie um banco de sangue sem estoque;
- mantém o encapsulamento da classe;
- simplifica o uso da classe."

**Prompt 7**

<ins>Pergunta:</ins>  
"Queremos utilizar a Thread para realizar um atendimento simultâneo. Como realizar isso?"

<ins>Resposta:</ins>
"Para simular atendimento simultâneo, vocês podem criar uma classe que herda de Thread. Cada objeto dessa classe representa um atendimento acontecendo ao mesmo tempo.

Exemplo:
```java
public class AtendimentoThread extends Thread {

    private String nomePaciente;
    private String nomeFuncionario;

    public AtendimentoThread(String nomePaciente, String nomeFuncionario) {
        this.nomePaciente = nomePaciente;
        this.nomeFuncionario = nomeFuncionario;
    }

    @Override
    public void run() {
        System.out.println("Atendimento iniciado: " + nomePaciente +
                " por " + nomeFuncionario);

        try {
            Thread.sleep(3000); // simula tempo de atendimento
        } catch (InterruptedException e) {
            System.out.println("Atendimento interrompido.");
        }

        System.out.println("Atendimento finalizado: " + nomePaciente);
    }
}
```

**Prompt 8**

<ins>Pergunta:</ins>  
"Quando passamos um parâmetro do tipo Date, recebemos a data no formato AAAA-MM-DD. Como passar para DD-MM-AAAA?"

<ins>Resposta:</ins>
"Se vocês estão utilizando LocalDate, o formato padrão exibido pelo Java é: AAAA-MM-DD.
Para exibir no formato DD-MM-AAAA, vocês devem utilizar um DateTimeFormatter.

Exemplo:
```java
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

LocalDate data = LocalDate.now();

DateTimeFormatter formatter =
        DateTimeFormatter.ofPattern("dd-MM-yyyy");

String dataFormatada = data.format(formatter);

System.out.println(dataFormatada);
```

**Prompt 9**

<ins>Pergunta:</ins>  
"Ao fazer a classe Menu, teria alguma maneira de facilitar a leitura dos dados digitados?"

<ins>Resposta:</ins>
"Sim. Uma forma simples e organizada é criar métodos auxiliares no próprio Menu para ler cada tipo de dado.

Exemplo:
```java
private Scanner entrada = new Scanner(System.in);

private String lerTexto(String mensagem) {
    System.out.print(mensagem);
    return entrada.nextLine();
}

private int lerInteiro(String mensagem) {
    System.out.print(mensagem);
    int valor = entrada.nextInt();
    entrada.nextLine(); // limpa o enter
    return valor;
}

private double lerDouble(String mensagem) {
    System.out.print(mensagem);
    double valor = entrada.nextDouble();
    entrada.nextLine(); // limpa o enter
    return valor;
}
```
Assim, ao cadastrar um paciente, por exemplo, o código fica mais limpo:
```java
String nome = lerTexto("Nome: ");
int idade = lerInteiro("Idade: ");
String cpf = lerTexto("CPF: ");
String tipoS = lerTexto("Tipo sanguíneo: ");
double quantSangue = lerDouble("Quantidade de sangue: ");
```
Também é possível criar um método específico para datas usando a classe DataUtil:
```java
private LocalDate lerData(String mensagem) {
    String texto = lerTexto(mensagem);
    return DataUtil.converter(texto);
}
```
Dessa forma, o Menu fica mais organizado, evita repetição de código e facilita a manutenção do sistema."

**Prompt 10**

<ins>Pergunta:</ins>  
"Nas heranças, eu preciso utilizar os atributos como private ou protected? O que seria o ideal?"

<ins>Resposta:</ins>
"Para um projeto de Programação Orientada a Objetos, o mais recomendado é utilizar private e acessar os atributos por meio de getters e setters.

Exemplo:
```java
public abstract class Pessoa {

    private String nome;
    private int idade;
    private String cpf;

    // getters e setters
}
```
Essa abordagem segue o princípio do encapsulamento, que é uma das principais boas práticas da POO.
No entanto, muitos professores permitem o uso de protected em projetos acadêmicos quando há várias classes filhas acessando frequentemente os atributos da superclasse."
