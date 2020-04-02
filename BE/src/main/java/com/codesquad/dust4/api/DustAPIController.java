package com.codesquad.dust4.api;

import com.codesquad.dust4.domain.DustForecast;
import com.codesquad.dust4.dto.DustInfoByStationDto;
import com.codesquad.dust4.dto.ForecastResponseDto;
import com.codesquad.dust4.dto.LocationReturnDto;
import com.codesquad.dust4.service.DustStatusPublicApi;
import com.codesquad.dust4.utils.DustMockDataUtil;
import com.codesquad.dust4.utils.LocationConverterUtil;
import java.util.concurrent.ExecutionException;
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

  public ResponseEntity<DustInfoByStationDto> getDustInfo(@RequestParam("latitude") String EPSGlatitude, @RequestParam("longitude") String EPSGlongitude)
      throws IOException, ExecutionException, InterruptedException {
    logger.info("longitude: {}, latitude: {}", EPSGlongitude, EPSGlatitude);

    LocationReturnDto locationReturnDto = LocationConverterUtil.locationConverter(EPSGlongitude, EPSGlatitude);

    String tmX = locationReturnDto.getLongitude();
    String tmY = locationReturnDto.getLatitude();

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
