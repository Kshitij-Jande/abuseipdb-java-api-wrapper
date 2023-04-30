package com.kshitij.abuseipdbwrapper;

import com.kshitij.abuseipdbwrapper.utils.HttpUtils;

public class AbuseIPDB {

    private String apiKey;
    private HttpUtils httpUtils;

    public AbuseIPDB(String apiKey) {
        this.apiKey = apiKey;
        this.httpUtils = new HttpUtils(apiKey);
    }

}
