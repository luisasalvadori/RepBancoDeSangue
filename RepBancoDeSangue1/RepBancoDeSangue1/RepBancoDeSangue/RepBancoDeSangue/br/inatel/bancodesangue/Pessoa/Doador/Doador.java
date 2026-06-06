package br.inatel.bancodesangue.Pessoa.Doador;

import br.inatel.bancodesangue.Exceptions.TipoSanguineoException;
import br.inatel.bancodesangue.Pessoa.Pessoa;
import br.inatel.bancodesangue.Exceptions.DadosInvalidosException;
import br.inatel.bancodesangue.Util.DataUtil;
import br.inatel.bancodesangue.Util.ValidadorDados;

import java.time.LocalDate;

//classe doador herda de Pessoa (ou seja, é um tipo de pessoa)
public class Doador extends Pessoa {
    //atributos específicos de doador
    private String tipoS;
    private double peso;
    private boolean aptoDoacao;
    private LocalDate ultimaDoacao;
    private LocalDate proximaDoacao;
    private int bolsasDoadas;

    //construtor de doador
    public Doador(String nome, int idade, String cpf, String sexo, String tipoS, double peso, LocalDate ultimaDoacao, int bolsasDoadas) throws TipoSanguineoException, DadosInvalidosException {
        //chama o construtor da classe mãe
        super(nome, idade, cpf, sexo);
        ValidadorDados.validarTipoSanguineo(tipoS); // valida se o tipo sanguíneo informado existe
        ValidadorDados.validarDataNaoFutura(ultimaDoacao, "Última doação"); // valida se a última doação não é uma data futura
        this.tipoS = tipoS.trim().toUpperCase(); // remove espaços e padroniza em letras maiúsculas 
        this.peso = peso;
        this.ultimaDoacao = ultimaDoacao;
        this.proximaDoacao = calcularProximaDoacao(); //chama o método para calcular automaticamente a próxima data de doação
        this.bolsasDoadas = bolsasDoadas;
        this.aptoDoacao = validarDados();// chama o método para validar se o doador está apto para doação 
    }

    //sobrescrita do metodo para verificar se o doador atende as regras para doar
    @Override
    public boolean validarDados(){
        //caso a idade ou peso forem negativos (dado inválido), chama a exception
        if (getIdade() < 0) {
            throw new DadosInvalidosException("Idade não pode ser negativa.");
        }

        if (peso <= 0) {
            throw new DadosInvalidosException("Peso inválido.");
        }
        // para doar sangue, a idade deve estar entre 16 e 69 anos, peso maior que 50kg 
        if(getIdade()<16 || getIdade()>69){
            return false;
        }
        if(peso<50){
            return false;
        }
        //caso o doador tenha mais que 60 anos, deve ter pelo menos 1 bolsa doada para ser apto a doaçao
        if(getIdade()>60 && bolsasDoadas == 0) {
            return false;
        }
        //verifica se a data atual já chegou ou passou da data permitida (se ainda não chegou, o doador não pode doar novamente 
        return LocalDate.now().isEqual(proximaDoacao) || LocalDate.now().isAfter(proximaDoacao);
    }

    //calcula a data da próxima doação
    public LocalDate calcularProximaDoacao(){ 
        // se a pessoa nunca doou, a próxima doação é a data atual
        if(ultimaDoacao == null){
            return LocalDate.now();
        }
        // se a pessoa é homem, a proxima doação é 60 dias depois da ultima
        if(getSexo().equalsIgnoreCase("masculino")){
            return ultimaDoacao.plusDays(60);
        }
        else{ //caso a pessoa for mulher, a proxima doação é 90 dias depois da ultima
            return ultimaDoacao.plusDays(90);
        }
    }

    //registra uma nova doação (recebe a data da coleta e atualiza os dados relacionados à doação
    public void registrarDoacao(LocalDate dataColeta) throws DadosInvalidosException {
        ValidadorDados.validarDataNaoFutura(dataColeta, "Data de coleta da doação" ); //impede que a data de coleta seja registrada no futuro
        bolsasDoadas++;
        ultimaDoacao = dataColeta;
        proximaDoacao = calcularProximaDoacao(); //recalcula a data da próxima doação
        aptoDoacao = false; //após doar, o doar deixa de estar apto(temporariamente)
    }

    //sobrescrita do método que exibe os dados completos do doador
    @Override
    public void mostrarDados(){
        System.out.println("===== DOADOR =====");
        super.mostrarDados(); //exibe os dados comuns herdados da superclasse 
        System.out.println("Tipo sanguíneo: " + tipoS);
        System.out.println("Peso: " + peso);
        //datas são formatadas para aparecer no formato comum
        System.out.println("Última doação: " + DataUtil.formatar(ultimaDoacao));
        System.out.println("Próxima doação: " + DataUtil.formatar(proximaDoacao));
        System.out.println("Quantidade de bolsas doadas: " + bolsasDoadas);
        System.out.println("Apto para doação: " + (aptoDoacao ? "sim":"não"));
    }

    // getters
    public String getTipoS(){
        return tipoS;
    }

    public boolean isAptoDoacao(){
        return aptoDoacao;
    }
}
