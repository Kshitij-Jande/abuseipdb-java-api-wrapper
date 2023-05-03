package com.kshitij.abuseipdbwrapper.models;

import com.kshitij.abuseipdbwrapper.utils.BasicUtils;

import java.text.ParseException;
import java.util.Date;

public class ReportObject {

    private final Date reportedAt;
    private final String comment;
    private final int[] categories;
    private final int reporterId;
    private final String reporterCountryCode;
    private final String reporterCountryName;

    public ReportObject(String reportedAt, String comment, int[] categories, int reporterId, String reporterCountryCode, String reporterCountryName) throws ParseException {
        this.reportedAt = BasicUtils.toDate(reportedAt);
        this.comment = comment;
        this.categories = categories;
        this.reporterId = reporterId;
        this.reporterCountryCode = reporterCountryCode;
        this.reporterCountryName = reporterCountryName;
    }

    public Date getReportedAt() {
        return reportedAt;
    }

    public String getComment() {
        return comment;
    }

    public int[] getCategories() {
        return categories;
    }

    public int getReporterId() {
        return reporterId;
    }

    public String getReporterCountryCode() {
        return reporterCountryCode;
    }

    public String getReporterCountryName() {
        return reporterCountryName;
    }

}
