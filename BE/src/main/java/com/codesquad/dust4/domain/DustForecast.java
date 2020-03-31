package com.codesquad.dust4.domain;

public class DustForecast {

  private String dataTime; //통보시간
  private String informData; //예측통보시간
  private String informGrade; //지역별 등급
  private String informOverall; //예보 개황
  private String IMAGE_PM10_1; //PM10 현재 요청 시간에서 가장 가까운 시간대의 이미지
  private String IMAGE_PM10_2; //PM10 현재 요청 시간에서 두 번째로 가까운 시간대의 이미지
  private String IMAGE_PM10_3; //PM10 현재 요청 시간에서 세 번째로 가까운 시간대의 이미지

  public DustForecast() {}

  public DustForecast(String dataTime, String informData, String informGrade,
      String informOverall, String IMAGE_PM10_1, String IMAGE_PM10_2, String IMAGE_PM10_3) {
    this.dataTime = dataTime;
    this.informData = informData;
    this.informGrade = informGrade;
    this.informOverall = informOverall;
    this.IMAGE_PM10_1 = IMAGE_PM10_1;
    this.IMAGE_PM10_2 = IMAGE_PM10_2;
    this.IMAGE_PM10_3 = IMAGE_PM10_3;
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

  public String getIMAGE_PM10_1() {
    return IMAGE_PM10_1;
  }

  public void setIMAGE_PM10_1(String IMAGE_PM10_1) {
    this.IMAGE_PM10_1 = IMAGE_PM10_1;
  }

  public String getIMAGE_PM10_2() {
    return IMAGE_PM10_2;
  }

  public void setIMAGE_PM10_2(String IMAGE_PM10_2) {
    this.IMAGE_PM10_2 = IMAGE_PM10_2;
  }

  public String getIMAGE_PM10_3() {
    return IMAGE_PM10_3;
  }

  public void setIMAGE_PM10_3(String IMAGE_PM10_3) {
    this.IMAGE_PM10_3 = IMAGE_PM10_3;
  }
}
