package br.inatel.bancodesangue.Pessoa.Funcionario;

public class Administrador extends Funcionario {
    public Administrador(String nome, int idade, String cpf, String sexo, String cargo, String matricula) {
        super(nome, idade, cpf, sexo, cargo, matricula);
    }

    public void gerarRelatorioGeral() {
        System.out.println("Relatório geral gerado pelo administrador " + nome);
    }

    public boolean verificarEstoque() {
        System.out.println("Administrador " + nome + " verificando o estoque.");
        return true;
    }

    @Override
    public boolean validarDados() {
        if (cargo == null || cargo.isEmpty()) {
            return false;
        }

        if (matricula == null || matricula.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public void mostrardados() {
        System.out.println("Informações do administrador:");

        super.mostrarDados();

        System.out.println("Cargo: " + this.cargo);
        System.out.println("Matrícula: " + this.matricula);
    }
}