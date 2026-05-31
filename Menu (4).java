package br.inatel.bancodesangue.Pessoa.Funcionario;

import br.inatel.bancodesangue.Pessoa.Pessoa;

public class Funcionario extends Pessoa {
    protected String cargo;
    protected String matricula;
    protected String turno;
    protected int qtdatendimentos;

    public Funcionario(String cargo, String matricula, String turno, int qtdatendimentos) {
        this.cargo = cargo;
        this.matricula = matricula;
        this.turno = turno;
        this.qtdatendimentos = qtdatendimentos;
    }

    public void registraratendimento(){

    }

    public void emitiralerta(){

    }

    public void mostrardados(){

    }
}
