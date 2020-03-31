package com.codesquad.dust4.dto;

public class ForecastResponseDto {
  private Object content;

  public ForecastResponseDto() {}

  public ForecastResponseDto(String content) {
    this.content = content;
  }

  public Object getContent() {
    return content;
  }

  public void setContent(Object content) {
    this.content = content;
  }
}
