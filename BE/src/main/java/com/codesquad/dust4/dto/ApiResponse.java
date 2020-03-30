package com.codesquad.dust4.dto;

public class ApiResponse {
  private String HttpStatus;
  private Object content;

  public ApiResponse() {}

  public ApiResponse(String HttpStatus, String content) {
    this.HttpStatus = HttpStatus;
    this.content = content;
  }

  public ApiResponse(String HttpStatus, Object content) {
    this.HttpStatus = HttpStatus;
    this.content = content;
  }

  public String getHttpStatus() {
    return HttpStatus;
  }

  public void setHttpStatus(String httpStatus) {
    HttpStatus = httpStatus;
  }

  public Object getContent() {
    return content;
  }

  public void setContent(Object content) {
    this.content = content;
  }
}
