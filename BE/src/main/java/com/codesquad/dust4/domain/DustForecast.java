package com.codesquad.dust4.domain;

public class DustForecast {

  private String dataTime; //통보시간
  private String informData; //예측통보시간
  private String informGrade; //지역별 등급
  private String informOverall; //예보 개황
  private String IMAGE_PM10_0600; //PM10 6시 이미지
  private String IMAGE_PM10_1200; //PM10 12시 이미지
  private String IMAGE_PM10_1800; //PM10 18시 이미지

  public DustForecast() {}

  public DustForecast(String dataTime, String informData, String informGrade,
      String informOverall, String IMAGE_PM10_0600, String IMAGE_PM10_1200,
      String IMAGE_PM10_1800) {
    this.dataTime = dataTime;
    this.informData = informData;
    this.informGrade = informGrade;
    this.informOverall = informOverall;
    this.IMAGE_PM10_0600 = IMAGE_PM10_0600;
    this.IMAGE_PM10_1200 = IMAGE_PM10_1200;
    this.IMAGE_PM10_1800 = IMAGE_PM10_1800;
  }

  public String getDataTime() {
    return dataTime;
  }

  public void setDataTime(String dataTime) {
    this.dataTime = dataTime;
  }

  public String getInformData() {
    return informData;
  }

  public void setInformData(String informData) {
    this.informData = informData;
  }

  public String getInformGrade() {
    return informGrade;
  }

  public void setInformGrade(String informGrade) {
    this.informGrade = informGrade;
  }

  public String getInformOverall() {
    return informOverall;
  }

  public void setInformOverall(String informOverall) {
    this.informOverall = informOverall;
  }

  public String getIMAGE_PM10_0600() {
    return IMAGE_PM10_0600;
  }

  public void setIMAGE_PM10_0600(String IMAGE_PM10_0600) {
    this.IMAGE_PM10_0600 = IMAGE_PM10_0600;
  }

  public String getIMAGE_PM10_1200() {
    return IMAGE_PM10_1200;
  }

  public void setIMAGE_PM10_1200(String IMAGE_PM10_1200) {
    this.IMAGE_PM10_1200 = IMAGE_PM10_1200;
  }

  public String getIMAGE_PM10_1800() {
    return IMAGE_PM10_1800;
  }

  public void setIMAGE_PM10_1800(String IMAGE_PM10_1800) {
    this.IMAGE_PM10_1800 = IMAGE_PM10_1800;
  }
}
