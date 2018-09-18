package com.talentappstore.apps.demoapp.exception;

/**
 * Class for all application checked exceptions to extend from. Makes for tidier error handling being able to catch
 * BaseException and handling all errors in a common way.
 */
abstract class BaseException extends Exception
{
  private final int httpStatusCode;

  public BaseException(String s, int httpStatusCode)
  {
    super(s);
    this.httpStatusCode = httpStatusCode;
  }

  public int getHttpStatusCode()
  {
    return httpStatusCode;
  }
}
