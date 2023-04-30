package com.kshitij.abuseipdbwrapper.utils;

import com.kshitij.abuseipdbwrapper.exceptions.ApiException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;

public class HttpUtils {

    private final String URL = "https://api.abuseipdb.com/api/v2";
    private final String apiKey;
    private final HttpClient client;

    public HttpUtils(String apiKey) {
        this.apiKey = apiKey;
        this.client = HttpClient.newHttpClient();
    }

    public HttpResponse<String> sendGet(String endpoint, Map<String, String> params) throws ApiException {
        String urlToSend = this.formUrlToSend(endpoint, params);
        try {
            HttpRequest request = buildRequest().GET().uri(new URI(urlToSend)).build();
            return this.dispatchRequest(request);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    public HttpResponse<String> sendPost(String endpoint, Map<String, String> params) throws ApiException {
        String urlToSend = this.formUrlToSend(endpoint, params);
        try {
            HttpRequest request = buildRequest().POST(HttpRequest.BodyPublishers.noBody()).uri(new URI(urlToSend)).build();
            return this.dispatchRequest(request);
        } catch (IOException | InterruptedException | URISyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String formUrlToSend(String endpoint, Map<String, String> params) {
        return this.URL + endpoint + "?" + this.formQueryParameters(params);
    }

    private String formQueryParameters(Map<String, String> params) {
        return params.entrySet().stream().map(p -> encodeString(p.getKey()) + "=" + encodeString(p.getValue())).collect(Collectors.joining("&"));
    }

    private String encodeString(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    private HttpRequest.Builder buildRequest() {
        return HttpRequest.newBuilder().setHeader("User-Agent", "AbuseIPDBAPIWrapper").setHeader("Key", this.apiKey).timeout(Duration.ofSeconds(30));
    }

    private HttpResponse<String> dispatchRequest(HttpRequest request) throws ApiException, IOException, InterruptedException {
        HttpResponse<String> httpResponse = this.client.send(request, HttpResponse.BodyHandlers.ofString());

        if (httpResponse.statusCode() < 200 || httpResponse.statusCode() > 399) {
            throw new ApiException(httpResponse.statusCode(), httpResponse.body());
        }
        return httpResponse;
    }

}
