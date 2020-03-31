package com.codesquad.dust4.exception;
import com.codesquad.dust4.dto.ForecastResponseDto;

public class ParentException extends RuntimeException {
  private String errorMessage;

  public ParentException(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public ForecastResponseDto returnErrorMessage() {
    return new ForecastResponseDto(errorMessage);
  }
}
