package com.codesquad.dust4.api;

import com.codesquad.dust4.domain.DustStatusQuo;
import com.codesquad.dust4.dto.ApiResponse;
import com.codesquad.dust4.utils.DustMockDataUtil;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DustStatusQuoApiController {

  private static final Logger logger = LoggerFactory.getLogger(DustStatusQuoApiController.class);


  @GetMapping("/{location}/dust-status")
  public ResponseEntity<ApiResponse> StatusQuoOfLocation(@PathVariable("location") String location) {
    logger.info("location: {}", location);
    ArrayList<DustStatusQuo> MockList = new ArrayList<>();

    for (int i = 0; i < 24; i++) {
      DustStatusQuo dumpData = DustMockDataUtil.CreateDumpDustStatusQuoData();
      String dumpDataTime = "2020-03-30 " + i + ":00";
      dumpData.setDataTime(dumpDataTime);
      MockList.add(dumpData);
    }

    ApiResponse returnApiResponse = new ApiResponse("200 OK", null);
    returnApiResponse.setContent(MockList);
    return new ResponseEntity<>(returnApiResponse, HttpStatus.OK);
  }
}
