package br.inatel.bancodesangue.Pessoa.Funcionario;

import br.inatel.bancodesangue.Sangue.BolsaSangue;
import br.inatel.bancodesangue.Sangue.Solicitacao.SolicitacaoSangue;

import java.time.LocalDate;

public class Biomedico extends Funcionario { // herda de funcionário
    private String registroProfissional;
    private BolsaSangue bolsaEmAnalise; // atributo que guarda a bolsa que está sendo analisada
    private SolicitacaoSangue solicitacao;

    public Biomedico(String nome, int idade, String cpf, String sexo, String cargo, String matricula, String registroProfissional, BolsaSangue bolsaEmAnalise) {
        super(nome, idade, cpf, sexo, cargo, matricula);
        this.registroProfissional = registroProfissional;
        this.bolsaEmAnalise = bolsaEmAnalise;
    }

    // método para verificar se a bolsa em análise está dentro do prazo de validade
    public boolean verificarBolsa(){
        if(bolsaEmAnalise == null){ // se não tem bolsa definida, não tem o que verificar
            System.out.println("Nenhuma bolsa em análise no momento.");
            return false;
        }
        // chama o método verificarValidade()
        boolean valida = bolsaEmAnalise.verificarValidade(LocalDate.now());
        // se retornar true a bolsa está dentro do prazo de validade, se retornar false está fora
        if(valida){
            System.out.println("Bolsa verificada: dentro do prazo de validade.");
        } else {
            System.out.println("Bolsa verificada: FORA do prazo de validade.");
        }

        return valida;
    }

    // método para verificar se o tipo sanguíneo está preenchido corretamente
    public boolean testeCompatibilidade(){
        if(bolsaEmAnalise == null || solicitacao == null){ // se não tem bolsa definida, não tem o que testar
            System.out.println("Nenhuma bolsa em análise para teste de compatibilidade.");
            return false;
        }
        String tipo = bolsaEmAnalise.getTipoS(); // pega o tipo sanguíneo da bolsa
        boolean compativel = solicitacao.verificarCompatibilidade(bolsaEmAnalise);
        if(compativel){
            System.out.println("Teste de compatibilidade concluído. Tipo sanguíneo " + tipo);
        } else {
            System.out.println("Teste de compatibilidade FALHOU: bolsa incompatível com o paciente.");
        }

        return compativel;
    }

    // método que libera a bolsa para uso clínico
    public void liberarBolsa(){
        if(bolsaEmAnalise == null){ // se não tem bolsa definida, não tem o que liberar
            System.out.println("Não há bolsa em análise para liberar.");
            return;
        }
        if (solicitacao == null) {
            System.out.println("Não há solicitação vinculada para liberar a bolsa.");
            return;
        }
        boolean bolsaValida = verificarBolsa();
        boolean bolsaCompativel = testeCompatibilidade();
        if (bolsaValida && bolsaCompativel) { // se a bolsa tiver sido verificada e passar pelo teste
            System.out.println("Bolsa do tipo " + bolsaEmAnalise.getTipoS()
                    + " LIBERADA pelo biomédico " + getNome()
                    + " (CRBio: " + getRegistroProfissional() + ").");
            // limpa a bolsa em análise (ela foi liberada, não está mais retida)
            bolsaEmAnalise = null;
        } else {
            System.out.println("Bolsa NÃO pode ser liberada — falhou na verificação.");
        }
    }

    // método para mostrar as informações de pessoa, funcionário e as extras do biomédico
    @Override
    public void mostrarDados() {
        super.mostrarDados(); // imprime os dados de Funcionario (cargo, matricula, etc.)
        System.out.println("Registro profissional (CRBio): " + registroProfissional);
        System.out.println("Bolsa em análise: " + (bolsaEmAnalise != null ? bolsaEmAnalise.getTipoS() : "nenhuma"));
    }

    public String getRegistroProfissional() {
        return registroProfissional; // retorna o número de registro
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional; // atualiza o registro
    }

    public BolsaSangue getBolsaEmAnalise() {
        return bolsaEmAnalise; // retorna a bolsa que está em análise
    }

    // define qual bolsa o biomédico vai analisar
    public void setBolsaEmAnalise(BolsaSangue bolsaEmAnalise) {
        this.bolsaEmAnalise = bolsaEmAnalise;
        System.out.println("Bolsa do tipo "
                + (bolsaEmAnalise != null ? bolsaEmAnalise.getTipoS() : "null")
                + " recebida para análise.");
    }

    public SolicitacaoSangue getSolicitacao() {
        return solicitacao;
    }

    public void setSolicitacao(SolicitacaoSangue solicitacao) {
        this.solicitacao = solicitacao;
    }

    // TOSTRING - extra, nao sei se vamos usar ou nao (deixar aqui por enquanto)
    /*
     -> Retorna uma representação em texto do objeto Biomedico.
     -> Chamado automaticamente em System.out.println(biomedico).

    @Override
    public String toString() {
        return "Biomedico{" +
                "registroProfissional='" + registroProfissional + '\'' +
                ", cargo='" + getCargo() + '\'' +
                ", matricula='" + getMatricula() + '\'' +
                '}';
    }*/
}
