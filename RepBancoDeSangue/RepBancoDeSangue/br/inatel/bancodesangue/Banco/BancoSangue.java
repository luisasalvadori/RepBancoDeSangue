package br.inatel.bancodesangue.Banco;
import br.inatel.bancodesangue.Exceptions.BolsaNaoEncontradaException;
import br.inatel.bancodesangue.Sangue.BolsaSangue;
import java.util.List;

public class BancoSangue {
    private String nome;
    private List<BolsaSangue> estoque;

    public BancoSangue(String nome, List<BolsaSangue> estoque) {
        this.nome = nome;
        this.estoque = estoque;
    }

    public void adicionarBolsa(BolsaSangue bolsa){ //bolsa = bolsa p/ ser adicionada
        estoque.add(bolsa);//adicionando a nova bolsa na lista
        System.out.println("A bolsa foi adicionada!");
    }
    public void removerBolsa(BolsaSangue bolsa) throws BolsaNaoEncontradaException { //bolsa = bolsa a ser Removida
        if (estoque.contains(bolsa)){
            estoque.remove(bolsa);
            System.out.println("A bolsa foi removida!");
        }else
            throw new BolsaNaoEncontradaException("A bolsa não existe no estoque");
    }
    public void listarTipo(String tipo){
        //TERMINAR DE PENSAR ESSA PARTE
    }
    public String getNome() {
        return nome;
    }
    public List<BolsaSangue> getEstoque() {
        return estoque;
    }
}
