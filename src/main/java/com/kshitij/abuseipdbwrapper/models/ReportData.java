package com.kshitij.abuseipdbwrapper.models;

public class ReportData {

    private final String ipAddress;
    private final int abuseConfidenceScore;

    public ReportData(String ipAddress, int abuseConfidenceScore) {
        this.ipAddress = ipAddress;
        this.abuseConfidenceScore = abuseConfidenceScore;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getAbuseConfidenceScore() {
        return abuseConfidenceScore;
    }

}
