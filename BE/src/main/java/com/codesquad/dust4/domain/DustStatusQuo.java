package com.codesquad.dust4.domain;

public class DustStatusQuo {

  private String dataTime; //측정일
  private LocationOfStation location; //측정소 정보
  private String pm10Value; //미세먼지(PM10) 농도
  private String pm10Grade; //미세먼지(PM10) 24시간 등급

  public DustStatusQuo() {}

  public DustStatusQuo(String dataTime, LocationOfStation location, String pm10Value,
      String pm10Value24, String pm10Grade, String pm10Grade1h) {
    this.dataTime = dataTime;
    this.location = location;
    this.pm10Value = pm10Value;
    this.pm10Grade = pm10Grade;
  }

  public String getDataTime() {
    return dataTime;
  }

  public void setDataTime(String dataTime) {
    this.dataTime = dataTime;
  }

  public LocationOfStation getLocation() {
    return location;
  }

  public void setLocation(LocationOfStation location) {
    this.location = location;
  }

  public String getPm10Value() {
    return pm10Value;
  }

  public void setPm10Value(String pm10Value) {
    this.pm10Value = pm10Value;
  }

  public String getPm10Grade() {
    return pm10Grade;
  }

  public void setPm10Grade(String pm10Grade) {
    this.pm10Grade = pm10Grade;
  }
}
