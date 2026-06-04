package br.inatel.bancodesangue.Pessoa.Funcionario;

import br.inatel.bancodesangue.Sangue.BolsaSangue;
import br.inatel.bancodesangue.Sangue.Solicitacao.SolicitacaoSangue;
import java.time.LocalDate;

public class Biomedico extends Funcionario {
    private String registroProfissional;
    private BolsaSangue bolsaEmAnalise;
    private SolicitacaoSangue solicitacao;

    public Biomedico(String nome, int idade, String cpf, String sexo, String cargo, String matricula, String registroProfissional) {
        super(nome, idade, cpf, sexo, cargo, matricula);
        this.registroProfissional = registroProfissional;
    }

    public boolean verificarBolsa() {
        if (bolsaEmAnalise == null) {
            System.out.println("Nenhuma bolsa em análise no momento.");
            return false;
        }
        boolean valida = bolsaEmAnalise.verificarValidade(LocalDate.now());
        System.out.println(valida ? "Bolsa dentro do prazo de validade." : "Bolsa fora do prazo de validade.");
        return valida;
    }

    public boolean testeCompatibilidade() {
        if (bolsaEmAnalise == null || solicitacao == null) {
            System.out.println("Defina uma bolsa e uma solicitação antes do teste.");
            return false;
        }
        boolean compativel = solicitacao.verificarCompatibilidade(bolsaEmAnalise);
        System.out.println(compativel ? "Teste de compatibilidade aprovado." : "Teste de compatibilidade reprovado.");
        return compativel;
    }

    public boolean liberarBolsa() {
        if (verificarBolsa() && testeCompatibilidade()) {
            bolsaEmAnalise.liberar();
            System.out.println("Bolsa do tipo " + bolsaEmAnalise.getTipoS() + " LIBERADA pelo biomédico " + getNome() + " (CRBio: " + registroProfissional + ").");
            return true;
        }
        System.out.println("Bolsa NÃO liberada.");
        return false;
    }

    @Override
    public boolean validarDados() {
        return super.validarDados() && registroProfissional != null && !registroProfissional.isBlank();
    }

    @Override
    public void mostrarDados() {
        System.out.println("===== BIOMÉDICO =====");
        super.mostrarDados();
        System.out.println("Registro profissional (CRBio): " + registroProfissional);
        System.out.println("Bolsa em análise: " + (bolsaEmAnalise == null ? "nenhuma" : bolsaEmAnalise.getTipoS()));
    }

    public void setBolsaEmAnalise(BolsaSangue bolsaEmAnalise) { this.bolsaEmAnalise = bolsaEmAnalise; }
    public void setSolicitacao(SolicitacaoSangue solicitacao) { this.solicitacao = solicitacao; }

}
