package com.kshitij.abuseipdbwrapper.utils;

import java.net.http.HttpClient;

public class HttpUtils {

    private final String URL = "https://api.abuseipdb.com/api/v2";
    private final String apiKey;
    private final HttpClient client;

    public HttpUtils(String apiKey) {
        this.apiKey = apiKey;
        this.client = HttpClient.newHttpClient();
    }

}
