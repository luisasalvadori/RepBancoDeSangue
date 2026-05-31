package br.inatel.bancodesangue.Pessoa.Funcionario;

public class Administrador extends Funcionario{
    protected int nivelacesso;

    public Administrador(String cargo, String matricula, String turno, int qtdatendimentos, int nivelacesso) {
        super(cargo, matricula, turno, qtdatendimentos);
        this.nivelacesso = nivelacesso;
    }

    public void gerarRelatorioGeral(){

    }

    public boolean verificarEstoque(){

    }

    public void gerenciarFuncionarios(){

    }
}
