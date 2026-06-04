package br.inatel.bancodesangue.Util;

import br.inatel.bancodesangue.Exceptions.DadosInvalidosException;
import br.inatel.bancodesangue.Exceptions.TipoSanguineoException;

import java.time.LocalDate;

public class ValidadorDados {
    private static final String[] TIPO_SANGUINEOS_VALIDOS = {"O+", "O-", "A+", "A-", "B+", "B-", "AB+", "AB-"};

    private ValidadorDados() {}

    public static void validarTipoSanguineo(String tipoS) throws TipoSanguineoException {
        if (tipoS == null || tipoS.isBlank()){
            throw new TipoSanguineoException("Tipo sanguíneo não pode ser vazio");
        }

        for (String tipoValido : TIPO_SANGUINEOS_VALIDOS) {
            if (tipoValido.equalsIgnoreCase(tipoS.trim())) {
                return;
            }
        }

        throw new TipoSanguineoException(
                "Tipo sanguíneo inválido: " + tipoS + ". Use apenas: O+, O-, A+, A-, B+, B-, AB+ ou AB-."
        );
    }

    public static void validarDataNaoFutura(LocalDate data, String nomeCampo) throws DadosInvalidosException {
        if (data != null && data.isAfter(LocalDate.now())) {
            throw new DadosInvalidosException(nomeCampo + " não pode ser uma data futura.");
        }
    }

    public static String[] getTipoSanguineosValidos(){ return TIPO_SANGUINEOS_VALIDOS.clone();}
}
