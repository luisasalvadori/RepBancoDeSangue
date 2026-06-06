package br.inatel.bancodesangue.Threads;

import br.inatel.bancodesangue.Pessoa.Funcionario.Biomedico;
import br.inatel.bancodesangue.Sangue.BolsaSangue;
import br.inatel.bancodesangue.Sangue.Solicitacao.SolicitacaoSangue;

//classe que simula um atendimento simultaneo realizado por um biomedico
public class AtendimentoThread extends Thread {
  private Biomedico biomedico; //biomedico reponsável pela analise da solicitação
  private SolicitacaoSangue solicitacao; //solicitação de sangue que será analisada
  private BolsaSangue bolsa; // bolsa de sangue associada à solicitação

  //construtor da classe 
  public AtendimentoThread(String nomeThread, Biomedico biomedico, SolicitacaoSangue solicitacao, BolsaSangue bolsa) {
    super(nomeThread);
    this.biomedico = biomedico;
    this.solicitacao = solicitacao;
    this.bolsa = bolsa;
  }

  //metodo executado quando a thread é iniciada
  @Override
  public void run() {
    System.out.println("[" + getName() + "] Atendimento iniciado."); //informa o inicio do atendimento 
    biomedico.setSolicitacao(solicitacao); //associa a solicitação ao biomedico
    biomedico.setBolsaEmAnalise(bolsa); //define a bolsa a ser analisada
    biomedico.liberarBolsa(); // analise e liberação da bolsa
    System.out.println("[" + getName() + "] Atendimento finalizado."); //informa o termino do atendimento
  }
}
