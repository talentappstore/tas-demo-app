package com.talentappstore.apps.demoapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * Exception handler to map all BaseException exceptions to a nice(r) error page view.
 */

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler
{

  @ExceptionHandler(value = {BaseException.class})
  protected ResponseEntity<Object> handleBaseException(BaseException ex, WebRequest request)
  {
    Map<String, String> responseBody = new HashMap<>();
    responseBody.put("error", ex.getMessage());
    return handleExceptionInternal(ex, responseBody,
                                   new HttpHeaders(), HttpStatus.resolve(ex.getHttpStatusCode()), request);
  }

  @ExceptionHandler(value = {Exception.class})
  protected ModelAndView handleUncaughtException(Exception ex, WebRequest request)
  {
    ModelAndView mav = new ModelAndView("error");
    mav.addObject("status", 500);
    mav.addObject("error", "Something went wrong ¯\\_(ツ)_/¯");
    return mav;
  }

}
