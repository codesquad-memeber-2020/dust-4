package com.codesquad.dust4.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseJSONDataUtil {

  private static final Logger logger = LoggerFactory.getLogger(ParseJSONDataUtil.class);

  public static ArrayList<String> parseLocationJSON(String rawJSONData) {
    ArrayList<String> returnValue = new ArrayList<>();
    JsonParser parser = new JsonParser();

    JsonObject jsonObject = (JsonObject) parser.parse(rawJSONData);
    JsonObject resultObject = (JsonObject) jsonObject.get("result");
    String posX = resultObject.get("posX").getAsString();
    String posY = resultObject.get("posY").getAsString();

    returnValue.add(posX);
    returnValue.add(posY);

    return returnValue;
  }

  public static String parseAccessTokenJson(String rawJSONData) {
    JsonParser parser = new JsonParser();
    JsonObject jsonObject = (JsonObject) parser.parse(rawJSONData);
    JsonObject resultObject = (JsonObject) jsonObject.get("result");
    String accessTokenObject = resultObject.get("accessToken").getAsString();

    logger.info("accessToken: {}", accessTokenObject);

    return accessTokenObject;
  }
}
