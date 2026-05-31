package br.inatel.bancodesangue.Pessoa.Funcionario;

import br.inatel.bancodesangue.Sangue.Doacao.Doacao;

public class Enfermeiro extends Funcionario{
    protected String corem;

    public Enfermeiro(String cargo, String matricula, String turno, int qtdatendimentos, String corem) {
        super(cargo, matricula, turno, qtdatendimentos);
        this.corem = corem;
    }

    public void cadastrarDoador(){

    }

    public boolean entrevistarDoador(){

    }

    public Doacao coletarSangue(){

    }
}
