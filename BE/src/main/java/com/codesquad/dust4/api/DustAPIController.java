package com.codesquad.dust4.api;

import com.codesquad.dust4.domain.DustForecast;
import com.codesquad.dust4.domain.DustStatusQuo;
import com.codesquad.dust4.domain.LocationOfStation;
import com.codesquad.dust4.dto.DustInfoByStationDto;
import com.codesquad.dust4.dto.ForecastResponseDto;
import com.codesquad.dust4.utils.DustMockDataUtil;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DustAPIController {

  private static final Logger logger = LoggerFactory.getLogger(DustAPIController.class);

  @GetMapping("/dust-status")
  public ResponseEntity<DustInfoByStationDto> getDustInfo(@RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude) {

    logger.info("latitude: {}, longitude: {}", latitude, longitude);

    ArrayList<DustStatusQuo> MockList = new ArrayList<>();
    LocationOfStation MockStation = new LocationOfStation("역삼동", "서울 강남구 역삼동 836-24", (float) 3.6);

    for (int i = 0; i < 24; i++) {
      DustStatusQuo dumpData = DustMockDataUtil.CreateDumpDustStatusQuoData();
      String dumpDataTime = "2020-03-30 " + i + ":00";
      dumpData.setDataTime(dumpDataTime);
      MockList.add(dumpData);
    }

    DustInfoByStationDto returnApiResponse = new DustInfoByStationDto();
    returnApiResponse.setLocation(MockStation);
    returnApiResponse.setContent(MockList);
    return new ResponseEntity<>(returnApiResponse, HttpStatus.OK);
  }

  @GetMapping("/forecast")
  public ResponseEntity<ForecastResponseDto> enlistDustForecast() {

    logger.info("dust forecast");

    DustForecast dumpData = DustMockDataUtil.createDumpDustForecastData();
    ForecastResponseDto responseDto = new ForecastResponseDto();
    responseDto.setContent(dumpData);
    return new ResponseEntity<>(responseDto, HttpStatus.OK);
  }
}