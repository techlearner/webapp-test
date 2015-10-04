package com.m2p.web.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpMethod;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by sriramk on 29-07-2015.
 */
public class JsonRequestUtility {
    private final String boundary;
    private static final String LINE_FEED = "\r\n";
    private HttpURLConnection httpConn;
    private String charset;
    private OutputStream outputStream;
    private PrintWriter writer;


    public JsonRequestUtility(String requestURL, String charset, HttpMethod method)
            throws IOException {
        this.charset = charset;

        // creates a unique boundary based on time stamp
        boundary = "===" + System.currentTimeMillis() + "===";

        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setRequestMethod(method.toString());
        httpConn.setConnectTimeout(60000);
        if (method.equals(HttpMethod.POST)) {
            httpConn.setDoOutput(true);    // indicates POST method
            httpConn.setRequestProperty("Content-Type",
                    "application/json");

            httpConn.setDoInput(true);

            httpConn.setRequestProperty("User-Agent", "CodeJava Agent");

        }
    }

    public void addRequestBody(Map<String, Object> jsonMap) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        writer.write(mapper.writeValueAsString(jsonMap));
        writer.flush();
    }

    public void addHeader(String key, String value) throws IOException {
        httpConn.setRequestProperty(key, value);
        if (httpConn.getRequestMethod().equals("POST")) {
            outputStream = httpConn.getOutputStream();
            writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                    true);
        }
    }

    public List<String> finish() throws IOException {
        List<String> response = new ArrayList<String>();

        // checks server's status code first
        int status = httpConn.getResponseCode();
        if (status == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpConn.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }
            reader.close();
            httpConn.disconnect();
        } else {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    httpConn.getErrorStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                response.add(line);
            }
            reader.close();
            httpConn.disconnect();
        }

        return response;
    }

    public YappayResponse getYappayResponse(List<String> finish) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(finish.get(0), YappayResponse.class);
    }
}
