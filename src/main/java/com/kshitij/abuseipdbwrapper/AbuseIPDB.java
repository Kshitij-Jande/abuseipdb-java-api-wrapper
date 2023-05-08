package com.kshitij.abuseipdbwrapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.kshitij.abuseipdbwrapper.exceptions.AbuseIPDBApiException;
import com.kshitij.abuseipdbwrapper.models.*;
import com.kshitij.abuseipdbwrapper.utils.BasicUtils;
import com.kshitij.abuseipdbwrapper.utils.HttpUtils;

import java.io.File;
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

    public ReportData reportIp(String ipAddress, int[] categories) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ipAddress);
        params.put("categories", BasicUtils.joinIntArray(categories));
        return gson.fromJson(
                httpUtils.sendPost("/report", params).get("data"),
                ReportData.class
        );
    }

    public ReportData reportIp(String ipAddress, int[] categories, String comment) {
        Map<String, String> params = new HashMap<>();
        params.put("ip", ipAddress);
        params.put("categories", BasicUtils.joinIntArray(categories));
        params.put("comment", comment);
        return gson.fromJson(
                httpUtils.sendPost("/report", params).get("data"),
                ReportData.class
        );
    }

    public CheckBlockData checkBlocked(String network) {
        Map<String, String> params = new HashMap<>();
        params.put("network", network);
        return gson.fromJson(
                httpUtils.sendGet("/check-block", params).get("data"),
                CheckBlockData.class
        );
    }

    public CheckBlockData checkBlocked(String network, int maxAgeInDays) {
        Map<String, String> params = new HashMap<>();
        params.put("network", network);
        params.put("maxAgeInDays", String.valueOf(maxAgeInDays));
        return gson.fromJson(
                httpUtils.sendGet("/check-block", params).get("data"),
                CheckBlockData.class
        );
    }

    public BulkReportData bulkReport(String pathToCsvFile) {
        Map<String, String> params = new HashMap<>();
        return gson.fromJson(
                httpUtils.sendPost("/bulk-report", params, "csv", new File(pathToCsvFile)),
                BulkReportData.class
        );
    }

    public ClearAddressData clearAddress(String ipAddress) {
        Map<String, String> params = new HashMap<>();
        params.put("ipAddress", ipAddress);
        return gson.fromJson(
                httpUtils.sendDelete("/clear-address", params).get("data"),
                ClearAddressData.class
        );
    }

}
