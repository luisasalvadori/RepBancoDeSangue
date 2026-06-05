package br.inatel.bancodesangue.Banco;
import br.inatel.bancodesangue.Exceptions.BolsaNaoEncontradaException;
import br.inatel.bancodesangue.Sangue.BolsaSangue;
import java.util.ArrayList;
import java.util.List;

public class BancoSangue {
    private String nome;
    private List<BolsaSangue> estoque; //lista que guarda todas as bolsas

    public BancoSangue(String nome) { //apenas nome como parâmetro no construtor. poderia ter passado o estoque também, mas não haveria garantia que ele existiria
        this.nome = nome;
        this.estoque = new ArrayList<>(); //cria uma lista vazia
    }

    public void adicionarBolsa(BolsaSangue bolsa){ 
        estoque.add(bolsa);//adicionando a nova bolsa na lista
        System.out.println("A bolsa foi adicionada!");
    }
    public void removerBolsa(BolsaSangue bolsa) throws BolsaNaoEncontradaException { 
        if (estoque.contains(bolsa)){
            estoque.remove(bolsa);
            System.out.println("A bolsa foi removida!");
        }else
            throw new BolsaNaoEncontradaException("A bolsa não existe no estoque");
    }
    public BolsaSangue buscarPorId(int id) throws BolsaNaoEncontradaException {
        for (BolsaSangue bolsa : estoque) {
            if (bolsa.getIdBolsa() == id) {
                return bolsa;}
        }
        throw new BolsaNaoEncontradaException("Bolsa com ID " + id + " não encontrada.");
    }
    public void listarTipo(String tipo) {
        boolean encontrou = false;
        for (BolsaSangue bolsa : estoque) {
            if (bolsa.getTipoS().equalsIgnoreCase(tipo)) {
                bolsa.mostrarDados();
                encontrou = true;
            }
        }
        if (!encontrou) { //se percorreu a lista e não encontrou o tipo especificado, 'encontrou' continua false e precisa ser invertido
            System.out.println("Nenhuma bolsa encontrada para o tipo " + tipo + ".");}
    }
    public int contarPorTipo(String tipo) {
        int quantidade = 0;
        for (BolsaSangue bolsa : estoque) {
            if (bolsa.getTipoS().equalsIgnoreCase(tipo)){
                quantidade++;}
        }
        return quantidade;
    }

    public void listarQuantidadePorTipo() {
        System.out.println("===== QUANTIDADE POR TIPO SANGUÍNEO =====");
        for (String tipo : BolsaSangue.getTiposValidos()) {
            System.out.println(tipo + ": " + contarPorTipo(tipo) + " bolsa(s)");
        }
        System.out.println("Total: " + getTotalBolsas() + " bolsa(s)");
    }

    public void listarEstoque() {
        System.out.println("===== ESTOQUE DO BANCO " + nome + " =====");
        if (estoque.isEmpty()) {
            System.out.println("Estoque vazio.");
        } else{
            for (BolsaSangue bolsa : estoque){
                bolsa.mostrarDados();}}
    }

    public String getNome() { 
        return nome; }
    public List<BolsaSangue> getEstoque() { 
        return estoque; }
    public int getTotalBolsas() {
        return estoque.size(); }
}
