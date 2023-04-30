package com.kshitij.abuseipdbwrapper.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BasicUtils {

    public static Date toDate(String s) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(s);
    }

    public static void sendRequest() {

    }

}
