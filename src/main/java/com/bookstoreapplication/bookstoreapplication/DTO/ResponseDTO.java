package com.bookstoreapplication.bookstoreapplication.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object for Response body
 * Generating getters,setters and constructors using lombook
 **/
@Data
@AllArgsConstructor(staticName = "Build")
@NoArgsConstructor
public class ResponseDTO {
  private String message;
  private Object data;
}
