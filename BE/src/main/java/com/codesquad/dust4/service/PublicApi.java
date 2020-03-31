package com.codesquad.dust4.service;

import jdk.nashorn.internal.parser.JSONParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PublicApi {

    public static String getStation(String tmX, String tmY) throws IOException {
        String stationJson = getStationResponse(tmX, tmY);
        return stationJson;
    }

    private static String getStationResponse(String tmX, String tmY) throws IOException {
        String serviceKey = "GHE6PYOY0ZzdJ8j%2BISHC3VOKNQI1DG60chkCAE19uUD5s1XCnXOseXvJfBJkFZlI1zpn2EGLDnkYy%2FQrgLpd6A%3D%3D";

        String urlBuilder = "http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList" + "?ServiceKey=" + serviceKey +
                "&tmX=" + URLEncoder.encode(tmX, "UTF-8") +
                "&tmY=" + URLEncoder.encode(tmY, "UTF-8") +
                "&ver=" + URLEncoder.encode("1.0", "UTF-8") +
                "&_returnType=" + URLEncoder.encode("json", "UTF-8");

        URL url = new URL(urlBuilder);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        return sb.toString();
    }


}
