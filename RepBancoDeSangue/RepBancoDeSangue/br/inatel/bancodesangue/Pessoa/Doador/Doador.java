package br.inatel.bancodesangue.Pessoa.Doador;

import br.inatel.bancodesangue.Pessoa.Pessoa;
import br.inatel.bancodesangue.Exceptions.DadosInvalidosException;
import br.inatel.bancodesangue.Exceptions.TipoSanguineoException;
import br.inatel.bancodesangue.Util.DataUtil;
import br.inatel.bancodesangue.Util.ValidadorDados;
import java.time.LocalDate;

public class Doador extends Pessoa {
    protected String tipoS;
    protected double peso;
    protected boolean aptoDoacao;
    protected LocalDate ultimaDoacao;
    protected LocalDate proximaDoacao;
    protected int bolsasDoadas;

    public Doador(String nome, int idade, String cpf, String sexo, String tipoS, double peso, boolean aptoDoacao, LocalDate ultimaDoacao, LocalDate proximaDoacao, int bolsasDoacao) {
        super(nome, idade, cpf, sexo);
        ValidarDados.validarTipoSanguineo(tipoS);
        ValidadorDados.validarDataNaoFutura(ultimaDoacao, "Última doação");
        this.tipoS = tipoS.trim().toUpperCase();
        this.peso = peso;
        this.ultimaDoacao = ultimaDoacao;
        this.proximaDoacao = calcularProximaDoacao();
        this.bolsasDoadas = bolsasDoadas;
        this.aptoDoacao = validarDados();
    }

    @Override
    public boolean validarDados(){
        if(getIdade()<16 || getIdade()>69){
            return false;
        }
        if(peso<50){
            return false;
        }
        if(getIdade()>60 && bolsasDoacao == 0) {
            return false;
        }
        return LocalDate.now().isEqual(proximaDoacao) || LocalDate.now().isAfter(proximaDoacao);
    }

    public LocalDate calcularProximaDoacao(){
        if(ultimaDoacao == null){
            return LocalDate.now();
        }
        if(getSexo().equalsIgnoreCase("masculino")){
            return ultimaDoacao.plusDays(60);
        }
        else{
            return ultimaDoacao.plusDays(90);
        }
    }

    public void registrarDoacao() throws DadosInvalidosException {
        ValidarDados.validarDataNaoFutura(dataColeta, "Data de coleta da doação");
        bolsasDoadas++;
        ultimaDoacao = dataColeta;
        proximaDoacao = calcularProximaDoacao();
        aptoDoacao = false;
    }

    @Override
    public void mostrarDados(){
        System.out.println("===== DOADOR =====");
        super.mostrarDados();
        System.out.println("Tipo sanguíneo: " + tipoS);
        System.out.println("Peso: " + peso);
        System.out.println("Última doação: " + DataUtil.formatar(ultimaDoacao));
        System.out.println("Próxima doação: " + DataUtil.formatar(proximaDoacao));
        System.out.println("Quantidade de bolsas doadas: " + bolsasDoadas);
        System.out.println("Apto para doação: " + aptoDoacao ? "sim":"não");
    }

    public String getTipoS(){
        return tipoS;
    }

    public boolean isAptoDoacao(){
        return aptoDoacao;
    }
}
