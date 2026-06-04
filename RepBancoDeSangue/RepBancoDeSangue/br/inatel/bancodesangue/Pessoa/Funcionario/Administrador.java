package br.inatel.bancodesangue.Pessoa.Funcionario;

import br.inatel.bancodesangue.Banco.BancoSangue;

public class Administrador extends Funcionario {
    public Administrador(String nome, int idade, String cpf, String sexo, String cargo, String matricula) {
        super(nome, idade, cpf, sexo, cargo, matricula);
    }

    public void gerarRelatorioGeral(BancoSangue banco) {
        System.out.println("Relatório geral gerado pelo administrador " + getNome());
        banco.listarEstoque();
    }
    
    @Override
    public void mostrardados() {
        System.out.println("== Informações do administrador ==");
        super.mostrarDados();
    }
}
