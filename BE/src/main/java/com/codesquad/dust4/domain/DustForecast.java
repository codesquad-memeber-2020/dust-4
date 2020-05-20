package com.codesquad.dust4.domain;

import java.util.List;

public class DustForecast {

  private String dataTime; //통보시간
  private String informData; //예측통보시간
  private String informGrade; //지역별 등급
  private String informOverall; //예보 개황
  private List<String> images;

  public DustForecast() {}

  public DustForecast(String dataTime, String informData, String informGrade,
      String informOverall, List<String> images) {
    this.dataTime = dataTime;
    this.informData = informData;
    this.informGrade = informGrade;
    this.informOverall = informOverall;
    this.images = images;
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

  public List<String> getImages() {
    return images;
  }

  public void setImages(List<String> images) {
    this.images = images;
  }

  @Override
  public String toString() {
    return "DustForecast{" +
            "dataTime='" + dataTime + '\'' +
            ", informData='" + informData + '\'' +
            ", informGrade='" + informGrade + '\'' +
            ", informOverall='" + informOverall + '\'' +
            ", images=" + images +
            '}';
  }
}
