package com.codesquad.dust4.utils;

import com.codesquad.dust4.api.DustAPIController;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class AccessTokenUtil {

  public static final String CONSUMER_KEY_LOCATION = "6e8fc57906324e20805a";
  public static final String CONSUMER_SECRET_LOCATION = "aaa5becbdfcd4b5bb349";
  private static final Logger logger = LoggerFactory.getLogger(DustAPIController.class);

  public static String getLocationConversionAPIToken()
      throws ExecutionException, InterruptedException {
    String URL = "https://sgisapi.kostat.go.kr/OpenAPI3/auth/authentication.json";
    String accessTokenURL =
        URL + "?consumer_key=" + AccessTokenUtil.CONSUMER_KEY_LOCATION + "&consumer_secret="
            + AccessTokenUtil.CONSUMER_SECRET_LOCATION;

    RestTemplate restTemplate = new RestTemplate();

    String receivedToken = restTemplate.getForObject(accessTokenURL, String.class);
    String accessToken = ParseJSONDataUtil.parseAccessTokenJson(receivedToken);
    logger.info("accessToken: {} ", accessToken);

    return accessToken;
  }
}
