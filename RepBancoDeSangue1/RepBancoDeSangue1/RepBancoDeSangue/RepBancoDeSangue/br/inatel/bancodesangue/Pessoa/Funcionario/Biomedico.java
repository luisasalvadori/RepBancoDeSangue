package br.inatel.bancodesangue.Pessoa.Funcionario;

import br.inatel.bancodesangue.Sangue.BolsaSangue;
import br.inatel.bancodesangue.Sangue.Solicitacao.SolicitacaoSangue;
import java.time.LocalDate;

// a classe Biomedico representa um funcionário responsável por analisar bolsas de sangue e liberar ou não a bolsa para um paciente.
public class Biomedico extends Funcionario {
    private String registroProfissional; // registro profissional do biomédico, como o CRBio.
    private BolsaSangue bolsaEmAnalise; // bolsa que está sendo analisada pelo biomédico no momento.
    private SolicitacaoSangue solicitacao; // solicitação de sangue que será usada para testar a compatibilidade entre o paciente e a bolsa analisada.

    /// método construtor - recebe os dados básicos de pessoa, os dados de funcionário e o registro profissional específico do biomédico.
    public Biomedico(String nome, int idade, String cpf, String sexo, String cargo, String matricula, String registroProfissional) {
        super(nome, idade, cpf, sexo, cargo, matricula);
        this.registroProfissional = registroProfissional;
    }

    // método responsável por verificar se a bolsa em análise ainda está válida
    public boolean verificarBolsa() {
        // se nenhuma bolsa foi definida para análise, não é possível verificar.
        if (bolsaEmAnalise == null) {
            System.out.println("Nenhuma bolsa em análise no momento.");
            return false;
        }
        // chama o método verificarValidade() da classe BolsaSangue, passando a data atual como referência.
        boolean valida = bolsaEmAnalise.verificarValidade(LocalDate.now());
        System.out.println(valida ? "Bolsa dentro do prazo de validade." : "Bolsa fora do prazo de validade.");
        return valida;
    }

    // método responsável por testar a compatibilidade entre a bolsa em análise e a solicitação de sangue do paciente.
    public boolean testeCompatibilidade() {
        // para realizar o teste, é necessário ter uma bolsa e uma solicitação.
        if (bolsaEmAnalise == null || solicitacao == null) {
            System.out.println("Defina uma bolsa e uma solicitação antes do teste.");
            return false;
        }
        // a verificação de compatibilidade é feita pela solicitação, pois ela possui os dados do paciente que precisa receber sangue.
        boolean compativel = solicitacao.verificarCompatibilidade(bolsaEmAnalise);
        // exibe o resultado do teste de compatibilidade.
        System.out.println(compativel ? "Teste de compatibilidade aprovado." : "Teste de compatibilidade reprovado.");
        return compativel; // retorna true se a bolsa for compatível e false se não for.
    }
    // método responsável por liberar ou não uma bolsa de sangue
    public boolean liberarBolsa() {
        // para liberar, a bolsa precisa estar válida e ser compatível com a solicitação.
        if (verificarBolsa() && testeCompatibilidade()) {
            bolsaEmAnalise.liberar(); // altera o status da bolsa para liberada.
            System.out.println("Bolsa do tipo " + bolsaEmAnalise.getTipoS() + " LIBERADA pelo biomédico " + getNome() + " (CRBio: " + registroProfissional + ").");
            return true;
        }
        System.out.println("Bolsa NÃO liberada.");
        return false;
    }

    // sobrescrita do método validarDados()
    @Override
    public boolean validarDados() { // verifica se os dados herdados de Funcionario são válidos e também se o registro profissional foi preenchido.
        return super.validarDados() && registroProfissional != null && !registroProfissional.isBlank();
    }
    // sobrescrita do método mostrarDados()
    @Override
    public void mostrarDados() { // exibe os dados completos do biomédico.
        System.out.println("===== BIOMÉDICO =====");
        super.mostrarDados(); // mostra os dados herdados de Pessoa e Funcionario, como nome, idade, CPF, sexo, cargo e matrícula.
        System.out.println("Registro profissional (CRBio): " + registroProfissional);
        // mostra o tipo da bolsa que está em análise
        System.out.println("Bolsa em análise: " + (bolsaEmAnalise == null ? "nenhuma" : bolsaEmAnalise.getTipoS())); // se não houver bolsa definida, mostra "nenhuma".
    }

    // define qual bolsa será analisada pelo biomédico.
    public void setBolsaEmAnalise(BolsaSangue bolsaEmAnalise) { this.bolsaEmAnalise = bolsaEmAnalise; }
    // define qual solicitação será usada no teste de compatibilidade.
    public void setSolicitacao(SolicitacaoSangue solicitacao) { this.solicitacao = solicitacao; }

}
