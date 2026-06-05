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

    public boolean verificarEstoque(BancoSangue banco) {
        boolean temEstoque = banco.getTotalBolsas() > 0;
        System.out.println("Estoque possui" +  banco.getTotalBolsas() + " bolsa(s).");
        return temEstoque;
    }
    
    @Override
    public void mostrarDados() {
        System.out.println("===== ADMINISTRADOR =====");
        super.mostrarDados();
    }
}
