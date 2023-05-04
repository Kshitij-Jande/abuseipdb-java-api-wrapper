package com.kshitij.abuseipdbwrapper.models;

import java.util.List;

public class CheckBlockData {

    private final String networkAddress;
    private final String netmask;
    private final String minAddress;
    private final String maxAddress;
    private final int numPossibleHosts;
    private final String addressSpaceDesc;
    private final List<ReportedAddressObject> reportedAddress;

    public CheckBlockData(String networkAddress, String netmask, String minAddress,
                          String maxAddress, int numPossibleHosts, String addressSpaceDesc,
                          List<ReportedAddressObject> reportedAddress) {
        this.networkAddress = networkAddress;
        this.netmask = netmask;
        this.minAddress = minAddress;
        this.maxAddress = maxAddress;
        this.numPossibleHosts = numPossibleHosts;
        this.addressSpaceDesc = addressSpaceDesc;
        this.reportedAddress = reportedAddress;
    }

    public String getNetworkAddress() {
        return networkAddress;
    }

    public String getNetworkMask() {
        return netmask;
    }

    public String getMinAddress() {
        return minAddress;
    }

    public String getMaxAddress() {
        return maxAddress;
    }

    public int getNumberOfPossibleHosts() {
        return numPossibleHosts;
    }

    public String getAddressSpaceDescription() {
        return addressSpaceDesc;
    }

    public List<ReportedAddressObject> getReportedAddresses() {
        return reportedAddress;
    }

}