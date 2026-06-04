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
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("CPF: " + cpf);
        System.out.println("Sexo: " + sexo);
    };

    public abstract boolean validarDados();

    public String getNome() {
        return nome;
    }
    public int getIdade(){
        return idade;
    }
    public String getSexo(){
        return sexo;
    }
}
