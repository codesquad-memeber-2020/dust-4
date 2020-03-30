package com.codesquad.dust4.api;

import com.codesquad.dust4.domain.DustForecast;
import com.codesquad.dust4.utils.DustMockDataUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DustForecastApiController {

  private static final Logger logger = LoggerFactory.getLogger(DustForecastApiController.class);

  @GetMapping("/forecast")
  public ResponseEntity<DustForecast> enlistDustForecast() {

    logger.info("dust forecast");

    DustForecast dumpData = DustMockDataUtil.createDumpDustForecastData();
    return new ResponseEntity<>(dumpData, HttpStatus.OK);
  }
}
