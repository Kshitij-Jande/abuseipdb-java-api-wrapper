package com.kshitij.abuseipdbwrapper.exceptions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class ApiException extends Exception {

    public final int statusCode;
    public final JsonObject body;
    public final List<String> errorDetails = new ArrayList<>();

    public ApiException(int statusCode, String body) {
        super("Got an error from AbuseIPDB's API.");
        this.statusCode = statusCode;
        JsonElement jsonElement = JsonParser.parseString(body);
        this.body = jsonElement.getAsJsonObject();
        JsonArray jsonArray = this.body.get("errors").getAsJsonArray();
        jsonArray.forEach((e) -> this.errorDetails.add(e.getAsJsonObject().get("detail").getAsString()));
    }

}
