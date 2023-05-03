package com.kshitij.abuseipdbwrapper.models;

import java.util.Date;
import java.util.List;

public class CheckData {

    private final String ipAddress;
    private final boolean isPublic;
    private final int ipVersion;
    private final boolean isWhiteListed;
    private final int abuseConfidenceScore;
    private final String countryCode;
    private final String usageType;
    private final String isp;
    private final String domain;
    private final List<String> hostnames;
    private final boolean isTor;
    private final String countryName; // this is included with the "verbose" parameter
    private final int totalReports;
    private final int distinctUsers;
    private final Date lastReport;
    private final List<ReportObject> reports; // this is included with the "verbose" parameter

    public CheckData(String ipAddress, boolean isPublic, int ipVersion,
                     boolean isWhiteListed, int abuseConfidenceScore, String countryCode,
                     String countryName, String usageType, String isp,
                     String domain, List<String> hostnames, boolean isTor,
                     int totalReports, int distinctUsers, Date lastReport,
                     List<ReportObject> reports) {
        this.ipAddress = ipAddress;
        this.isPublic = isPublic;
        this.ipVersion = ipVersion;
        this.isWhiteListed = isWhiteListed;
        this.abuseConfidenceScore = abuseConfidenceScore;
        this.countryCode = countryCode;
        this.usageType = usageType;
        this.isp = isp;
        this.domain = domain;
        this.hostnames = hostnames;
        this.isTor = isTor;
        this.countryName = countryName;
        this.totalReports = totalReports;
        this.distinctUsers = distinctUsers;
        this.lastReport = lastReport;
        this.reports = reports;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public int getIpVersion() {
        return ipVersion;
    }

    public boolean isWhiteListed() {
        return isWhiteListed;
    }

    public int getAbuseConfidenceScore() {
        return abuseConfidenceScore;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getUsageType() {
        return usageType;
    }

    public String getIsp() {
        return isp;
    }

    public String getDomain() {
        return domain;
    }

    public List<String> getHostnames() {
        return hostnames;
    }

    public boolean isTor() {
        return isTor;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getTotalReports() {
        return totalReports;
    }

    public int getDistinctUsers() {
        return distinctUsers;
    }

    public Date getLastReport() {
        return lastReport;
    }

    public List<ReportObject> getReports() {
        return reports;
    }

}
