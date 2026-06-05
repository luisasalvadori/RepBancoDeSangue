package br.inatel.bancodesangue.Pessoa.Funcionario;

import br.inatel.bancodesangue.Exceptions.TipoSanguineoException;
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

    public Doador cadastrarDoador(String nome, int idade,String cpf,String sexo, String tipoS, double peso, LocalDate ultimaDoacao){
        int numeroDoacoes;

        if(ultimaDoacao == null) {
            numeroDoacoes = 0;
        }else {
            numeroDoacoes = 1;
        }
        try {
            Doador doador = new Doador(nome,idade,cpf,sexo,tipoS,peso,ultimaDoacao,numeroDoacoes);

            System.out.println("Doador cadastrado com sucesso");
            return doador;
        } catch (TipoSanguineoException | DadosInvalidosException e){
            System.out.println("Erro no cadastro do doador: " + e.getMessage());
            return null;
        }
    }


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

    public Doacao coletarSangue(Doador doador, BolsaSangue bolsa) throws DadosInvalidosException {
        return coletarSangue(doador, bolsa, LocalDate.now());
    }

    public Doacao coletarSangue(Doador doador, BolsaSangue bolsa, LocalDate dataColeta) throws DadosInvalidosException {
        
        if(!entrevistarDoador(doador))
        {
            return null;
        }
        doador.registrarDoacao(dataColeta);
        return new Doacao(dataColeta, doador, this, bolsa);
    }

        @Override
        public boolean validarDados() { return super.validarDados() && corem != null && !corem.isBlank(); }

        @Override
        public void mostrarDados() {
            System.out.print("===== ENFERMEIRO =====\n");
            super.mostrarDados();
            System.out.println("COREN: " + corem);
        }
    }


    

