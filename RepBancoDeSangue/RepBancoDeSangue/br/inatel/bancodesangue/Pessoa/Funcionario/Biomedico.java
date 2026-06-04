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
            System.out.println("Defina uma bolsa e uma solicitação antes do teste.");
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
        if(verificarBolsa() && testeCompatibilidade()){ // se não tem bolsa definida, não tem o que liberar
            bolsaEmAnalise.liberar()
            System.out.println("Bolsa do tipo " + bolsaEmAnalise.getTipoS() + " LIBERADA pelo biomédico " + getNome() + " (CRBio: " + registroProfissional + ")." );
            return true;
        }
        System.out.println("Bolsa NÃO liberada.");
            return falsa;
        }
    }

    public validarDados(){
        return super.validarDados() && registroProfissional != null && !registroProfissional.isBlack();
    }

    // método para mostrar as informações de pessoa, funcionário e as extras do biomédico
    @Override
    public void mostrarDados() {
        System.out.println("===== BIOMÉDICO =====")
        super.mostrarDados(); // imprime os dados de Funcionario (cargo, matricula, etc.)
        System.out.println("Registro profissional (CRBio): " + registroProfissional);
        System.out.println("Bolsa em análise: " + (bolsaEmAnalise == null ? "nenhuma" : bolsaEmAnalise.getTipoS()));
    }

    // define qual bolsa o biomédico vai analisar
    public void setBolsaEmAnalise(BolsaSangue bolsaEmAnalise) {
        this.bolsaEmAnalise = bolsaEmAnalise;
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
