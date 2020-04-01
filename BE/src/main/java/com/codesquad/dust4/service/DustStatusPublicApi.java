package com.codesquad.dust4.service;

import com.codesquad.dust4.domain.DustStatusQuo;
import com.codesquad.dust4.domain.LocationOfStation;
import com.codesquad.dust4.dto.DustInfoByStationDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DustStatusPublicApi {
    private static final String SERVICE_KEY = "GHE6PYOY0ZzdJ8j%2BISHC3VOKNQI1DG60chkCAE19uUD5s1XCnXOseXvJfBJkFZlI1zpn2EGLDnkYy%2FQrgLpd6A%3D%3D";
    private static final String PUBLIC_API_REQUEST_URL = "http://openapi.airkorea.or.kr/openapi/services/rest/";

    public static DustInfoByStationDto dustStatus(String tmX, String tmY) throws IOException {
        String response = stationFromPublicApi(tmX, tmY);
        LocationOfStation closestStation = closestStation(response);
        String dustStatusResponse = dustStatusFromPublicApi(closestStation);
        List<DustStatusQuo> status = statusQuos(dustStatusResponse);

        return new DustInfoByStationDto(status, closestStation);
    }

    private static LocationOfStation closestStation(String stationJson) {
        JSONParser parser = new JSONParser();
        JSONObject obj;
        JSONArray array;
        List<LocationOfStation> stations = new ArrayList<>();

        try {
            obj = (JSONObject) parser.parse(stationJson);
            array = (JSONArray) obj.get("list");
            for (Object o : array) {
                LocationOfStation station = new LocationOfStation();
                JSONObject o1 = (JSONObject) o;
                String addr = String.valueOf(o1.get("addr"));
                String stationName = String.valueOf(o1.get("stationName"));
                String tm = String.valueOf(o1.get("tm"));
                station.setAddr(addr);
                station.setStationName(stationName);
                station.setTm(Float.parseFloat(tm));
                stations.add(station);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return  stations.stream().reduce(stations.get(0), (closest, station) -> closest.getTm() >= station.getTm() ? closest : station);
    }

    private static String stationFromPublicApi(String tmX, String tmY) throws IOException {
        URL url = new URL(PUBLIC_API_REQUEST_URL + "MsrstnInfoInqireSvc/getNearbyMsrstnList?ServiceKey=" + SERVICE_KEY +
                "&tmX=" + tmX +
                "&tmY=" + tmY +
                "&ver=1.0" +
                "&_returnType=json");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");

        BufferedReader bufferedReader;
        if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        bufferedReader.close();
        connection.disconnect();
        return stringBuilder.toString();
    }

    private static  List<DustStatusQuo> statusQuos(String statusJson) {
        JSONParser parser = new JSONParser();
        JSONObject obj;
        JSONArray array;
        List<DustStatusQuo> statusList = new ArrayList<>();

        try {
            obj = (JSONObject) parser.parse(statusJson);
            array = (JSONArray) obj.get("list");
            for (Object object : array) {
                DustStatusQuo status = new DustStatusQuo();
                JSONObject stationObject = (JSONObject) object;
                String dateTime = String.valueOf(stationObject.get("dataTime"));
                String grade = String.valueOf(stationObject.get("pm10Grade"));
                String value = String.valueOf(stationObject.get("pm10Value"));
                String grade1h = String.valueOf(stationObject.get("pm10Grade10"));
                String value24 = String .valueOf(stationObject.get("pm10Value24"));

                status.setDataTime(dateTime);
                status.setPm10Grade(grade);
                status.setPm10Value(value);
                status.setPm10Grade1h(grade1h);
                status.setPm10Value24(value24);

                statusList.add(status);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(statusList);
        return statusList;
    }

    private static String dustStatusFromPublicApi(LocationOfStation station) throws IOException {
        URL url = new URL(PUBLIC_API_REQUEST_URL +
                "ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName=" +
                station.getStationName() + "&dataTerm=DAILY&pageNo=1&numOfRows=25&ServiceKey=" +
                SERVICE_KEY +
                "&ver=1.3&_returnType=json");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Cotent-type", "application/json");

        BufferedReader bufferedReader;
        if (connection.getResponseCode() >= 200 && connection.getResponseCode() <= 300) {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        bufferedReader.close();
        connection.disconnect();
        return stringBuilder.toString();
    }
}
