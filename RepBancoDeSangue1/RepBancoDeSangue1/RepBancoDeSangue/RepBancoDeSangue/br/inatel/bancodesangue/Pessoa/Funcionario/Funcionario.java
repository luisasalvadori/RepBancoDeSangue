package br.inatel.bancodesangue.Pessoa.Funcionario;

import br.inatel.bancodesangue.Pessoa.Pessoa;

//classe Funcionario representa um funcionário do banco de sangue, ela herda da classe Pessoa, portanto, todo funcionário também é uma pessoa
public class Funcionario extends Pessoa {
    // cargo do funcionário, se é: Administrador, Biomédico ou Enfermeiro, e matrícula do funcionário dentro do sistema
    private String cargo;
    private String matricula;

     // construtor da classe Funcionario, recebe os dados básicos da pessoa e também os dados específicos do funcionário
    public Funcionario(String nome, int idade, String cpf, String sexo, String cargo, String matricula) {
        // chama o construtor da classe Pessoa para inicializar os atributos herdados
        super(nome, idade, cpf, sexo);

        // inicializa os atributos específicos da classe Funcionario
        this.cargo = cargo;
        this.matricula = matricula;
    }

    // sobrescrita do método validarDados, método verifica se os dados específicos do funcionário estão válidos
    @Override
    public boolean validarDados() {
        // verifica se o cargo está nulo ou vazio, caso esteja, os dados do funcionário são considerados inválidos
        if(cargo == null || cargo.isEmpty()){
            return false;
        }
        // verifica se a matrícula está nula ou vazia, caso esteja, os dados do funcionário são considerados inválidos
        if(matricula == null || matricula.isEmpty()){
            return false;
        }
        
        // se cargo e matrícula estiverem preenchidos, os dados são válidos
        return true;
    }

    // sobrescrita do método mostrarDados, método exibe os dados do funcionário na tela
    @Override
    public void mostrarDados(){
        // chama o método mostrarDados da classe Pessoa, assim, primeiro são exibidos os dados comuns
        super.mostrarDados();

        // depois são exibidos os dados específicos do funcionário
        System.out.println("Cargo: " + cargo);
        System.out.println("Matrícula: " + matricula);
    }

    // getter do cargo e matricula, permite acessar esses dados do funcionário fora da classe
    public String getCargo(){
        return cargo;
    }
    public String getMatricula(){
        return matricula;
    }
}
