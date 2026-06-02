package br.inatel.bancodesangue.Pessoa.Funcionario;

import br.inatel.bancodesangue.Pessoa.Doador.Doador;
import br.inatel.bancodesangue.Sangue.BolsaSangue;
import br.inatel.bancodesangue.Sangue.Doacao.Doacao;

import java.time.LocalDate;

public class Enfermeiro extends Funcionario{
    protected String corem;

    public Enfermeiro(String nome, int idade, String cpf, String sexo, String cargo, String matricula, String corem) {
        super(nome, idade, cpf, sexo, cargo, matricula);
        this.corem = corem;
    }

    public void cadastrarDoador(){
        System.out.println("doador cadastrado com sucesso");
    }

    public boolean entrevistarDoador(Doador doador){
        return doador.validarDados();
    }

    public Doacao coletarSangue(Doador doador, BolsaSangue bolsa){
        if(!entrevistarDoador(doador))
        {
            System.out.println("Doador nao apto para doacao");
            return null;
        }

        return new Doacao(LocalDate.now(),doador,this,bolsa);

    }

    @Override
    public String toString() {
        return "Enfermeiro{" +
                "corem='" + corem + '\'' +
                '}';
    }
}
