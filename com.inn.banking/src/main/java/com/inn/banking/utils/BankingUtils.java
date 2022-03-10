package com.inn.banking.utils;

import java.util.Date;

public class BankingUtils {

    private BankingUtils() {
    }

    public static String getUUID() {
        Date date = new Date();
        long accno = date.getTime();
        return String.valueOf(accno);
    }

   
}
