package com.kshitij.abuseipdbwrapper.models;

import com.kshitij.abuseipdbwrapper.utils.BasicUtils;
import java.text.ParseException;
import java.util.Date;

public class BlacklistObject {

    private String ipAddress;
    private int abuseConfidenceScore;
    private String countryCode;
    private Date lastReportedAt;

    public BlacklistObject(String ipAddress, int abuseConfidenceScore, String countryCode, String lastReportedAt) throws ParseException {
        this.ipAddress = ipAddress;
        this.abuseConfidenceScore = abuseConfidenceScore;
        this.countryCode = countryCode;
        this.lastReportedAt = BasicUtils.toDate(lastReportedAt);
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getAbuseConfidenceScore() {
        return abuseConfidenceScore;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public Date getLastReportDate() {
        return lastReportedAt;
    }

}
