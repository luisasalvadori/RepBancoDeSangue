package br.inatel.bancodesangue.Pessoa.Paciente;

import br.inatel.bancodesangue.Exceptions.TipoSanguineoException;
import br.inatel.bancodesangue.Pessoa.Pessoa;
import br.inatel.bancodesangue.Sangue.Solicitacao.SolicitacaoSangue;
import br.inatel.bancodesangue.Util.ValidadorDados;

import java.time.LocalDate;

public class Paciente extends Pessoa {
    private String tipoS;
    private int nivelUrgencia;
    private double quantSangue;
    private int bolsasRecebidas;
    private String diagnostico;

    public Paciente(String nome, int idade, String cpf, String sexo, String tipoS, int nivelUrgencia, double quantSangue, int bolsasRecebidas, String diagnostico) throws TipoSanguineoException {
        super(nome, idade, cpf, sexo);
        ValidadorDados.validarTipoSanguineo(tipoS);
        this.tipoS = tipoS.trim().toUpperCase();
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
        else{
            return 3;
        }
        //1-alta prioridade, 2-média prioridade, 3-baixa prioridade
    };

    public void receberBolsa(){

        bolsasRecebidas++;
    }
    
    @Override
    public boolean validarDados() {
        return getIdade()>0 && tipoS!=null && !tipoS.isBlank() && nivelUrgencia>=0 && nivelUrgencia<=10 && quantSangue>0;
    }

    @Override
    public void mostrarDados(){
        System.out.println("===== PACIENTE =====");
        super.mostrarDados();
        System.out.println("Tipo sanguíneo: " + tipoS);
        System.out.println("Diagnóstico: " + diagnostico);
        System.out.println("Nível de urgência: " + nivelUrgencia);
        System.out.println("Prioridade: " + calcularprioridade());
        System.out.println("Bolsas necessárias: " + quantSangue);
        System.out.println("Bolsas recebidas: " + bolsasRecebidas);
    }

    public String getTipoS() {
        return tipoS;
    }
}
