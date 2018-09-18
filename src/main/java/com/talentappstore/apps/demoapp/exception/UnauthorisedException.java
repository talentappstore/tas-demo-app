package com.talentappstore.apps.demoapp.exception;

public class UnauthorisedException extends BaseException
{

  public UnauthorisedException()
  {
    super("Unauthorised.", 401);
  }
}
