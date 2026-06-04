package br.inatel.bancodesangue.Sangue.Doacao;

import br.inatel.bancodesangue.Pessoa.Doador.Doador;
import br.inatel.bancodesangue.Pessoa.Funcionario.Enfermeiro;
import br.inatel.bancodesangue.Sangue.BolsaSangue;
import br.inatel.bancodesangue.Util.DataUtil;
import java.time.LocalDate;

public class Doacao {
    private LocalDate datacoleta;
    private Doador doador;
    private Enfermeiro enfermeiro;
    private BolsaSangue bolsa;

    public Doacao(LocalDate datacoleta, Doador doador, Enfermeiro enfermeiro, BolsaSangue bolsa) {
        this.datacoleta = datacoleta;
        this.doador = doador;
        this.enfermeiro = enfermeiro;
        this.bolsa = bolsa;
    }

    public BolsaSangue gerarBolsa(){
        return bolsa;
    }

    public void registrarDoacao(){
        System.out.println("Doacao registrada em: " + DataUtil.formatar(dataColeta));

    }

    
}
