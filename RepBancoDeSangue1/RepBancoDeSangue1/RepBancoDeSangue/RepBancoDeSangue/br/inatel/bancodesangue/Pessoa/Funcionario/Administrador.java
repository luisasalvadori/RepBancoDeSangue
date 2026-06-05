package br.inatel.bancodesangue.Pessoa.Funcionario;

import br.inatel.bancodesangue.Banco.BancoSangue;

// classe Administrador é uma subclasse de Funcionario, isso significa que ela herda os atributos e métodos de Funcionario
public class Administrador extends Funcionario {

    // construtor da classe Administrador
    public Administrador(String nome, int idade, String cpf, String sexo, String cargo, String matricula) {
        // super para herdar os atributos da superclasse 
        super(nome, idade, cpf, sexo, cargo, matricula);
    }

    // método responsável por gerar um relatório geral do banco de sangue, recebe como parâmetro um objeto BancoSangue
    public void gerarRelatorioGeral(BancoSangue banco) {
        System.out.println("Relatório geral gerado pelo administrador " + getNome());
        // chama o método listarEstoque da classe BancoSangue, para exibir todas as bolsas cadastradas no estoque
        banco.listarEstoque();
    }

    // método responsável por verificar se existe alguma bolsa cadastrada no estoque
    public boolean verificarEstoque(BancoSangue banco) {
        // Verifica se a quantidade total de bolsas no banco é maior que zero
        boolean temEstoque = banco.getTotalBolsas() > 0;
        
        // exibe a quantidade total de bolsas cadastradas no estoque
        System.out.println("Estoque possui" +  banco.getTotalBolsas() + " bolsa(s).");
        
        // retorna o resultado da verificação
        return temEstoque;
    }

    
    // sobrescrita do método mostrarDados(), método já existe na classe Funcionario, mas aqui ele é adaptado para mostrar que os dados pertencem a um Administrador
    @Override
    public void mostrarDados() {
        System.out.println("===== ADMINISTRADOR =====");

        // chama o mostrarDados da superclasse Funcionario, que também pode chamar os dados herdados de Pessoa
        super.mostrarDados();
    }
}
