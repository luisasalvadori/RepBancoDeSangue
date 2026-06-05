package br.inatel.bancodesangue.Util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DataUtil {
    private static final DateTimeFormatter FORMATO_BR = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter FORMATO_BR_BARRA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static String formatar(LocalDate data)
    {
        if (data == null) return "não informada";
        return data.format(FORMATO_BR);
    }
    public static LocalDate converter(String texto)
    {
        if (texto == null || texto.isBlank()) {
            throw new DateTimeParseException("Data vazia", "", 0);
        }
        String entrada = texto.trim();
        try{
            return LocalDate.parse(entrada, FORMATO_BR);
        }catch (DateTimeParseException e) {
            return LocalDate.parse(entrada, FORMATO_BR_BARRA);
        }
    }
}
