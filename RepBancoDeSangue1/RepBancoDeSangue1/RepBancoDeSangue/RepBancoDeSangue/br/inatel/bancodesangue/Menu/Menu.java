package br.inatel.bancodesangue.Menu;

import br.inatel.bancodesangue.Arquivos.Arquivos;
import br.inatel.bancodesangue.Banco.BancoSangue;
import br.inatel.bancodesangue.Exceptions.BolsaNaoEncontradaException;
import br.inatel.bancodesangue.Exceptions.DadosInvalidosException;
import br.inatel.bancodesangue.Exceptions.TipoSanguineoException;
import br.inatel.bancodesangue.Pessoa.Doador.Doador;
import br.inatel.bancodesangue.Pessoa.Funcionario.Administrador;
import br.inatel.bancodesangue.Pessoa.Funcionario.Biomedico;
import br.inatel.bancodesangue.Pessoa.Funcionario.Enfermeiro;
import br.inatel.bancodesangue.Pessoa.Funcionario.Funcionario;
import br.inatel.bancodesangue.Pessoa.Paciente.Paciente;
import br.inatel.bancodesangue.Sangue.BolsaSangue;
import br.inatel.bancodesangue.Sangue.Doacao.Doacao;
import br.inatel.bancodesangue.Sangue.Solicitacao.SolicitacaoSangue;
import br.inatel.bancodesangue.Threads.AtendimentoThread;
import br.inatel.bancodesangue.Util.DataUtil;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// classe responsavel pela interação com o usuario, controla menus, cadastros, estoque, doacoes, solicitacoes e geracao de relatorios
public class Menu {
    private Scanner scanner;
    private BancoSangue banco;
    private Arquivos arquivo;
    private List<Doador> doadores;
    private List<Paciente> pacientes;
    private List<Administrador> administradores;
    private List<Biomedico> biomedicos;
    private List<Enfermeiro> enfermeiros;
    private SolicitacaoSangue solicitacaoAtual;

    public Menu() {
        scanner = new Scanner(System.in);
        banco = new BancoSangue("Rep Banco de Sangue");
        arquivo = new Arquivos("estoque_banco_sangue.csv");
        doadores = new ArrayList<>();
        pacientes = new ArrayList<>();
        administradores = new ArrayList<>();
        biomedicos = new ArrayList<>();
        enfermeiros = new ArrayList<>();
        carregarDadosIniciais();
    }

    private void carregarDadosIniciais() {
        try {
            Enfermeiro enfermeiro = new Enfermeiro("Ana", 32, "11111111111", "feminino", "Enfermeira", "ENF001", "COREN-12345");
            Biomedico biomedico = new Biomedico("Carlos", 40, "22222222222", "masculino", "Biomédico", "BIO001", "CRBio-98765");
            Administrador administrador = new Administrador("Marina", 35, "33333333333", "feminino", "Administradora", "ADM001");
            Doador doador = new Doador("João", 25, "44444444444", "masculino", "O+", 80.0, null, 0);
            Paciente paciente = new Paciente("Maria", 28, "5555555555", "feminino", "A+", 9, 2, 0, "Cirurgia de emergência");

            enfermeiros.add(enfermeiro);
            biomedicos.add(biomedico);
            administradores.add(administrador);
            doadores.add(doador);
            pacientes.add(paciente);
            solicitacaoAtual = paciente.solicitarSangue();

            banco.adicionarBolsa(new BolsaSangue("O+", LocalDate.now().minusDays(3)));
            banco.adicionarBolsa(new BolsaSangue("A+", LocalDate.now().minusDays(10)));
            banco.adicionarBolsa(new BolsaSangue("O-", LocalDate.now().minusDays(2)));
        } catch (TipoSanguineoException | DadosInvalidosException e) {
            System.out.println("Erro ao carregar dados iniciais: " + e.getMessage());
        }
    }

