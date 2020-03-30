package com.codesquad.dust4.api;

import com.codesquad.dust4.domain.LocationOfStation;
import com.codesquad.dust4.dto.ApiResponse;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DustStationApiController {

  private static final Logger logger = LoggerFactory.getLogger(DustStationApiController.class);

  @GetMapping("/locations")
  public ResponseEntity<ApiResponse> enlistLocationStation() {

    logger.info("We embarked on enlisting location of stations.");

    ArrayList<LocationOfStation> MockList = new ArrayList<>();

    LocationOfStation MockData1 = new LocationOfStation("역삼동", "서울 강남구 역삼동 836-24", (float) 3.6);
    LocationOfStation MockData2 = new LocationOfStation("설성면", "경기 이천시 설평면 신필리산 88-5", (float) 2.4);

    MockList.add(MockData1);
    MockList.add(MockData2);

    ApiResponse returnMockResponse = new ApiResponse("200 OK", null);
    returnMockResponse.setContent(MockList);

    return new ResponseEntity<>(returnMockResponse, HttpStatus.OK);
  }

  @GetMapping("/location/@={latitude},{longitude}")
  public ResponseEntity<ApiResponse> getTheClosestStation(@PathVariable("latitude") String latitude, @PathVariable("longitude") String longitude) {
    logger.info("latitude: {}, longitude: {}", latitude, longitude);

    LocationOfStation MockData = new LocationOfStation("역삼동", "서울 강남구 역삼동 836-24", (float) 3.6);

    ApiResponse returnMockResponse = new ApiResponse("200 OK", null);
    returnMockResponse.setContent(MockData);

    return new ResponseEntity<>(returnMockResponse, HttpStatus.OK);
  }
}