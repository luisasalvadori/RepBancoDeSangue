package br.inatel.bancodesangue.Util;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

//classe responsavel pela conversao e formatação de datas 
public class DataUtil {
    //formato brasileiro de data
    private static final DateTimeFormatter FORMATO_BR = DateTimeFormatter.ofPattern("dd-MM-yyyy"); 
    private static final DateTimeFormatter FORMATO_BR_BARRA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    //Converte um objeto LocalDate para uma String formatada no padrao brasileiro
    public static String formatar(LocalDate data)
    {
        if (data == null) return "não informada"; //verifica se a data for informada
        return data.format(FORMATO_BR);
    }
    //Converte uma String para um objeto LocalDate.
    public static LocalDate converter(String texto)
    {
        //verifica se a entrada esta vazia ou null
        if (texto == null || texto.isBlank()) {
            throw new DateTimeParseException("Data vazia", "", 0);
        }
        String entrada = texto.trim(); //remove espaços em branco 
        try{
            return LocalDate.parse(entrada, FORMATO_BR); //tenta convertar utilizando o formato com hifen
        }catch (DateTimeParseException e) {
            return LocalDate.parse(entrada, FORMATO_BR_BARRA); //se não converter com hifen, converte com barra
        }
    }
}
