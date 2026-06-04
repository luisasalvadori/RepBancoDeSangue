package br.inatel.bancodesangue.Pessoa.Funcionario;

import br.inatel.bancodesangue.Pessoa.Doador.Doador;
import br.inatel.bancodesangue.Sangue.BolsaSangue;
import br.inatel.bancodesangue.Sangue.Doacao.Doacao;
import br.inatel.bancodesangue.Exceptions.DadosInvalidosException;
import java.time.LocalDate;

public class Enfermeiro extends Funcionario{
    private String corem;

    public Enfermeiro(String nome, int idade, String cpf, String sexo, String cargo, String matricula, String corem) {
        super(nome, idade, cpf, sexo, cargo, matricula);
        this.corem = corem;
    }

    public void cadastrarDoador(Doador doador){
        System.out.println("Doador " + doador.getnome() + " cadastrado com sucesso");
    }

    public boolean entrevistarDoador(Doador doador){
        boolean apto = doador.validarDados();
        if(apto == true)
        {
            System.out.println("Doador apto para doacao")
        }else{
            System.out.println("Doador nao apto para doacao");
        }
        return apto;
    }

    public Doacao coletarSangue(Doador doador, BolsaSangue bolsa) throws DadosInvalidadosException {
        return coletarSangue(doador, bolsa, LocalDate.now());
    }

    public Doacao coletarSangue(Doador doador, BolsaSangue bolsa, LocalDate dataColeta) throws DadosInvalidadosException {
        
        if(!entrevistarDoador(doador))
        {
            return null;
        }
        doador.registrarDoacao(dataColeta);
        return new Doacao(dataColeta, doador, this, bolsa);
    }

        @Override
        public boolean validarDados() { return super.validarDados() && coren != null && !coren.isBlank(); }

        @Overrride
        public void mostrarDados() {
            System.out.print("===== ENFERMEIRO =====");
            super.mostrarDados();
            System.out.println("COREN: " + coren);
        }
    }
    


    

