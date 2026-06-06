package br.inatel.bancodesangue.Util;

import br.inatel.bancodesangue.Exceptions.DadosInvalidosException;
import br.inatel.bancodesangue.Exceptions.TipoSanguineoException;

import java.time.LocalDate;

//responsavel pela validação de dados
public class ValidadorDados {
    private static final String[] TIPO_SANGUINEOS_VALIDOS = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"}; //vetor que contem os tipos de sangue aceitos pelo sistema

    private ValidadorDados() {}

    //verifica se o tipo sanguineo informado é valido
    public static void validarTipoSanguineo(String tipoS) throws TipoSanguineoException {
        if (tipoS == null || tipoS.isBlank()){
            throw new TipoSanguineoException("Tipo sanguíneo não pode ser vazio");
        }

        for (String tipoValido : TIPO_SANGUINEOS_VALIDOS) { //percorre a lista de tipos sanguineos validos
            if (tipoValido.equalsIgnoreCase(tipoS.trim())) {
                return; 
            }
        }

        throw new TipoSanguineoException(
                "Tipo sanguíneo inválido: " + tipoS + ". Use apenas: O+, O-, A+, A-, B+, B-, AB+ ou AB-."
        );
    }

    //verifica se uma data informada não está no futuro
    public static void validarDataNaoFutura(LocalDate data, String nomeCampo) throws DadosInvalidosException {
        if (data != null && data.isAfter(LocalDate.now())) {
            throw new DadosInvalidosException(nomeCampo + " não pode ser uma data futura.");
        }
    }

    //retorna uma cópia da lista de tipos sanguíneos válidos
    public static String[] getTipoSanguineosValidos(){ return TIPO_SANGUINEOS_VALIDOS.clone();}
}
