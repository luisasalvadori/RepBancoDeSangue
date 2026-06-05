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
        int idade;
        do {
            idade = lerInteiro("Idade: ");

            if (idade < 0) {
                System.out.println("ERRO: idade não pode ser negativa.");
            }
        } while (idade < 0);
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
            if (peso<30)
            {
                System.out.println("ERRO: peso não pode ser menor que 30kg.");
            }
        }while(peso<30);

        LocalDate ultimaDoacao = lerDataOpcionalNaoFutura("Última doação (DD-MM-AAAA ou vazio se nunca doou): ");

        if(enfermeiros.isEmpty()){
            System.out.println("Cadastre pelo menos um enfermeiro primeiro");
            return;
        }

        Enfermeiro enfermeiro = escolherEnfermeiro();

        if(enfermeiro == null){
            return;
        }


        Doador doador = enfermeiro.cadastrarDoador(nome,idade,cpf,sexo,tipoS,peso,ultimaDoacao);

        if (doador != null) {
            doadores.add(doador);
        }
    }
        private void cadastrarPaciente() {
            System.out.println("===== CADASTRO DE PACIENTE =====");
            String nome = lerTexto("Nome: ");
            int idade;
            do {
                idade = lerInteiro("Idade: ");

                if (idade < 0) {
                    System.out.println("ERRO: idade não pode ser negativa.");
                }
            } while (idade < 0);
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
                Paciente paciente = new Paciente(nome, idade, cpf, sexo, tipoS, nivelUrgencia, quantSangue, 0, diagnostico);
                pacientes.add(paciente);
                System.out.println("Paciente cadastrado com sucesso.");
            } catch (TipoSanguineoException e) {
                System.out.println("Erro no cadastro do paciente: " + e.getMessage());
            }
        }

    private void cadastrarAdministrador() {
        System.out.println("===== CADASTRO DE ADMINISTRADOR =====");
        String nome = lerTexto("Nome: ");
        int idade = lerInteiro("Idade: ");
        String cpf = lerTexto("CPF: ");
        String sexo = lerTexto("Sexo: ");
        String matricula = lerTexto("Matrícula: ");
        administradores.add(new Administrador(nome, idade, cpf, sexo, "Administrador", matricula));
        System.out.println("Administrador cadastrado com sucesso.");
    }

    private void cadastrarBiomedico() {
        System.out.println("===== CADASTRO DE BIOMÉDICO =====");
        String nome = lerTexto("Nome: ");
        int idade = lerInteiro("Idade: ");
        String cpf = lerTexto("CPF: ");
        String sexo = lerTexto("Sexo: ");
        String matricula = lerTexto("Matrícula: ");
        String crbio = lerTexto("CRBio: ");
        biomedicos.add(new Biomedico(nome, idade, cpf, sexo, "Biomédico", matricula, crbio));
        System.out.println("Biomédico cadastrado com sucesso.");
    }

    private void cadastrarEnfermeiro() {
        System.out.println("===== CADASTRO DE ENFERMEIRO =====");
        String nome = lerTexto("Nome: ");
        int idade = lerInteiro("Idade: ");
        String cpf = lerTexto("CPF: ");
        String sexo = lerTexto("Sexo: ");
        String matricula = lerTexto("Matrícula: ");
        String coren = lerTexto("COREN: ");
        enfermeiros.add(new Enfermeiro(nome, idade, cpf, sexo, "Enfermeiro", matricula, coren));
        System.out.println("Enfermeiro cadastrado com sucesso.");
    }

    private void adicionarBolsaPeloMenu() {
        try {
            String tipo = lerTexto("Tipo sanguíneo da bolsa: ");
            LocalDate dataColeta = lerDataObrigatoriaNaoFutura("Data da coleta (DD-MM-AAAA): ");
            banco.adicionarBolsa(new BolsaSangue(tipo, dataColeta));
        } catch (TipoSanguineoException | DadosInvalidosException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listarPorTipo() {
        String tipo = lerTexto("Tipo sanguíneo para busca: ");
        banco.listarTipo(tipo);
    }

    private void registrarDoacao() {
        if (doadores.isEmpty()) {
            System.out.println("Cadastre pelo menos um doador primeiro.");
            return;
        }
        if (enfermeiros.isEmpty()) {
            System.out.println("Cadastre pelo menos um enfermeiro primeiro.");
            return;
        }

        Doador doador = escolherDoador();
        Enfermeiro enfermeiro = escolherEnfermeiro();
        if (doador == null || enfermeiro == null) return;

        try {
            LocalDate dataColeta = lerDataObrigatoriaNaoFutura("Data da coleta (DD-MM-AAAA): ");
            BolsaSangue bolsa = new BolsaSangue(doador.getTipoS(), dataColeta);
            Doacao doacao = enfermeiro.coletarSangue(doador, bolsa, dataColeta);
            if (doacao != null) {
                doacao.registrarDoacao();
                banco.adicionarBolsa(doacao.gerarBolsa());
            }
        } catch (TipoSanguineoException | DadosInvalidosException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void criarSolicitacao() {
        if (pacientes.isEmpty()) {
            System.out.println("Cadastre pelo menos um paciente primeiro.");
            return;
        }
        Paciente paciente = escolherPaciente();
        if (paciente == null) return;
        solicitacaoAtual = paciente.solicitarSangue();
        solicitacaoAtual.mostrarDados();
    }

    private void mostrarSolicitacaoAtual() {
        if (solicitacaoAtual == null) {
            System.out.println("Nenhuma solicitação criada.");
            return;
        }
        solicitacaoAtual.mostrarDados();
    }

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
            Biomedico biomedico = escolherBiomedico();
            if (biomedico == null) return;
            int id = lerInteiro("Informe o ID da bolsa: ");
            BolsaSangue bolsa = banco.buscarPorId(id);
            biomedico.setSolicitacao(solicitacaoAtual);
            biomedico.setBolsaEmAnalise(bolsa);
            if (biomedico.liberarBolsa()) {
                solicitacaoAtual.getPaciente().receberBolsa();
                banco.removerBolsa(bolsa);
            }
        } catch (BolsaNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

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
            AtendimentoThread atendimento = new AtendimentoThread("Thread-Atendimento-1", biomedico, solicitacaoAtual, bolsa);
            atendimento.start();
            atendimento.join();
        } catch (BolsaNaoEncontradaException e) {
            System.out.println("Erro: " + e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Thread interrompida.");
        }
    }

    private void salvarEstoque() {
        try {
            arquivo.salvarEstoque(banco);
        } catch (IOException e) {
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
        }
    }

    private void lerArquivo() {
        try {
            arquivo.mostrarArquivo();
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo: " + e.getMessage());
        }
    }

    private void gerarRelatorioGeral() {
        if (administradores.isEmpty()) {
            System.out.println("Cadastre pelo menos um administrador primeiro.");
            return;
        }
        Administrador administrador = escolherAdministrador();
        if (administrador != null) administrador.gerarRelatorioGeral(banco);
    }

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

    private void listarFuncionarios() {
        System.out.println("===== FUNCIONÁRIOS CADASTRADOS =====");
        listarListaFuncionarios("Administradores", administradores);
        listarListaFuncionarios("Biomédicos", biomedicos);
        listarListaFuncionarios("Enfermeiros", enfermeiros);
    }

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

    private Doador escolherDoador() {
        listarNomesDoadores();
        int posicao = lerInteiro("Escolha o doador: ") - 1;
        if (posicao < 0 || posicao >= doadores.size()) {
            System.out.println("Doador inválido.");
            return null;
        }
        return doadores.get(posicao);
    }

    private Paciente escolherPaciente() {
        listarNomesPacientes();
        int posicao = lerInteiro("Escolha o paciente: ") - 1;
        if (posicao < 0 || posicao >= pacientes.size()) {
            System.out.println("Paciente inválido.");
            return null;
        }
        return pacientes.get(posicao);
    }

    private Administrador escolherAdministrador() {
        listarNomesFuncionarios(administradores);
        int posicao = lerInteiro("Escolha o administrador: ") - 1;
        if (posicao < 0 || posicao >= administradores.size()) {
            System.out.println("Administrador inválido.");
            return null;
        }
        return administradores.get(posicao);
    }

    private Biomedico escolherBiomedico() {
        listarNomesFuncionarios(biomedicos);
        int posicao = lerInteiro("Escolha o biomédico: ") - 1;
        if (posicao < 0 || posicao >= biomedicos.size()) {
            System.out.println("Biomédico inválido.");
            return null;
        }
        return biomedicos.get(posicao);
    }

    private Enfermeiro escolherEnfermeiro() {
        listarNomesFuncionarios(enfermeiros);
        int posicao = lerInteiro("Escolha o enfermeiro: ") - 1;
        if (posicao < 0 || posicao >= enfermeiros.size()) {
            System.out.println("Enfermeiro inválido.");
            return null;
        }
        return enfermeiros.get(posicao);
    }

    private void listarNomesDoadores() {
        System.out.println("===== ESCOLHA UM DOADOR =====");
        for (int i = 0; i < doadores.size(); i++) {
            System.out.println((i + 1) + " - " + doadores.get(i).getNome() + " (" + doadores.get(i).getTipoS() + ")");
        }
    }

    private void listarNomesPacientes() {
        System.out.println("===== ESCOLHA UM PACIENTE =====");
        for (int i = 0; i < pacientes.size(); i++) {
            System.out.println((i + 1) + " - " + pacientes.get(i).getNome() + " (" + pacientes.get(i).getTipoS() + ")");
        }
    }

    private void listarNomesFuncionarios(List<? extends Funcionario> funcionarios) {
        System.out.println("===== ESCOLHA UM FUNCIONÁRIO =====");
        for (int i = 0; i < funcionarios.size(); i++) {
            System.out.println((i + 1) + " - " + funcionarios.get(i).getNome() + " | Matrícula: " + funcionarios.get(i).getMatricula());
        }
    }

    private String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

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
