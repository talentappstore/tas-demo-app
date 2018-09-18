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

  @PostMapping("/tas/core/tenants")
  public void installTenant(@RequestBody Tenant tenant)
  {
    tenantService.createTenant(tenant);
  }

  @DeleteMapping("/tas/core/tenants/{tenant}")
  public void uninstallTenant(@PathVariable("tenant") String tenantShortCode)
  {
    tenantService.uninstallTenant(tenantShortCode);
  }

  @GetMapping("/t/{tenant}/tas/devs/tas/appStatus")
  public AppStatus appStatus(@PathVariable("tenant") String tenantShortCode) throws Exception
  {
    return tenantService.getTenantStatus(tenantShortCode);
  }

}
