package br.inatel.bancodesangue.Pessoa.Funcionario;

public class Biomédico extends Funcionario {
    protected String registroProfissional;

    public Biomédico(String cargo, String matricula, String turno, int qtdatendimentos, String registroProfissional) {
        super(cargo, matricula, turno, qtdatendimentos);
        this.registroProfissional = registroProfissional;
    }

    public boolean verificarBolsa(){

    }

    public boolean testeCompatibilidade(){

    }

    public void liberarBolsa(){

    }
}
