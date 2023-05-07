package com.kshitij.abuseipdbwrapper.exceptions;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AbuseIPDBApiException extends RuntimeException {

    private final int statusCode;
    private final JsonObject body;
    private final List<String> errorDetails = new ArrayList<>();

    public AbuseIPDBApiException(int statusCode, String body) {
        super("Error from AbuseIPDB's API." + JsonParser.parseString(body).getAsJsonObject().get("errors").getAsJsonArray()
                .asList().stream().map(o -> o.getAsJsonObject().get("detail").getAsString())
                .collect(Collectors.joining("\n", " ", "")));
        this.statusCode = statusCode;
        JsonElement jsonElement = JsonParser.parseString(body);
        this.body = jsonElement.getAsJsonObject();
        JsonArray jsonArray = this.body.get("errors").getAsJsonArray();
        jsonArray.forEach((e) -> this.errorDetails.add(e.getAsJsonObject().get("detail").getAsString()));
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public List<String> getErrorDetails() {
        return this.errorDetails;
    }

    public JsonObject getFullResponseBody() {
        return this.body;
    }

}
