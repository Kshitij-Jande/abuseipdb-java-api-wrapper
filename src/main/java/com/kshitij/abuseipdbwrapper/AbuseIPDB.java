package com.kshitij.abuseipdbwrapper;

public class AbuseIPDB {

    private String apiKey;
    private final String URL = "https://api.abuseipdb.com/api/v2";

    public AbuseIPDB(String apiKey) {
        this.apiKey = apiKey;
    }

}
