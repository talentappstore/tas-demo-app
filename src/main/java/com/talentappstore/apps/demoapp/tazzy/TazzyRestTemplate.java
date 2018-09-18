package com.talentappstore.apps.demoapp.tazzy;

import com.talentappstore.apps.demoapp.tazzy.interceptor.RestTemplateLoggingInterceptor;
import com.talentappstore.apps.demoapp.tazzy.interceptor.TazzyRestTemplateHeadersInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * An implementation of RestTemplate with interceptors configured for talking to TalentAppStore.
 */

@Component
public class TazzyRestTemplate extends RestTemplate
{

  @Autowired
  public TazzyRestTemplate(TazzyRestTemplateHeadersInterceptor tazzyRestTemplateHeadersInterceptor)
  {

    super();

    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();

    requestFactory.setBufferRequestBody(false); // allows posting of large files via resttemplate

    setRequestFactory(requestFactory);

    getInterceptors().add(tazzyRestTemplateHeadersInterceptor);
    getInterceptors().add(new RestTemplateLoggingInterceptor());

  }

}
