package com.codesquad.dust4.utils;

import com.codesquad.dust4.dto.LocationReturnDto;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class LocationConverterUtil {

  private static final Logger logger = LoggerFactory.getLogger(LocationConverterUtil.class);

  public static LocationReturnDto locationConverter(String EPSGlongitude, String EPSGlatitude)
      throws ExecutionException, InterruptedException {
    String URL = "https://sgisapi.kostat.go.kr/OpenAPI3/transformation/transcoord.json";
    String requestURL = URL + "?accessToken=" + AccessTokenUtil.getLocationConversionAPIToken()
        + "&src=" + "4326" + "&dst=" + "5181" + "&posX=" + EPSGlongitude
        + "&posY=" + EPSGlatitude;

    RestTemplate restTemplate = new RestTemplate();

    String receivedTmLocation = restTemplate.getForObject(requestURL, String.class);
    logger.info("received tm location: {} ", receivedTmLocation);
    ArrayList<String> parsedLocation = ParseJSONDataUtil.parseLocationJSON(receivedTmLocation);

    LocationReturnDto locationReturnDto = new LocationReturnDto();
    locationReturnDto.setLongitude(parsedLocation.get(0));
    locationReturnDto.setLatitude(parsedLocation.get(1));

    return locationReturnDto;
  }
}
