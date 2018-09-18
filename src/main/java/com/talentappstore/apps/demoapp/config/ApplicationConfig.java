package com.talentappstore.apps.demoapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
public class ApplicationConfig
{

  @Autowired
  public ApplicationConfig()
  {
    System.setProperty("java.net.preferIPv4Stack", "true");
  }

}
