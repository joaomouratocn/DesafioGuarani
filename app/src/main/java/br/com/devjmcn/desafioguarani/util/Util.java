package br.com.devjmcn.desafioguarani.util;

import java.text.NumberFormat;
import java.util.Locale;

public class Util {
    public static String numberFormat(String value){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.getDefault());
        double valor = Double.parseDouble(value);
        return numberFormat.format(valor);
    }
}
