package br.inatel.bancodesangue.Pessoa.Doador;

import br.inatel.bancodesangue.Pessoa.Pessoa;

import java.time.LocalDate;

public class Doador extends Pessoa {
    protected String tipoS;
    protected double peso;
    protected boolean aptoDoacao;
    protected LocalDate ultimaDoacao;
    protected LocalDate proximaDoacao;
    protected int bolsasDoacao;

    public Doador(String nome, int idade, String cpf, String sexo, String tipoS, double peso, boolean aptoDoacao, LocalDate ultimaDoacao, LocalDate proximaDoacao, int bolsasDoacao) {
        super(nome, idade, cpf, sexo);
        this.tipoS = tipoS;
        this.peso = peso;
        this.aptoDoacao = validarDados();
        this.ultimaDoacao = ultimaDoacao;
        this.proximaDoacao = calcularProximaDoacao();
        this.bolsasDoacao = bolsasDoacao;
    }

    @Override
    public boolean validarDados(){
        if(idade<16 || idade>69){
            return false;
        }
        if(peso<50){
            return false;
        }
        if(idade>60 && bolsasDoacao == 0) {
            return false;
        }
        return true;
    }

    public LocalDate calcularProximaDoacao(){
        if(ultimaDoacao == null){
            return LocalDate.now();
        }
        if(sexo.equalsIgnoreCase("masculino")){
            return ultimaDoacao.plusDays(60);
        }
        else{
            return ultimaDoacao.plusDays(90);
        }
    }

    @Override
    public void mostrarDados(){
        System.out.println("Informações do doador:");
        super.mostrarDados();
        System.out.println("Peso: " + this.peso);
        System.out.println("Tipo sanguíneo: " + this.tipoS);
        System.out.println("Última doação: " + this.ultimaDoacao);
        System.out.println("Próxima doação: " + this.proximaDoacao);
        System.out.println("Quantidade de bolsas doadas: " + this.bolsasDoacao);
        System.out.println("Apto para doação: " + this.aptoDoacao);
    }
}
