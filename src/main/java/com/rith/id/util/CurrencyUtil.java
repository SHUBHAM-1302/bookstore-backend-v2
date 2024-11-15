package com.rith.id.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtil {

    public static String toUSD(BigDecimal value){
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return numberFormat.format(value);
    }

    public static BigDecimal fromUSD(String value){
        value = value.replaceAll("[^\\d.]]","");
        return new BigDecimal(value);
    }

}
