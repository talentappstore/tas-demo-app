package com.talentappstore.apps.demoapp.tas.model;

/**
 * Model class for returning the app status to TalentAppStore for a tenant.
 */
public class AppStatus
{
  private String landingPage;
  private String settingsPage;
  private boolean setupRequired = false;


  public String getLandingPage()
  {
    return landingPage;
  }

  public void setLandingPage(String landingPage)
  {
    this.landingPage = landingPage;
  }

  public String getSettingsPage()
  {
    return settingsPage;
  }

  public void setSettingsPage(String settingsPage)
  {
    this.settingsPage = settingsPage;
  }

  public boolean isSetupRequired()
  {
    return setupRequired;
  }

  public void setSetupRequired(boolean setupRequired)
  {
    this.setupRequired = setupRequired;
  }

}
