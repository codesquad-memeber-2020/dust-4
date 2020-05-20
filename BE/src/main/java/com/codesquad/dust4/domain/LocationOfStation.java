package com.codesquad.dust4.domain;

public class LocationOfStation {

  private String stationName; //측정소 명
  private String addr; //측정소 주소
  private float tm; //요청한 TM좌표와 측정소간의 거리(km 단위)

  public LocationOfStation() {}

  public LocationOfStation(String stationName, String addr, float tm) {
    this.stationName = stationName;
    this.addr = addr;
    this.tm = tm;
  }

  public String getStationName() {
    return stationName;
  }

  public void setStationName(String stationName) {
    this.stationName = stationName;
  }

  public String getAddr() {
    return addr;
  }

  public void setAddr(String addr) {
    this.addr = addr;
  }

  public float getTm() {
    return tm;
  }

  public void setTm(float tm) {
    this.tm = tm;
  }

  @Override
  public String toString() {
    return "LocationOfStation{" +
        "stationName='" + stationName + '\'' +
        ", addr='" + addr + '\'' +
        ", tm=" + tm +
        '}';
  }
}
