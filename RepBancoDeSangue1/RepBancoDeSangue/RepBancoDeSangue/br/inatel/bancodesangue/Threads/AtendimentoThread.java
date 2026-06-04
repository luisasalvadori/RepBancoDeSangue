package br.inatel.bancodesangue.Threads;

import br.inatel.bancodesangue.Pessoa.Funcionario.Biomedico;
import br.inatel.bancodesangue.Sangue.BolsaSangue;
import br.inatel.bancodesangue.Sangue.Solicitacao.SolicitacaoSangue;

public class AtendimentoThread extends Thread {
  private Biomedico biomedico;
  private SolicitacaoSangue solicitacao;
  private BolsaSangue bolsa;

  public AtendimentoThread(String nomeThread, Biomedico biomedico, SolicitacaoSangue solicitacao, BolsaSangue bolsa) {
    super(nomeThread);
    this.biomedico = biomedico;
    this.solicitacao = solicitacao;
    this.bolsa = bolsa;
  }

  @Override
  public void run() {
    System.out.println("[" + getName() + "] Atendimento iniciado.");
    biomedico.setSolicitacao(solicitacao);
    biomedico.setBolsaEmAnalise(bolsa);
    biomedico.liberarBolsa();
    System.out.println("[" + getName() + "] Atendimento finalizado.");
  }
}
