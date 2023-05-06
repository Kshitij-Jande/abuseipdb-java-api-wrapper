package com.kshitij.abuseipdbwrapper.models;

public class ClearAddressData {

    private final int numReportsDeleted;

    public ClearAddressData(int numReportsDeleted) {
        this.numReportsDeleted = numReportsDeleted;
    }

    public int getNumberOfDeletedReports() {
        return numReportsDeleted;
    }

}
