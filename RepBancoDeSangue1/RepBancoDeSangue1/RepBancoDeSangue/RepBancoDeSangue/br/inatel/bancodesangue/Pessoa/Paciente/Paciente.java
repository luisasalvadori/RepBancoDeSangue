package br.inatel.bancodesangue.Pessoa.Paciente;

import br.inatel.bancodesangue.Exceptions.TipoSanguineoException;
import br.inatel.bancodesangue.Pessoa.Pessoa;
import br.inatel.bancodesangue.Sangue.Solicitacao.SolicitacaoSangue;
import br.inatel.bancodesangue.Util.ValidadorDados;

import java.time.LocalDate;

//paciente herda de pessoa
public class Paciente extends Pessoa {
    //atributos específicos de paciente
    private String tipoS;
    private int nivelUrgencia;
    private double quantSangue;
    private int bolsasRecebidas;
    private String diagnostico;

    //construtor de paciente 
    public Paciente(String nome, int idade, String cpf, String sexo, String tipoS, int nivelUrgencia, double quantSangue, int bolsasRecebidas, String diagnostico) throws TipoSanguineoException {
        super(nome, idade, cpf, sexo); //chama o construtor da classe mãe
        ValidadorDados.validarTipoSanguineo(tipoS); // valida se o tipo sanguíneo informado existe
        this.tipoS = tipoS.trim().toUpperCase(); // remove espaços e padroniza em letras maiúsculas 
        this.nivelUrgencia = nivelUrgencia;
        this.quantSangue = quantSangue;
        this.bolsasRecebidas = bolsasRecebidas;
        this.diagnostico = diagnostico;
    }

    public SolicitacaoSangue solicitarSangue(){ //metodo para criar a solicitação de sangue para o paciente (criada com o próprio paciente e data atual)
        return new SolicitacaoSangue(this, LocalDate.now());
    }

    public int calcularprioridade(){ //metodo para calcular a prioridade do paciente com base no nivel de urgencia
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

    //método que registra a quantidade de bolsas recebidas
    public void receberBolsa(){

        bolsasRecebidas++;
    }

    //sobrescrita do método para verificar se os dados especificos estao corretos
    @Override
    public boolean validarDados() {
        // idade deve ser maior que 0, tipo sanguíneo não pode ser vazio ou null, o nivel de urgencia deve ser maior ou igual a 0 e a quantidade de sangue deve ser maior que 0
        return getIdade()>0 && tipoS!=null && !tipoS.isBlank() && nivelUrgencia>=0 && nivelUrgencia<=10 && quantSangue>0;
    }

    //sobrescrita do método que exibe as informações completas do paciente
    @Override
    public void mostrarDados(){
        System.out.println("===== PACIENTE =====");
        super.mostrarDados(); //exibe os dados comuns herdados da superclasse 
        System.out.println("Tipo sanguíneo: " + tipoS);
        System.out.println("Diagnóstico: " + diagnostico);
        System.out.println("Nível de urgência: " + nivelUrgencia);
        System.out.println("Prioridade: " + calcularprioridade());
        System.out.println("Bolsas necessárias: " + quantSangue);
        System.out.println("Bolsas recebidas: " + bolsasRecebidas);
    }

    //getter
    public String getTipoS() {
        return tipoS;
    }
}
