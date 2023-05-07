package com.kshitij.abuseipdbwrapper.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.kshitij.abuseipdbwrapper.exceptions.AbuseIPDBApiException;
import org.apache.commons.codec.Charsets;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
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

    public JsonObject sendGet(String endpoint, Map<String, String> params) throws AbuseIPDBApiException {
        String urlToSend = formUrlToSend(endpoint, params);
        try {
            HttpGet request = new HttpGet(urlToSend);
            setNecessaryHeaders(request);
            return getJsonFromHttpEntity(dispatchRequest(request));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JsonObject sendPost(String endpoint, Map<String, String> params) throws AbuseIPDBApiException {
        String urlToSend = formUrlToSend(endpoint, params);
        try {
            HttpPost request = new HttpPost(urlToSend);
            setNecessaryHeaders(request);
            HttpEntity entity = dispatchRequest(request);
            return getJsonFromHttpEntity(entity);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JsonObject sendPost(String endpoint, Map<String, String> params, String key, File file) throws AbuseIPDBApiException {
        String urlToSend = this.formUrlToSend(endpoint, params);
        try {
            HttpPost request = new HttpPost(urlToSend);
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addPart(key, new FileBody(file, ContentType.DEFAULT_BINARY));
            request.setEntity(builder.build());
            setNecessaryHeaders(request);
            return getJsonFromHttpEntity(dispatchRequest(request));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public JsonObject sendDelete(String endpoint, Map<String, String> params) throws AbuseIPDBApiException {
        String urlToSend = formUrlToSend(endpoint, params);
        try {
            HttpDelete request = new HttpDelete(urlToSend);
            setNecessaryHeaders(request);
            return getJsonFromHttpEntity(dispatchRequest(request));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String formUrlToSend(String endpoint, Map<String, String> params) {
        return URL + endpoint + "?" + formQueryParameters(params);
    }

    private String formQueryParameters(Map<String, String> params) {
        return params.entrySet().stream().map((p) -> {
            try {
                return encodeString(p.getKey()) + "=" + encodeString(p.getValue());
            } catch (NullPointerException e) {
                return encodeString(p.getKey());
            }
        }).collect(Collectors.joining("&"));
    }

    private String encodeString(String s) {
        return URLEncoder.encode(s, StandardCharsets.UTF_8);
    }

    private void setNecessaryHeaders(HttpUriRequest request) {
        request.setHeader("User-Agent", "AbuseIPDBAPIWrapper");
        request.setHeader("Key", apiKey);
        request.setHeader("Accept", "application/json");
    }

    private HttpEntity dispatchRequest(HttpUriRequest request) throws AbuseIPDBApiException, IOException {
        CloseableHttpResponse client = HttpClients.createDefault().execute(request);
        int statusCode = client.getStatusLine().getStatusCode();
        if (statusCode < 200 || statusCode > 399) {
            throw new AbuseIPDBApiException(statusCode, getStringFromEntity(client.getEntity()));
        }
        return client.getEntity();
    }

    private JsonObject getJsonFromHttpEntity(HttpEntity entity) throws IOException {
        return new Gson().fromJson(
                getStringFromEntity(entity),
                JsonObject.class
        );
    }

    private String getStringFromEntity(HttpEntity entity) throws IOException {
        Header encodingHeader = entity.getContentEncoding();
        Charset encodingCharset = encodingHeader == null
                ? StandardCharsets.UTF_8
                : Charsets.toCharset(encodingHeader.getValue());
        return EntityUtils.toString(entity, encodingCharset);
    }

}
