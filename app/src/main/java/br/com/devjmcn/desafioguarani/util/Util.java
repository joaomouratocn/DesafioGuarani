package br.com.devjmcn.desafioguarani.util;

import java.text.NumberFormat;
import java.util.Locale;

public class Util {
    public static NumberFormat numberFormat(){
        return NumberFormat.getCurrencyInstance(Locale.getDefault());
    }
}
