package com.talentappstore.apps.demoapp.service;

import com.talentappstore.apps.demoapp.tas.model.SamlAssertions;
import com.talentappstore.apps.demoapp.tas.model.Tenant;
import com.talentappstore.apps.demoapp.tazzy.TazzyRestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 * Service to handle calls to TAS core APIs.
 */
@Service
public class TasCoreInService
{

  private Logger log = LoggerFactory.getLogger(TasCoreInService.class);

  private TazzyRestTemplate tazzyRestTemplate;
  private Environment environment;

  @Autowired
  public TasCoreInService(TazzyRestTemplate tazzyRestTemplate, Environment environment)
  {
    this.tazzyRestTemplate = tazzyRestTemplate;
    this.environment = environment;
  }

  public Tenant getTenant(String tenantShortCode)
  {
    String coreInURL = environment.getProperty("tas.core.in.url.template") + "/tenants/{tenant}";
    return tazzyRestTemplate.getForObject(coreInURL, Tenant.class, tenantShortCode);
  }

  public SamlAssertions getSamlAssertions(String tenantShortCode, String tazzySaml)
  {
    try
    {
      return tazzyRestTemplate.getForObject(environment.getProperty("tas.core.in.url.template") + "/tenants/{tenant}/saml/assertions/byKey/{key}/json", SamlAssertions.class,
                                            tenantShortCode, tazzySaml);
    }
    catch (Exception e)
    {
      log.error("Error retrieving SAML assertions {} {}", tenantShortCode, tazzySaml, e);
    }
    return null;
  }

}
