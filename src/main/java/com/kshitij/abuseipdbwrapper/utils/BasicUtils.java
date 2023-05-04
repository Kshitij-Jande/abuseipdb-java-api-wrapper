package com.kshitij.abuseipdbwrapper.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BasicUtils {

    public static Date toDate(String s) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(s);
    }

    public static String joinIntArray(int[] array) {
        return String.join(",", Arrays.stream(array).mapToObj(String::valueOf).toArray(String[]::new));
    }

    public static String joinIntArray(int[] array, String delimiter) {
        return String.join(delimiter, Arrays.stream(array).mapToObj(String::valueOf).toArray(String[]::new));
    }

}
