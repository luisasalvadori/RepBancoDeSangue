package br.inatel.bancodesangue.Sangue.Doacao;

import br.inatel.bancodesangue.Pessoa.Doador.Doador;
import br.inatel.bancodesangue.Pessoa.Funcionario.Enfermeiro;
import br.inatel.bancodesangue.Sangue.BolsaSangue;
import br.inatel.bancodesangue.Util.DataUtil;

import java.time.LocalDate;

public class Doacao {
    private LocalDate datacoleta;
    // doador responsavel pela doacao
    private Doador doador;
    // enfermeiro que realizou a coleta
    private Enfermeiro enfermeiro;
    // bolsa de sangue gerada durante a doacao
    private BolsaSangue bolsa;

    public Doacao(LocalDate datacoleta, Doador doador, Enfermeiro enfermeiro, BolsaSangue bolsa) {
        this.datacoleta = datacoleta;
        this.doador = doador;
        this.enfermeiro = enfermeiro;
        this.bolsa = bolsa;
    }
    // retorna a bolsa de sangue associada à doacao
    public BolsaSangue gerarBolsa(){
        return bolsa;
    }
    // registra a doacao exibindo a data da coleta
    public void registrarDoacao(){// utiliza DataUtil para formatar a data antes de exibir
        System.out.println("Doacao registrada em: " + DataUtil.formatar(datacoleta));

    }

    
}
