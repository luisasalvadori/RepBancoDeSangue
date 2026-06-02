package br.inatel.bancodesangue.Pessoa.Paciente;

import br.inatel.bancodesangue.Pessoa.Pessoa;
import br.inatel.bancodesangue.Sangue.Solicitacao.SolicitacaoSangue;

import java.time.LocalDate;

public class Paciente extends Pessoa {
    protected String tipoS;
    protected int nivelUrgencia;
    protected double quantSangue;
    protected int bolsasRecebidas;
    protected String diagnostico;

    public Paciente(String nome, int idade, String cpf, String sexo, String tipoS, int nivelUrgencia, double quantSangue, int bolsasRecebidas, String diagnostico) {
        super(nome, idade, cpf, sexo);
        this.tipoS = tipoS;
        this.nivelUrgencia = nivelUrgencia;
        this.quantSangue = quantSangue;
        this.bolsasRecebidas = bolsasRecebidas;
        this.diagnostico = diagnostico;
    }

    public SolicitacaoSangue solicitarSangue(){
        return new SolicitacaoSangue(this, LocalDate.now());
    }

    public int calcularprioridade(){
        if(nivelUrgencia >=8){
            return 1;
        }
        else if(nivelUrgencia>=5 && nivelUrgencia<8){
            return 2;
        }
        else if(nivelUrgencia>=0 && nivelUrgencia<5){
            return 3;
        }
        //1-alta prioridade, 2-média prioridade, 3-baixa prioridade
    }

    @Override
    public boolean validarDados() {

    }

    @Override
    public void mostrarDados(){
        System.out.println("Informações do paciente:");
        super.mostrarDados();
        System.out.println("Tipo sanguíneo: " + this.tipoS);
        System.out.println("Diagnóstico: " + this.diagnostico);
        System.out.println("Nível de urgência: " + this.nivelUrgencia);
        System.out.println("Quantidade de bolsas necessárias: " + this.quantSangue); // esta certo?
        System.out.println("Qauntidade de bolsas recebidas: " + this.bolsasRecebidas);
    }

    public String getTipoS() {
        return tipoS;
    }

    public void setTipoS(String tipoS) {
        this.tipoS = tipoS;
    }
}
