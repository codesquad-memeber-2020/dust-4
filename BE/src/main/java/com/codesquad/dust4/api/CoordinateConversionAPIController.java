package com.codesquad.dust4.api;

import com.codesquad.dust4.dto.LocationReturnDto;
import com.codesquad.dust4.utils.AccessTokenUtil;
import com.codesquad.dust4.utils.ParseJSONDataUtil;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CoordinateConversionAPIController {

  private static final Logger logger = LoggerFactory.getLogger(CoordinateConversionAPIController.class);

  @GetMapping("/location")
  public ResponseEntity<LocationReturnDto> convertLocation(
      @RequestParam("EPSGLatitude") String EPSGLatitude,
      @RequestParam("EPSGLongitude") String EPSGLongitude)
      throws ExecutionException, InterruptedException {

    String URL = "https://sgisapi.kostat.go.kr/OpenAPI3/transformation/transcoord.json";
    String requestURL = URL + "?accessToken=" + AccessTokenUtil.getLocationConversionAPIToken()
        + "&src=" + "4326" + "&dst=" + "5181" + "&posX=" + EPSGLatitude
        + "&posY=" + EPSGLongitude;

    ExecutorService executorService = Executors.newCachedThreadPool();
    RestTemplate restTemplate = new RestTemplate();

    String receivedTmLocation = restTemplate.getForObject(requestURL, String.class);
    logger.info("received tm location: {} ", receivedTmLocation);
    ArrayList<String> parsedLocation = ParseJSONDataUtil.parseLocationJSON(receivedTmLocation);

    LocationReturnDto locationReturnDto = new LocationReturnDto();
    locationReturnDto.setLatitude(parsedLocation.get(0));
    locationReturnDto.setLongitude(parsedLocation.get(1));

    return new ResponseEntity<>(locationReturnDto, HttpStatus.OK);
  }
}
