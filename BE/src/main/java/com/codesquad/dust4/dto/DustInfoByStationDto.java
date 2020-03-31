package com.codesquad.dust4.dto;

import com.codesquad.dust4.domain.DustStatusQuo;
import com.codesquad.dust4.domain.LocationOfStation;
import java.util.List;

public class DustInfoByStationDto {

  private List<DustStatusQuo> content;
  private LocationOfStation location;

  public DustInfoByStationDto() {}

  public List<DustStatusQuo> getContent() {
    return content;
  }

  public void setContent(List<DustStatusQuo> content) {
    this.content = content;
  }

  public LocationOfStation getLocation() {
    return location;
  }

  public void setLocation(LocationOfStation location) {
    this.location = location;
  }
}
