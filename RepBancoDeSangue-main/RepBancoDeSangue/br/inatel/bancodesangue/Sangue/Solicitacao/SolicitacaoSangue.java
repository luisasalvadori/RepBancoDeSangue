package br.inatel.bancodesangue.Sangue.Solicitacao;

import br.inatel.bancodesangue.Pessoa.Paciente.Paciente;
import br.inatel.bancodesangue.Sangue.Doacao.Doacao;

import java.time.LocalDate;

public class SolicitacaoSangue {
    private Paciente paciente;
    private LocalDate datalocalizacao;

    public SolicitacaoSangue(Paciente paciente, LocalDate datalocalizacao) {
        this.paciente = paciente;
        this.datalocalizacao = datalocalizacao;
    }

    public boolean varificarValidade(Doacao doacao){

    }
}
