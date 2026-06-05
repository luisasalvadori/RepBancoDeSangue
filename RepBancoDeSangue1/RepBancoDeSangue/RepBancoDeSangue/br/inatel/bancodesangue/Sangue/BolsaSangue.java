package br.inatel.bancodesangue.Sangue;

import br.inatel.bancodesangue.Exceptions.DadosInvalidosException;
import br.inatel.bancodesangue.Exceptions.TipoSanguineoException;
import br.inatel.bancodesangue.Util.DataUtil;
import br.inatel.bancodesangue.Util.ValidadorDados;

import java.time.LocalDate;

public class BolsaSangue {
    private static int proximoId = 1; //todas as bolsas compartilham o mesmo contador

    private int idBolsa;
    private String tipoS;
    private LocalDate dataColeta;
    private LocalDate dataValidade;
    private boolean liberada;

    public BolsaSangue(String tipoS, LocalDate dataColeta) throws TipoSanguineoException, DadosInvalidosException {
        ValidadorDados.validarTipoSanguineo(tipoS);
        ValidadorDados.validarDataNaoFutura(dataColeta, "Data de coleta da bolsa");
        this.idBolsa = proximoId++;
        this.tipoS = tipoS.trim().toUpperCase();
        this.dataColeta = dataColeta;
        this.dataValidade = dataColeta.plusDays(35);
        this.liberada = false;
    }

    public boolean verificarValidade(LocalDate dataUso) {
        return !dataUso.isAfter(dataValidade); 
    }

    public void liberar() { 
        this.liberada = true; }

    public void mostrarDados() {
        System.out.print("ID: " + idBolsa + " | Tipo: " + tipoS + " | Coleta: " + DataUtil.formatar(dataColeta) + " | Validade: " + DataUtil.formatar(dataValidade) + " | Liberada: " );
        if (liberada) {
            System.out.println("Sim");
        }else {
            System.out.println("Não");}
    }

    @Override
    public String toString() { //converte o objeto para String para facilitar a exibição
        return idBolsa + ";" + tipoS + ";" + DataUtil.formatar(dataColeta) + ";" + DataUtil.formatar(dataValidade) + ";" + liberada;
    }

    public static String[] getTiposValidos() { return ValidadorDados.getTipoSanguineosValidos(); }
    public int getIdBolsa() { return idBolsa; }
    public String getTipoS() { return tipoS; }
    public LocalDate getDataColeta() { return dataColeta; }
    public LocalDate getDataValidade() { return dataValidade; }
    public boolean isLiberada() { return liberada; } 
}
    
