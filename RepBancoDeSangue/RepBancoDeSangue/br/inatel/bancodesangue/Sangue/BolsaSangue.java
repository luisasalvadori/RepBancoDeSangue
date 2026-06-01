package br.inatel.bancodesangue.Sangue;
import br.inatel.bancodesangue.Exceptions.TipoSanguineoException;

import java.time.LocalDate;

public class BolsaSangue {
    private String tipoS;
    private LocalDate datacoleta;
    private LocalDate datavalidade;
    private String []tiposValidos = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};


    public BolsaSangue(String tipoS, LocalDate datacoleta, LocalDate datavalidade) throws TipoSanguineoException {
        boolean validado = false;
        for (String tipoValido : tiposValidos){
            if (tipoValido.equalsIgnoreCase(tipoS)){
                this.tipoS = tipoS;
                validado = true;
            }
        }
        if (!validado){
            throw new TipoSanguineoException("Tipo sanguíneo inexistente");
        }

        this.datacoleta = datacoleta;
        this.datavalidade = datavalidade;
    }
    public boolean verificarValidade(LocalDate data) { //data do uso da bolsa
        return data.isBefore(datavalidade); //true = dentro da validade; false = vencida
    }
    public String getTipoS(){
        return tipoS;
    }
    public LocalDate getDataColeta(){
        return datacoleta;
    }
    public LocalDate getDataValidade() {
        return datavalidade;
    }
}
