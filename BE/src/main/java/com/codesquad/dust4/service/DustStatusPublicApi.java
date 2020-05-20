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
                JSONObject o1 = (JSONObject) o;
                String addr = String.valueOf(o1.get("addr"));
                String stationName = String.valueOf(o1.get("stationName"));
                String tm = String.valueOf(o1.get("tm"));

                LocationOfStation station = new LocationOfStation(stationName, addr, Float.parseFloat(tm));
                stations.add(station);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  stations.stream().reduce(stations.get(0), (closest, station) -> closest.getTm() <= station.getTm() ? closest : station);
    }

    private static String stationFromPublicApi(String tmX, String tmY) throws IOException {
        URL url = new URL(PublicApi.API_URL + "MsrstnInfoInqireSvc/getNearbyMsrstnList?ServiceKey=" + PublicApi.API_KEY +
                "&tmX=" + tmX +
                "&tmY=" + tmY +
                "&_returnType=json");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");

        return readString(connection);
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
                JSONObject stationObject = (JSONObject) object;
                String dateTime = String.valueOf(stationObject.get("dataTime"));
                String grade = String.valueOf(stationObject.get("pm10Grade"));
                if (grade.equals("-")) {
                    grade = "0";
                }
                String value = String.valueOf(stationObject.get("pm10Value"));
                if (value.equals("-")) {
                    value = "0";
                }

                DustStatusQuo status = new DustStatusQuo(dateTime, value, grade);
                statusList.add(status);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return statusList;
    }

    private static String readString(HttpURLConnection connection) throws IOException {
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


    private static String dustStatusFromPublicApi(LocationOfStation station) throws IOException {
        URL url = new URL(PublicApi.API_URL +
                "ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName=" +
                station.getStationName() + "&dataTerm=DAILY&pageNo=1&numOfRows=25&ServiceKey=" +
                PublicApi.API_KEY +
                "&ver=1.3&_returnType=json");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-type", "application/json");

        return readString(connection);
    }
}
