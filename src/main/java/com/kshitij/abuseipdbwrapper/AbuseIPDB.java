package com.kshitij.abuseipdbwrapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.kshitij.abuseipdbwrapper.exceptions.AbuseIPDBApiException;
import com.kshitij.abuseipdbwrapper.models.BlacklistData;
import com.kshitij.abuseipdbwrapper.models.CheckData;
import com.kshitij.abuseipdbwrapper.models.ReportsData;
import com.kshitij.abuseipdbwrapper.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class AbuseIPDB {

    private final String apiKey;
    private final HttpUtils httpUtils;
    private final Gson gson;

    public AbuseIPDB(String apiKey) {
        this.apiKey = apiKey;
        this.httpUtils = new HttpUtils(apiKey);
        this.gson = new GsonBuilder().serializeNulls().create();
    }

    public CheckData checkIp(String ip, boolean verbose) throws AbuseIPDBApiException {
        Map<String, String> params = new HashMap<>();
        params.put("ipAddress", ip);
        if (verbose) params.put("verbose", null);
        return gson.fromJson(
                httpUtils.sendGet("/check", params).get("data"),
                CheckData.class
        );
    }

    public ReportsData getReports(String ip) {
        Map<String, String> params = new HashMap<>();
        params.put("ipAddress", ip);
        return gson.fromJson(
                httpUtils.sendGet("/reports", params).get("data"),
                ReportsData.class
        );
    }

    public ReportsData getReports(String ip, int maxAgeInDays, int page, int perPage) {
        Map<String, String> params = new HashMap<>();
        params.put("ipAddress", ip);
        params.put("maxAgeInDays", String.valueOf(maxAgeInDays));
        params.put("page", String.valueOf(page));
        params.put("perPage", String.valueOf(perPage));
        return gson.fromJson(
                httpUtils.sendGet("/reports", params).get("data"),
                ReportsData.class
        );
    }

    public BlacklistData getBlacklists() {
        Map<String, String> params = new HashMap<>();
        return getBlacklistData(params);
    }

    public BlacklistData getBlacklists(int confidenceMinimum) {
        Map<String, String> params = new HashMap<>();
        params.put("confidenceMinimum", String.valueOf(confidenceMinimum));
        return getBlacklistData(params);
    }

    private BlacklistData getBlacklistData(Map<String, String> params) {
        JsonObject raw = httpUtils.sendGet("/blacklist", params);
        JsonObject processed = new JsonObject();
        processed.add("generatedAt", raw.get("meta").getAsJsonObject().get("generatedAt"));
        processed.add("blacklists", raw.get("data"));
        return gson.fromJson(
                processed,
                BlacklistData.class
        );
    }

}
