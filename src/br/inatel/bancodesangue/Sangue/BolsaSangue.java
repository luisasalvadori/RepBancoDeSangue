package br.inatel.bancodesangue.Sangue;

import java.time.LocalDate;

public class BolsaSangue {
    private String tipoS;
    private LocalDate datacoleta;
    private LocalDate datavalidade;

    public BolsaSangue(String tipoS, LocalDate datacoleta, LocalDate datavalidade) {
        this.tipoS = tipoS;
        this.datacoleta = datacoleta;
        this.datavalidade = datavalidade;
    }

    public boolean verificarValidade(){

    }
}
