package com.talentappstore.apps.demoapp.tazzy.interceptor;

import com.talentappstore.apps.demoapp.tazzy.filter.TazzySecretFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * An interceptor to add our tazzy-secret header to requests we make to TalentAppStore APIs
 * so Tazzy knows they're from this app.
 */
@Component
public class TazzyRestTemplateHeadersInterceptor implements ClientHttpRequestInterceptor
{

  private Environment environment;


  @Autowired
  public TazzyRestTemplateHeadersInterceptor(Environment environment)
  {
    this.environment = environment;
  }

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException
  {

    HttpHeaders headers = request.getHeaders();
    headers.add(TazzySecretFilter.TAZZY_SECRET, environment.getProperty("tas.app.secret"));

    return execution.execute(request, body);
  }


}
