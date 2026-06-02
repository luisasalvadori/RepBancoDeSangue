package br.inatel.bancodesangue.Sangue.Solicitacao;

import br.inatel.bancodesangue.Pessoa.Paciente.Paciente;
import br.inatel.bancodesangue.Sangue.BolsaSangue;
import br.inatel.bancodesangue.Sangue.Compatibilidade.Compativel;

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

        String tipoPaciente = paciente.getTipoS(); // pega o tipo sanguíneo do paciente
        String tipoBolsa = bolsa.getTipoS(); // pega o tipo sanguíneo da bolsa

        if(tipoPaciente == null || tipoBolsa == null){ // se algum dos tipos estiver nulo retorna falso
            return false;
        }

        // verifica o tipo sanguíneo do paciente (quem vai receber o sangue) e é listado para cada caso, quais tipos de bolsa são aceitos
        switch(tipoPaciente.toUpperCase()){
            case "A+":
                return tipoBolsa.equalsIgnoreCase("O-") // "equalsIgnoreCase" compara ignorando maiúsculas/minúsculas
                        || tipoBolsa.equalsIgnoreCase("O+")
                        || tipoBolsa.equalsIgnoreCase("A-")
                        || tipoBolsa.equalsIgnoreCase("A+");
            case "A-":
                return tipoBolsa.equalsIgnoreCase("O-")
                        || tipoBolsa.equalsIgnoreCase("A-");
            case "B+":
                return tipoBolsa.equalsIgnoreCase("O-")
                        || tipoBolsa.equalsIgnoreCase("O+")
                        || tipoBolsa.equalsIgnoreCase("B-")
                        || tipoBolsa.equalsIgnoreCase("B+");
            case "B-":
                return tipoBolsa.equalsIgnoreCase("O-")
                        || tipoBolsa.equalsIgnoreCase("B-");
            case "AB+":
                return true; // receptor universal — aceita qualquer tipo sanguíneo
            case "AB-":
                return tipoBolsa.equalsIgnoreCase("O-")
                        || tipoBolsa.equalsIgnoreCase("A-")
                        || tipoBolsa.equalsIgnoreCase("B-")
                        || tipoBolsa.equalsIgnoreCase("AB-");
            case "O+":
                return tipoBolsa.equalsIgnoreCase("O-")
                        || tipoBolsa.equalsIgnoreCase("O+");
            case "O-":
                return tipoBolsa.equalsIgnoreCase("O-");
            default:
                // se o tipo do paciente não for nenhum dos acima, é inválido
                System.out.println("Tipo sanguíneo do paciente não reconhecido: " + tipoPaciente);
                return false;
        }
    }

    public void mostrarDados() {
        System.out.println("===== SOLICITAÇÃO DE SANGUE =====");
        System.out.println("Paciente: " + paciente.getNome());
        System.out.println("Tipo sanguíneo do paciente: " + paciente.getTipoS());
        System.out.println("Data da solicitação: " + dataSolicitacao);
    }

    public Paciente getPaciente() { return paciente; } // retorna o paciente armazenado
    public void setPaciente(Paciente paciente) { this.paciente = paciente; } // substitui o paciente

    public LocalDate getDataSolicitacao() { return dataSolicitacao; } // retorna a data da solicitação
    public void setDataSolicitacao(LocalDate dataSolicitacao) { this.dataSolicitacao = dataSolicitacao; } // substitui a data

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
