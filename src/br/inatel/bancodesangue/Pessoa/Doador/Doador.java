package br.inatel.bancodesangue.Pessoa.Doador;

import br.inatel.bancodesangue.Pessoa.Pessoa;

import java.time.LocalDate;

public class Doador extends Pessoa {
    protected String tipoS;
    protected double peso;
    protected String sexo;
    protected boolean aptoDoacao;
    protected LocalDate ultimaDoacao;
    protected LocalDate proximaDoaca;
    protected int bolsasDoacao;

    public Doador(String tipoS, double peso, String sexo, boolean aptoDoacao, LocalDate ultimaDoacao, LocalDate proximaDoaca, int bolsasDoacao) {
        this.tipoS = tipoS;
        this.peso = peso;
        this.sexo = sexo;
        this.aptoDoacao = aptoDoacao;
        this.ultimaDoacao = ultimaDoacao;
        this.proximaDoaca = proximaDoaca;
        this.bolsasDoacao = bolsasDoacao;
    }

    public boolean verificarAptidao(){

    }

    public LocalDate calcularProximaDoacao(){

    }
}
