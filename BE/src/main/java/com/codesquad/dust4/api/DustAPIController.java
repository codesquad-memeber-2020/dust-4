package com.codesquad.dust4.api;

import com.codesquad.dust4.domain.DustForecast;
import com.codesquad.dust4.dto.DustInfoByStationDto;
import com.codesquad.dust4.dto.ForecastResponseDto;
import com.codesquad.dust4.service.DustStatusPublicApi;
import com.codesquad.dust4.utils.DustMockDataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DustAPIController {

  private static final Logger logger = LoggerFactory.getLogger(DustAPIController.class);

  @GetMapping("/dust-status")

  public ResponseEntity<DustInfoByStationDto> getDustInfo(@RequestParam("latitude") String latitude, @RequestParam("longitude") String longitude) throws IOException {
    logger.info("latitude: {}, longitude: {}", latitude, longitude);

//    todo
//    tm 좌표 변환

    String tmX = "35";
    String tmY = "140";

    DustInfoByStationDto dustInfoByStationDto = DustStatusPublicApi.dustStatus(tmX, tmY);

    return new ResponseEntity<>(dustInfoByStationDto, HttpStatus.OK);
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
