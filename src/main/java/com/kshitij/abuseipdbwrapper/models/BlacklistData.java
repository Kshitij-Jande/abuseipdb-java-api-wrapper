package com.kshitij.abuseipdbwrapper.models;

import com.kshitij.abuseipdbwrapper.utils.BasicUtils;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class BlacklistData {

    private final Date generatedAt;
    private final List<BlacklistObject> blacklists;

    public BlacklistData(String generatedAt, List<BlacklistObject> blacklists) throws ParseException {
        this.generatedAt = BasicUtils.toDate(generatedAt);
        this.blacklists = blacklists;
    }

    public Date getGeneratedDate() {
        return generatedAt;
    }

    public List<BlacklistObject> getBlacklists() {
        return blacklists;
    }

}