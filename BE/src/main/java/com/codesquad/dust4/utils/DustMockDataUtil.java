package com.codesquad.dust4.utils;

import com.codesquad.dust4.domain.DustForecast;
import com.codesquad.dust4.domain.DustStatusQuo;

public class DustMockDataUtil {

  public static DustStatusQuo CreateDumpDustStatusQuoData() {
    DustStatusQuo MockData = new DustStatusQuo();
    //MockData.setDataTime("2020-03-30 02:00");
    MockData.setPm10Grade("2");
    MockData.setPm10Value("40");
    MockData.setPm10Value("40");

    return MockData;
  }

  public static DustForecast createDumpDustForecastData() {
    DustForecast MockData = new DustForecast();
    MockData.setDataTime("2020-03-30 23시 발표");
    MockData.setInformData("2020-03-31");
    MockData.setInformOverall("○ [미세먼지] 전 권역이 '좋음'∼'보통'으로 예상됨. 다만/ 경기남부·충청권은 오전에 일시적으로 '나쁨' 수준일 것으로 예상됨.");
    MockData.setInformGrade("서울 : 보통/제주 : 좋음/전남 : 보통/전북 : 보통/광주 : 보통/경남 : 좋음/경북 : 좋음/울산 : 좋음/대구 : 좋음/부산 : 좋음/충남 : 보통/충북 : 보통/세종 : 보통/대전 : 보통/영동 : 좋음/영서 : 보통/경기남부 : 보통/경기북부 : 보통/인천 : 보통");
    MockData.setIMAGE_PM10_1("http://www.airkorea.or.kr/file/viewImage/?atch_id=138845");
    MockData.setIMAGE_PM10_2("http://www.airkorea.or.kr/file/viewImage/?atch_id=138846");
    MockData.setIMAGE_PM10_3("http://www.airkorea.or.kr/file/viewImage/?atch_id=138847");

    return MockData;
  }
}