    public void executar() {
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> menuCadastros();
                case 2 -> menuEstoque();
                case 3 -> menuDoacoesSolicitacoes();
                case 4 -> menuArquivosRelatorios();
                case 5 -> simularThread();
                case 0 -> System.out.println("Encerrando o sistema.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void exibirMenuPrincipal() {
        System.out.println("===== SISTEMA BANCO DE SANGUE =====");
        System.out.println("1 - Cadastros");
        System.out.println("2 - Estoque e bolsas de sangue");
        System.out.println("3 - Doações e solicitações");
        System.out.println("4 - Arquivos e relatórios");
        System.out.println("5 - Simular atendimento com thread");
        System.out.println("0 - Sair");
    }

    private void menuCadastros() {
        int opcao;
        do {
            System.out.println("===== MENU DE CADASTROS =====");
            System.out.println("1 - Cadastrar doador");
            System.out.println("2 - Cadastrar paciente");
            System.out.println("3 - Cadastrar administrador");
            System.out.println("4 - Cadastrar biomédico");
            System.out.println("5 - Cadastrar enfermeiro");
            System.out.println("6 - Listar doadores");
            System.out.println("7 - Listar pacientes");
            System.out.println("8 - Listar funcionários");
            System.out.println("0 - Voltar");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarDoador();
                case 2 -> cadastrarPaciente();
                case 3 -> cadastrarAdministrador();
                case 4 -> cadastrarBiomedico();
                case 5 -> cadastrarEnfermeiro();
                case 6 -> listarDoadores();
                case 7 -> listarPacientes();
                case 8 -> listarFuncionarios();
                case 0 -> System.out.println("Voltando ao menu principal.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void menuEstoque() {
        int opcao;
        do {
            System.out.println("===== MENU DE ESTOQUE =====");
            System.out.println("1 - Listar estoque completo");
            System.out.println("2 - Adicionar bolsa manualmente");
            System.out.println("3 - Listar bolsas por tipo sanguíneo");
            System.out.println("4 - Listar quantidade de cada tipo sanguíneo");
            System.out.println("0 - Voltar");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> banco.listarEstoque();
                case 2 -> adicionarBolsaPeloMenu();
                case 3 -> listarPorTipo();
                case 4 -> banco.listarQuantidadePorTipo();
                case 0 -> System.out.println("Voltando ao menu principal.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void menuDoacoesSolicitacoes() {
        int opcao;
        do {
            System.out.println("===== MENU DE DOAÇÕES E SOLICITAÇÕES =====");
            System.out.println("1 - Registrar doação");
            System.out.println("2 - Criar solicitação de sangue");
            System.out.println("3 - Mostrar solicitação atual");
            System.out.println("4 - Liberar bolsa para a solicitação atual");
            System.out.println("0 - Voltar");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> registrarDoacao();
                case 2 -> criarSolicitacao();
                case 3 -> mostrarSolicitacaoAtual();
                case 4 -> liberarBolsa();
                case 0 -> System.out.println("Voltando ao menu principal.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void menuArquivosRelatorios() {
        int opcao;
        do {
            System.out.println("===== MENU DE ARQUIVOS E RELATÓRIOS =====");
            System.out.println("1 - Salvar estoque em arquivo");
            System.out.println("2 - Ler arquivo de estoque");
            System.out.println("3 - Gerar relatório geral");
            System.out.println("0 - Voltar");
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> salvarEstoque();
                case 2 -> lerArquivo();
                case 3 -> gerarRelatorioGeral();
                case 0 -> System.out.println("Voltando ao menu principal.");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void cadastrarDoador() {
        System.out.println("===== CADASTRO DE DOADOR =====");
        String nome = lerTexto("Nome: ");
        // verificando a idade, enquanto for negativa, solicita novamente
        int idade;
        do {
            idade = lerInteiro("Idade: ");

            if (idade < 0) {
                System.out.println("ERRO: idade não pode ser negativa.");
            }
        } while (idade < 0);
        // o cpf deve conter exatamente 11 digitos, sendo todos eles números 
        String cpf;
        do {
            cpf = lerTexto("CPF: ");

            if (!cpf.matches("\\d{11}")) {
                System.out.println("ERRO: CPF deve conter exatamente 11 NÚMEROS.");
            }

        } while (!cpf.matches("\\d{11}"));
        String sexo = lerTexto("Sexo: ");
        String tipoS = lerTexto("Tipo sanguíneo: ");
        double peso;
        do {
            peso = lerDouble("Peso: ");
            // o doador deve possuir no minimo 30 kg para fazer o cadastro com sucesso
            if (peso<30)
            {
                System.out.println("ERRO: peso não pode ser menor que 30kg.");
            }
        }while(peso<30);

        LocalDate ultimaDoacao = lerDataOpcionalNaoFutura("Última doação (DD-MM-AAAA ou vazio se nunca doou): ");
        // verifica se existe enfermeiro algum cadastrado
        if(enfermeiros.isEmpty()){
            System.out.println("Cadastre pelo menos um enfermeiro primeiro");
            return;
        }
        // seleciona o enfermeiro responsavel pelo cadastro
        Enfermeiro enfermeiro = escolherEnfermeiro();

        if(enfermeiro == null){
            return;
        }
        // o enfermeiro realiza o cadastro do doador
        Doador doador = enfermeiro.cadastrarDoador(nome,idade,cpf,sexo,tipoS,peso,ultimaDoacao);
        // se o cadastro foi realizado com sucesso, adiciona o doador a lista do sistema
        if (doador != null) {
            doadores.add(doador);
        }
    }

        // método responsável por cadastrar um novo paciente 
        private void cadastrarPaciente() {
            System.out.println("===== CADASTRO DE PACIENTE =====");
            String nome = lerTexto("Nome: ");

            // valida a idade para impedir valores negativos
            int idade;
            do {
                idade = lerInteiro("Idade: ");

                if (idade < 0) {
                    System.out.println("ERRO: idade não pode ser negativa.");
                }
            } while (idade < 0);

            // valida o peso, no caso usando o parametro que não pode ser menor que 0kg
            double peso;
            do {
                peso = lerDouble("Peso: ");
                if (peso < 0)
                {
                    System.out.println("ERRO: peso não pode ser menor ou igual a 0kg.");
                }
            }while(peso < 0);
            String cpf = lerTexto("CPF: ");
            String sexo = lerTexto("Sexo: ");
            String tipoS = lerTexto("Tipo sanguíneo: ");
            int nivelUrgencia = lerInteiro("Nível de urgência (0 a 10): ");
            double quantSangue = lerDouble("Quantidade de bolsas necessárias: ");
            String diagnostico = lerTexto("Diagnóstico: ");

            try {
                // cria o paciente com os dados informados
                Paciente paciente = new Paciente(nome, idade, cpf, sexo, tipoS, nivelUrgencia, quantSangue, 0, diagnostico);
                // adiciona o paciente à lista do sistema
                pacientes.add(paciente);
                System.out.println("Paciente cadastrado com sucesso.");
            } catch (TipoSanguineoException e) {
                // caso o tipo sanguíneo seja inválido, o erro é tratado aqui
                System.out.println("Erro no cadastro do paciente: " + e.getMessage());
            }
        }

    // método responsável por cadastrar um administrador
    private void cadastrarAdministrador() {
        System.out.println("===== CADASTRO DE ADMINISTRADOR =====");
        String nome = lerTexto("Nome: ");
        int idade = lerInteiro("Idade: ");
        String cpf = lerTexto("CPF: ");
        String sexo = lerTexto("Sexo: ");
        String matricula = lerTexto("Matrícula: ");
        // cargo é definido automaticamente como "Administrador".
        administradores.add(new Administrador(nome, idade, cpf, sexo, "Administrador", matricula));
        System.out.println("Administrador cadastrado com sucesso.");
    }

    // método responsável por cadastrar um biomédico
    private void cadastrarBiomedico() {
        System.out.println("===== CADASTRO DE BIOMÉDICO =====");
        String nome = lerTexto("Nome: ");
        int idade = lerInteiro("Idade: ");
        String cpf = lerTexto("CPF: ");
        String sexo = lerTexto("Sexo: ");
        String matricula = lerTexto("Matrícula: ");
        String crbio = lerTexto("CRBio: ");
        // cargo é definido automaticamente como "Biomédico"
        biomedicos.add(new Biomedico(nome, idade, cpf, sexo, "Biomédico", matricula, crbio));
        System.out.println("Biomédico cadastrado com sucesso.");
    }
    
    // método responsável por cadastrar um enfermeiro
    private void cadastrarEnfermeiro() {
        System.out.println("===== CADASTRO DE ENFERMEIRO =====");
        String nome = lerTexto("Nome: ");
        int idade = lerInteiro("Idade: ");
        String cpf = lerTexto("CPF: ");
        String sexo = lerTexto("Sexo: ");
        String matricula = lerTexto("Matrícula: ");
        String coren = lerTexto("COREN: ");
        // cargo é definido automaticamente como "Enfermeiro"
        enfermeiros.add(new Enfermeiro(nome, idade, cpf, sexo, "Enfermeiro", matricula, coren));
        System.out.println("Enfermeiro cadastrado com sucesso.");
    }

    // método responsável por adicionar uma bolsa diretamente pelo menu
    private void adicionarBolsaPeloMenu() {
        try {
            String tipo = lerTexto("Tipo sanguíneo da bolsa: ");
             // lê a data da coleta e impede datas futuras
            LocalDate dataColeta = lerDataObrigatoriaNaoFutura("Data da coleta (DD-MM-AAAA): ");
            // cria a bolsa e adiciona ao estoque do banco
            banco.adicionarBolsa(new BolsaSangue(tipo, dataColeta));
        } catch (TipoSanguineoException | DadosInvalidosException e) {
             // trata tipo sanguíneo inválido ou data inválida
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // lista as bolsas de um tipo sanguíneo específico
    private void listarPorTipo() {
        String tipo = lerTexto("Tipo sanguíneo para busca: ");
        banco.listarTipo(tipo);
    }

    // registra uma doação feita por um doador
    private void registrarDoacao() {
        // não é possível registrar doação sem doadores cadastrados
        if (doadores.isEmpty()) {
            System.out.println("Cadastre pelo menos um doador primeiro.");
            return;
        }
        // não é possível registrar doação sem enfermeiros cadastrados
        if (enfermeiros.isEmpty()) {
            System.out.println("Cadastre pelo menos um enfermeiro primeiro.");
            return;
        }

        // escolhe o doador e o enfermeiro responsáveis pela doação
        Doador doador = escolherDoador();
        Enfermeiro enfermeiro = escolherEnfermeiro();
        // se alguma escolha for inválida, cancela a operação
        if (doador == null || enfermeiro == null) return;

        try {
            // lê a data da coleta da doação
            LocalDate dataColeta = lerDataObrigatoriaNaoFutura("Data da coleta (DD-MM-AAAA): ");
            // cria uma bolsa com o mesmo tipo sanguíneo do doador
            BolsaSangue bolsa = new BolsaSangue(doador.getTipoS(), dataColeta);
            // enfermeiro realiza a coleta
            Doacao doacao = enfermeiro.coletarSangue(doador, bolsa, dataColeta);
            // se a doação foi realizada, registra e adiciona a bolsa ao estoque
            if (doacao != null) {
                doacao.registrarDoacao();
                banco.adicionarBolsa(doacao.gerarBolsa());
            }
        } catch (TipoSanguineoException | DadosInvalidosException e) {
            // trata erros de tipo sanguíneo ou dados inválidos
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // cria uma nova solicitação de sangue para um paciente escolhido
    private void criarSolicitacao() {
        if (pacientes.isEmpty()) {
            System.out.println("Cadastre pelo menos um paciente primeiro.");
            return;
        }
        Paciente paciente = escolherPaciente();
        if (paciente == null) return;
        // cria uma solicitação usando o método da própria classe Paciente
        solicitacaoAtual = paciente.solicitarSangue();
        // mostra os dados da solicitação recém-criada
        solicitacaoAtual.mostrarDados();
    }

    // exibe os dados da solicitação atual
    private void mostrarSolicitacaoAtual() {
        if (solicitacaoAtual == null) {
            System.out.println("Nenhuma solicitação criada.");
            return;
        }
        solicitacaoAtual.mostrarDados();
    }
    
    // libera uma bolsa para a solicitação atual, a liberação depende da análise do biomédico
    private void liberarBolsa() {
        if (solicitacaoAtual == null) {
            System.out.println("Crie uma solicitação antes de liberar uma bolsa.");
            return;
        }
        if (biomedicos.isEmpty()) {
            System.out.println("Cadastre pelo menos um biomédico primeiro.");
            return;
        }

        try {
            // escolhe o biomédico responsável pela análise
            Biomedico biomedico = escolherBiomedico();
            if (biomedico == null) return;
            // busca a bolsa pelo ID informado
            int id = lerInteiro("Informe o ID da bolsa: ");
            BolsaSangue bolsa = banco.buscarPorId(id);
            // define no biomédico qual solicitação e qual bolsa serão analisadas
            biomedico.setSolicitacao(solicitacaoAtual);
            biomedico.setBolsaEmAnalise(bolsa);
            // se a bolsa for liberada, o paciente recebe a bolsa e ela é removida do estoque
            if (biomedico.liberarBolsa()) {
                solicitacaoAtual.getPaciente().receberBolsa();
                banco.removerBolsa(bolsa);
            }
        } catch (BolsaNaoEncontradaException e) {
            // caso o ID informado não corresponda a nenhuma bolsa
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // simula um atendimento usando Thread, ela executa a análise/liberação de uma bolsa em uma linha de execução separada
    private void simularThread() {
        if (solicitacaoAtual == null || biomedicos.isEmpty()) {
            System.out.println("É necessário ter uma solicitação atual e um biomédico cadastrado.");
            return;
        }
        try {
            Biomedico biomedico = escolherBiomedico();
            if (biomedico == null) return;
            int id = lerInteiro("Informe o ID da bolsa para a thread: ");
            BolsaSangue bolsa = banco.buscarPorId(id);
            // cria a thread de atendimento
            AtendimentoThread atendimento = new AtendimentoThread("Thread-Atendimento-1", biomedico, solicitacaoAtual, bolsa);
            // inicia a execução da thread
            atendimento.start();
            // faz a thread principal esperar a thread de atendimento terminar
            atendimento.join();
        } catch (BolsaNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (InterruptedException e) {
            // caso a thread seja interrompida, restaura o estado de interrupção
            Thread.currentThread().interrupt();
            System.out.println("Thread interrompida.");
        }
    }
    
    // salva o estoque atual em um arquivo
    private void salvarEstoque() {
        try {
            arquivo.salvarEstoque(banco);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    // lê e mostra o conteúdo do arquivo de estoque
    private void lerArquivo() {
        try {
            arquivo.mostrarArquivo();
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    // gera um relatório geral usando um administrador
    private void gerarRelatorioGeral() {
        if (administradores.isEmpty()) {
            System.out.println("Cadastre pelo menos um administrador primeiro.");
            return;
        }
        Administrador administrador = escolherAdministrador();
        if (administrador != null) administrador.gerarRelatorioGeral(banco);
    }

    // lista todos os doadores cadastrados
    private void listarDoadores() {
        System.out.println("===== DOADORES CADASTRADOS =====");
        if (doadores.isEmpty()) {
            System.out.println("Nenhum doador cadastrado.");
            return;
        }
        for (int i = 0; i < doadores.size(); i++) {
            System.out.println("[" + (i + 1) + "]");
            doadores.get(i).mostrarDados();
        }
    }

    // lista todos os pacientes cadastrados
    private void listarPacientes() {
        System.out.println("===== PACIENTES CADASTRADOS =====");
        if (pacientes.isEmpty()) {
            System.out.println("Nenhum paciente cadastrado.");
            return;
        }
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println("[" + (i + 1) + "]");
            pacientes.get(i).mostrarDados();
        }
    }

    // lista todos os funcionários, separados por tipo
    private void listarFuncionarios() {
        System.out.println("===== FUNCIONÁRIOS CADASTRADOS =====");
        listarListaFuncionarios("Administradores", administradores);
        listarListaFuncionarios("Biomédicos", biomedicos);
        listarListaFuncionarios("Enfermeiros", enfermeiros);
    }

    // método genérico para listar qualquer lista de funcionários, o uso de "? extends Funcionario" permite passar listas de Administrador, Biomedico ou Enfermeiro, pois todos herdam de Funcionario
    private void listarListaFuncionarios(String titulo, List<? extends Funcionario> funcionarios) {
        System.out.println("\n--- " + titulo + " ---");
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum cadastro.");
            return;
        }
        for (int i = 0; i < funcionarios.size(); i++) {
            System.out.println("\n[" + (i + 1) + "]");
            funcionarios.get(i).mostrarDados();
        }
    }

    // mostra os doadores cadastrados e permite escolher um deles
    private Doador escolherDoador() {
        listarNomesDoadores();
        int posicao = lerInteiro("Escolha o doador: ") - 1;
        if (posicao < 0 || posicao >= doadores.size()) {
            System.out.println("Doador inválido.");
            return null;
        }
        return doadores.get(posicao);
    }

    // mostra os pacientes cadastrados e permite escolher um deles
    private Paciente escolherPaciente() {
        listarNomesPacientes();
        int posicao = lerInteiro("Escolha o paciente: ") - 1;
        if (posicao < 0 || posicao >= pacientes.size()) {
            System.out.println("Paciente inválido.");
            return null;
        }
        return pacientes.get(posicao);
    }

    // mostra os administradores cadastrados e permite escolher um deles
    private Administrador escolherAdministrador() {
        listarNomesFuncionarios(administradores);
        int posicao = lerInteiro("Escolha o administrador: ") - 1;
        if (posicao < 0 || posicao >= administradores.size()) {
            System.out.println("Administrador inválido.");
            return null;
        }
        return administradores.get(posicao);
    }

    // mostra os biomédicos cadastrados e permite escolher um deles
    private Biomedico escolherBiomedico() {
        listarNomesFuncionarios(biomedicos);
        int posicao = lerInteiro("Escolha o biomédico: ") - 1;
        if (posicao < 0 || posicao >= biomedicos.size()) {
            System.out.println("Biomédico inválido.");
            return null;
        }
        return biomedicos.get(posicao);
    }

    // mostra os enfermeiros cadastrados e permite escolher um deles
    private Enfermeiro escolherEnfermeiro() {
        listarNomesFuncionarios(enfermeiros);
        int posicao = lerInteiro("Escolha o enfermeiro: ") - 1;
        if (posicao < 0 || posicao >= enfermeiros.size()) {
            System.out.println("Enfermeiro inválido.");
            return null;
        }
        return enfermeiros.get(posicao);
    }

    // lista apenas os nomes dos doadores para facilitar a escolha
    private void listarNomesDoadores() {
        System.out.println("===== ESCOLHA UM DOADOR =====");
        for (int i = 0; i < doadores.size(); i++) {
            System.out.println((i + 1) + " - " + doadores.get(i).getNome() + " (" + doadores.get(i).getTipoS() + ")");
        }
    }

    // lista apenas os nomes dos pacientes para facilitar a escolha
    private void listarNomesPacientes() {
        System.out.println("===== ESCOLHA UM PACIENTE =====");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + " - " + pacientes.get(i).getNome() + " (" + pacientes.get(i).getTipoS() + ")");
        }
    }

    // lista apenas nome e matrícula dos funcionários para facilitar a escolha
    private void listarNomesFuncionarios(List<? extends Funcionario> funcionarios) {
        System.out.println("===== ESCOLHA UM FUNCIONÁRIO =====");
        for (int i = 0; i < funcionarios.size(); i++) {
            System.out.println((i + 1) + " - " + funcionarios.get(i).getNome() + " | Matrícula: " + funcionarios.get(i).getMatricula());
        }
    }
    // le um texto digitado pelo usuario
    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    // lê um número inteiro, enquanto o usuário digitar algo inválido, o método continua pedindo
    private int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }
    // lê um número decimal, o replace permite aceitar tanto vírgula quanto ponto
    private double lerDouble(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Double.parseDouble(scanner.nextLine().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Digite um número decimal válido.");
            }
        }
    }

    // lê uma data obrigatória no formato DD-MM-AAAA, se o formato estiver errado, pede novamente
    private LocalDate lerDataObrigatoria(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return DataUtil.converter(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use DD-MM-AAAA, por exemplo: 03-06-2026.");
            }
        }
    }
    
    // lê uma data obrigatória e impede que ela seja futura
    private LocalDate lerDataObrigatoriaNaoFutura(String mensagem) {
        while (true) {
            LocalDate data = lerDataObrigatoria(mensagem);
            try {
                br.inatel.bancodesangue.Util.ValidadorDados.validarDataNaoFutura(data, "Data informada");
                return data;
            } catch (DadosInvalidosException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    // lê uma data opcional, se o usuário deixar em branco, retorna null
    private LocalDate lerDataOpcional(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String texto = scanner.nextLine();
                if (texto.isBlank()) return null;
                return DataUtil.converter(texto);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida. Use DD-MM-AAAA, por exemplo: 03-06-2026.");
            }
        }
    }

    // lê uma data opcional e impede que ela seja futura
    private LocalDate lerDataOpcionalNaoFutura(String mensagem) {
        while (true) {
            LocalDate data = lerDataOpcional(mensagem);
            try {
                br.inatel.bancodesangue.Util.ValidadorDados.validarDataNaoFutura(data, "Data informada");
                return data;
            } catch (DadosInvalidosException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }
}
