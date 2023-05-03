package com.kshitij.abuseipdbwrapper.models;

import java.util.List;

public class ReportsData {

    private final int total;
    private final int page;
    private final int count;
    private final int perPage;
    private final int lastPage;
    private final String nextPageUrl;
    private final String previousPageUrl;
    private final List<ReportObject> results;

    public ReportsData(int total, int page, int count, int perPage, int lastPage, String nextPageUrl, String previousPageUrl, List<ReportObject> results) {
        this.total = total;
        this.page = page;
        this.count = count;
        this.perPage = perPage;
        this.lastPage = lastPage;
        this.nextPageUrl = nextPageUrl;
        this.previousPageUrl = previousPageUrl;
        this.results = results;
    }

    public int getTotalReports() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getCount() {
        return count;
    }

    public int getPerPage() {
        return perPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    private String getNextPageUrl() {
        return nextPageUrl;
    }

    private String getPreviousPageUrl() {
        return previousPageUrl;
    }

    public List<ReportObject> getReports() {
        return results;
    }

}
