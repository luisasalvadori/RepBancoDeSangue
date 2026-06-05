package br.inatel.bancodesangue.Pessoa;

//classe abstrata (representa uma pessoa no sistema)
public abstract class Pessoa {
    //atributos comuns aos tipos de pessoa (Funcionário, Paciente, Doador)
    private String nome;
    private int idade;
    private String cpf;
    private String sexo;

    //construtor da classe
    public Pessoa(String nome, int idade, String cpf, String sexo) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.sexo = sexo;
    }

    //método que mostra as informações comuns 
    public void mostrarDados(){
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("CPF: " + cpf);
        System.out.println("Sexo: " + sexo);
    };

    //método abstrato que valida os dados da pessoa (é implementado nas subclasses)
    public abstract boolean validarDados();

    //getters que permitem acessar os atributos de Pessoa
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
