package br.inatel.bancodesangue.Sangue.Solicitacao;

import br.inatel.bancodesangue.Pessoa.Paciente.Paciente;
import br.inatel.bancodesangue.Sangue.BolsaSangue;
import br.inatel.bancodesangue.Sangue.Compatibilidade.Compativel;
import br.inatel.bancodesangue.Util.DataUtil;
import java.time.LocalDate;

public class SolicitacaoSangue implements Compativel {
    private Paciente paciente; // paciente que está solicitando o sangue
    private LocalDate dataSolicitacao; // data em que o pedido foi feito

    // método construtor
    public SolicitacaoSangue(Paciente paciente, LocalDate dataSolicitacao) {
        this.paciente = paciente; // paciente que está solicitando o sangue
        this.dataSolicitacao = dataSolicitacao; // data em que o pedido foi feito
    }
    
    // método que compara o tipo sanguíneo do paciente com o da bolsa e retorna true se a bolsa puder ser usada pelo paciente
    @Override
    public boolean verificarCompatibilidade(BolsaSangue bolsa){
        if (bolsa == null || paciente == null) { // se a bolsa ou o paciente não existirem, retorna false
            return false;
        }
        String receptor = paciente.getTipoS().toUpperCase(); // pega o tipo sanguíneo do paciente
        String doador = bolsa.getTipoS().toUpperCase(); // pega o tipo sanguíneo da bolsa 

        return switch(receptor){ // verifica o tipo sanguíneo do paciente (quem vai receber o sangue) e é listado para cada caso, quais tipos de bolsa são aceitos
            case "A+" -> doador.equals("O-") || doador.equals("O+") || doador.equals("A-") || doador.equals("A+");
            case "A-" -> doador.equals("O-") || doador.equals("A-");
            case "B+" -> doador.equals("O-") || doador.equals("O+") || doador.equals("B-") || doador.equals("B+");
            case "B-" -> doador.equals("O-") || doador.equals("B-");
            case "AB+" -> true; // receptor universal — aceita qualquer tipo sanguíneo
            case "AB-" -> doador.equals("O-") || doador.equals("A-") || doador.equals("B-") || doador.equals("AB-");
            case "O+" -> doador.equals("O-") || doador.equals("O+");
            case "O-" -> doador.equals("O-");
            default -> false; // se o tipo do paciente não for nenhum dos acima, é inválido
                //System.out.println("Tipo sanguíneo do paciente não reconhecido: " + tipoPaciente);
        };
    }

    public void mostrarDados() {
        System.out.println("===== SOLICITAÇÃO DE SANGUE =====");
        System.out.println("Paciente: " + paciente.getNome());
        System.out.println("Tipo sanguíneo do paciente: " + paciente.getTipoS());
        System.out.println("Data da solicitação: " + DataUtil.formatar(dataSolicitacao));
        System.out.println("Prioridade: " + paciente.calcularPrioridade());
    }

    public Paciente getPaciente() { return paciente; } // retorna o paciente armazenado
    
    // TOSTRING - extra, nao sei se vamos usar ou nao (deixar aqui por enquanto)
    /*
     -> toString é chamado automaticamente quando tentamos imprimir o objeto.
     -> Ex: System.out.println(solicitacao) vai chamar este método.
     -> Retorna uma String legível com os dados principais do objeto.
     -> O operador ternário "paciente != null ? ... : ..."
     -> evita erro caso o paciente seja nulo.

    @Override
    public String toString() {
        return "SolicitacaoSangue{" +
                "paciente=" + (paciente != null ? paciente.getNome() : "null") +
                ", dataSolicitacao=" + dataSolicitacao +
                '}';
    }
    */
}
