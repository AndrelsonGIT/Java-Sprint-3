package org.salesforce.service;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataRegional {

    public DataRegional() {
    }

    public String converterParaFormatoBrasileiro(LocalDateTime dataParaSerConvertida){
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return formatador.format(dataParaSerConvertida);
    }
}
