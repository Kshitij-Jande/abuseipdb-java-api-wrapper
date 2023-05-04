package com.kshitij.abuseipdbwrapper.models;

import java.util.Date;

public class ReportedAddressObject {

    private final String ipAddress;
    private final int numReports;
    private final Date mostRecentReport;
    private final int abuseConfidenceScore;
    private final String countryCode;

    public ReportedAddressObject(String ipAddress, int numReports, Date mostRecentReport,
                                 int abuseConfidenceScore, String countryCode) {
        this.ipAddress = ipAddress;
        this.numReports = numReports;
        this.mostRecentReport = mostRecentReport;
        this.abuseConfidenceScore = abuseConfidenceScore;
        this.countryCode = countryCode;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getNumberOfReports() {
        return numReports;
    }

    public Date getMostRecentReport() {
        return mostRecentReport;
    }

    public int getAbuseConfidenceScore() {
        return abuseConfidenceScore;
    }

    public String getCountryCode() {
        return countryCode;
    }

}