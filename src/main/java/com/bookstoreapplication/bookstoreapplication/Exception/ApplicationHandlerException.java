package com.bookstoreapplication.bookstoreapplication.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationHandlerException extends Exception {

  // ---------------------------Method arguments invalid
  // exception--------------------------//
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> methodArgumentException(MethodArgumentNotValidException ex) {
    Map<String, String> errorMap = new HashMap<>();

    ex.getBindingResult().getFieldErrors().forEach(errors -> {
      errorMap.put(errors.getField(), errors.getDefaultMessage());
    });
    return errorMap;
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(BookNotFoundException.class)
  public Map<String, String> handleBusinessException(BookNotFoundException ex) {
    Map<String, String> errorMap = new HashMap<>();

    errorMap.put("Error Message", ex.getMessage());
    return errorMap;
  }
//-------------------------User not found exception-----------------------------------//
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(UserNotFoundException.class)
  public Map<String, String> handleBusinessException(UserNotFoundException ex) {
    Map<String, String> errorMap = new HashMap<>();

    errorMap.put("Error Message", ex.getMessage());
    return errorMap;
  }

  //---------------------------Username password invalid exception----------------------//
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(UsernamePasswordInvalidException.class)
  public Map<String, String> handleBusinessException(UsernamePasswordInvalidException ex) {
      Map<String, String> errorMap = new HashMap<>();

      errorMap.put("Error Message", ex.getMessage());
      return errorMap;
  }


  //---------------------------Cart item not found exception----------------------//
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(CartItemNotFoundException.class)
  public Map<String, String> handleBusinessException(CartItemNotFoundException ex) {
      Map<String, String> errorMap = new HashMap<>();
      errorMap.put("Error Message", ex.getMessage());
      return errorMap;
  }
}
