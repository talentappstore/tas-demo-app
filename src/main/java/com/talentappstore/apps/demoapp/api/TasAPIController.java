package com.talentappstore.apps.demoapp.api;

import com.talentappstore.apps.demoapp.service.TenantService;
import com.talentappstore.apps.demoapp.tas.model.AppStatus;
import com.talentappstore.apps.demoapp.tas.model.Tenant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * This controller contains APIs published by the app which TalentAppStore will call.
 */
@RestController
public class TasAPIController
{
  private TenantService tenantService;

  @Autowired
  public TasAPIController(TenantService tenantService)
  {
    this.tenantService = tenantService;
  }

  /**
   * This endpoint will be called by TalentAppStore when a tenant installs your app.
   *
   * @param tenant
   */
  @PostMapping("/tas/core/tenants")
  public void installTenant(@RequestBody Tenant tenant)
  {
    tenantService.createTenant(tenant);
  }

  /**
   * This endpoint will be called by TalentAppStore when a tenant uninstalls your app.
   *
   * @param tenantShortCode
   */
  @DeleteMapping("/tas/core/tenants/{tenant}")
  public void uninstallTenant(@PathVariable("tenant") String tenantShortCode)
  {
    tenantService.uninstallTenant(tenantShortCode);
  }

  /**
   * This endpoint is called by TalentAppStore to retrieve the status of your app for a given tenant.
   *
   * @param tenantShortCode
   * @return
   * @throws Exception
   */
  @GetMapping("/t/{tenant}/tas/devs/tas/appStatus")
  public AppStatus appStatus(@PathVariable("tenant") String tenantShortCode)
  {
    return tenantService.getTenantStatus(tenantShortCode);
  }

}
