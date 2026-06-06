package br.inatel.bancodesangue.Pessoa.Funcionario;

import br.inatel.bancodesangue.Exceptions.TipoSanguineoException;
import br.inatel.bancodesangue.Pessoa.Doador.Doador;
import br.inatel.bancodesangue.Sangue.BolsaSangue;
import br.inatel.bancodesangue.Sangue.Doacao.Doacao;
import br.inatel.bancodesangue.Exceptions.DadosInvalidosException;
import java.time.LocalDate;

public class Enfermeiro extends Funcionario{
    private String coren;

    public Enfermeiro(String nome, int idade, String cpf, String sexo, String cargo, String matricula, String coren) {
        //inicia os atributos herdados de funcionario
        super(nome, idade, cpf, sexo, cargo, matricula);
        this.coren = coren;
    }
    // metodo responsavel por cadastrar um novo doador
    public Doador cadastrarDoador(String nome, int idade,String cpf,String sexo, String tipoS, double peso, LocalDate ultimaDoacao){
        int numeroDoacoes;
        // Se nao houver data da ultima doacao
        // considera que o doador nunca doou
        if(ultimaDoacao == null) {
            numeroDoacoes = 0;
        }else {
            numeroDoacoes = 1;
        }
        //testa se 
        try {
            // cria um novo objeto doador
            Doador doador = new Doador(nome,idade,cpf,sexo,tipoS,peso,ultimaDoacao,numeroDoacoes);

            System.out.println("Doador cadastrado com sucesso");
            return doador;
        } catch (TipoSanguineoException | DadosInvalidosException e){
            // exibe a mensagem de erro caso os dados sejam invalidos
            System.out.println("Erro no cadastro do doador: " + e.getMessage());
            return null;
        }
    }

    // realiza a entrevista do doador verificando se os dados sao validos
    public boolean entrevistarDoador(Doador doador){
        boolean apto = doador.validarDados();
        if(apto == true)
        {
            System.out.println("Doador apto para doacao");
        }else{
            System.out.println("Doador nao apto para doacao");
        }
        return apto;
    }
    // Sobrecarga do metodo coletarsangue
    // utiliza automaticamente a data atual
    public Doacao coletarSangue(Doador doador, BolsaSangue bolsa) throws DadosInvalidosException {
        return coletarSangue(doador, bolsa, LocalDate.now());
    }

    public Doacao coletarSangue(Doador doador, BolsaSangue bolsa, LocalDate dataColeta) throws DadosInvalidosException {
        // verifica se o doador foi aprovado na entrevista
        if(!entrevistarDoador(doador))
        {
            return null;
        }
        // cria e retorna uma nova doacao
        // "this" representa o enfermeiro que realizou a coleta
        doador.registrarDoacao(dataColeta);
        return new Doacao(dataColeta, doador, this, bolsa);
    }
        // validacao dos dados do enfermeiro
        @Override
        public boolean validarDados() { return super.validarDados() && coren != null && !coren.isBlank(); }
        // Exibe os dados do enfermeiro
        //sobrescrita do metodo
        @Override
        public void mostrarDados() {
            System.out.print("===== ENFERMEIRO =====\n");
            super.mostrarDados();
            System.out.println("COREN: " + coren);
        }
    }


    

