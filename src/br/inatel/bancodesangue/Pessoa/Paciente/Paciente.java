package br.inatel.bancodesangue.Pessoa.Paciente;

import br.inatel.bancodesangue.Pessoa.Pessoa;

public class Paciente extends Pessoa {
    protected String tipoS;
    protected int nivelurgencia;
    protected double qtdsnague;
    protected int bolsasrecebidas;
    protected String diagnostico;

    public Paciente(String tipoS, int nivelurgencia, double qtdsnague, int bolsasrecebidas, String diagnostico) {
        this.tipoS = tipoS;
        this.nivelurgencia = nivelurgencia;
        this.qtdsnague = qtdsnague;
        this.bolsasrecebidas = bolsasrecebidas;
        this.diagnostico = diagnostico;
    }

    public void solicitarSangue(){

    }

    public int calcularprioridade(){

    }
}
