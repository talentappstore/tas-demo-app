package com.talentappstore.apps.demoapp.service;

import com.talentappstore.apps.demoapp.tas.model.AppStatus;
import com.talentappstore.apps.demoapp.tas.model.Tenant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service to handle anything tenant related.
 */
@Service
public class TenantService
{
  private Logger log = LoggerFactory.getLogger(TenantService.class);

  /**
   * Tenant has installed your app.
   *
   * @param tenant
   */
  public void createTenant(Tenant tenant)
  {
    // Save new tenant.
    log.debug("Tenant {} installed app", tenant.getShortCode());
  }


  /**
   * Called by TAS to get app status for a tenant.
   *
   * @param tenantShortCode
   * @return
   */
  public AppStatus getTenantStatus(String tenantShortCode)
  {
    log.debug("TAS requested appstatus for tenant {}", tenantShortCode);
    // Return the app status for the given tenant. For the purpose of this example we'll return setup required.
    AppStatus appStatus = new AppStatus();
    appStatus.setSettingsPage("https://www.test.com/setup");
    appStatus.setSetupRequired(true);
    return appStatus;
  }

  /**
   * Tenant has uninstalled your app.
   *
   * @param tenantShortCode
   */
  public void uninstallTenant(String tenantShortCode)
  {
    // Delete tenant
    log.debug("Tenant {} uninstalled app", tenantShortCode);
  }


}
