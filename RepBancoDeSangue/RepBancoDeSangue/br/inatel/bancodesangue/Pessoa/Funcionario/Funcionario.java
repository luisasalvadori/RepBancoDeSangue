package br.inatel.bancodesangue.Pessoa.Funcionario;

import br.inatel.bancodesangue.Pessoa.Pessoa;

public class Funcionario extends Pessoa {
    protected String cargo;
    protected String matricula;

    public Funcionario(String nome, int idade, String cpf, String sexo, String cargo, String matricula) {
        super(nome, idade, cpf, sexo);

        this.cargo = cargo;
        this.matricula = matricula;
    }

    public void emitiralerta(){
        // exibindo uma mensagem para quando for necessário emitir um alerta
        System.out.println("Alerta emitido pelo funcionario " + nome);
    }

    @Override
    public boolean validarDados() {
        if(cargo == null || cargo.isEmpty()){
            return false;
        }
        if(matricula == null || matricula.isEmpty()){
            return false;
        }

        return true;
    }

    @Override
    public void mostrardados(){
        System.out.println("Informações do funcionário: ");

        super.mostrarDados();

        System.out.println("Cargo: " + this.cargo);
        System.out.println("Matrícula: " + this.matricula);
    }
}
