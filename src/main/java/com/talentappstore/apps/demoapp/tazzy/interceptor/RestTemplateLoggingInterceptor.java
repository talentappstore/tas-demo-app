package com.talentappstore.apps.demoapp.tazzy.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;

/**
 * Debug logging of request/responses.
 */

public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor
{

  private Logger log = LoggerFactory.getLogger(RestTemplateLoggingInterceptor.class);

  @Override
  public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException
  {

    logRequest(request, body);
    ClientHttpResponse response = execution.execute(request, body);
    logResponse(response);
    return response;
  }

  private void logRequest(HttpRequest request, byte[] body)
  {
    log.debug("*** Request ***");
    log.debug("URI: {} {}", request.getMethod(), request.getURI());
    log.debug("Headers: {}", request.getHeaders());
    log.debug("Body: {}", new String(body));
    log.debug("*** Request end ***");
  }

  private void logResponse(ClientHttpResponse response) throws IOException
  {
    log.debug("*** Response ***");
    log.debug("Status code: {} {}", response.getStatusCode(), response.getStatusText());
    log.debug("Headers: {}", response.getHeaders());
    log.debug("*** Response End ***");
  }


}
