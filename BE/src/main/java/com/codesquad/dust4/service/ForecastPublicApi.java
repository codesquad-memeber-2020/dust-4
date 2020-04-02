package com.codesquad.dust4.service;

import com.codesquad.dust4.domain.DustForecast;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ForecastPublicApi {

    public static DustForecast forecast() throws URISyntaxException, JsonProcessingException {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        URI requestURL = new URI(PublicApi.API_URL + "ArpltnInforInqireSvc/getMinuDustFrcstDspth?searchDate=" + date + "&ServiceKey=" + PublicApi.API_KEY + "&InformCode=PM10&_returnType=json");

        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject(requestURL, String.class);
        ObjectMapper mapper = new ObjectMapper();

        JsonNode forecastObject;
        forecastObject = mapper.readTree(response).get("list").get(0);

        String dataTime = forecastObject.get("dataTime").asText();
        String informData = forecastObject.get("informData").asText();
        String informGrade = forecastObject.get("informGrade").asText();
        String informOverall = forecastObject.get("informOverall").asText();

        List<String> images = new ArrayList<>();
        int imageCount = 3;
        for (int i = 0; i < imageCount; i++) {
            images.add(forecastObject.get("imageUrl" + (i + 1)).asText());
        }

        return new DustForecast(dataTime, informData, informGrade, informOverall, images);
    }
}
