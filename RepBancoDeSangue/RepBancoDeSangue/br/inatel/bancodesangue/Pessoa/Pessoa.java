package br.inatel.bancodesangue.Pessoa;

public abstract class Pessoa {
    protected String nome;
    protected int idade;
    protected String cpf;
    protected String sexo;

    public Pessoa(String nome, int idade, String cpf, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.sexo = sexo;
    }

    public void mostrarDados(){
        System.out.println("Nome: " + this.nome);
        System.out.println("Idade: " + this.idade);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Sexo: " + this.sexo);
    };

    public abstract boolean validarDados();

    public abstract void mostrardados();
}
