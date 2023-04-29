package com.kshitij.abuseipdbwrapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utility {

    public static Date toDate(String s) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(s);
    }

    public static void sendRequest() {

    }

}
