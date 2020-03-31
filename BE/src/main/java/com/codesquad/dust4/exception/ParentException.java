package com.codesquad.dust4.exception;


import com.codesquad.dust4.dto.ApiResponse;

public class ParentException extends RuntimeException {
  private String errorMessage;

  public ParentException(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public ApiResponse returnErrorMessage() {
    return new ApiResponse("ERROR", errorMessage);
  }
}
