package com.talentappstore.apps.demoapp.tas.model;

public class Tenant
{
  private String shortCode;
  private String name;
  private String contactEmail;
  private String type;
  private String logoUrl;

  public Tenant()
  {
  }

  public String getShortCode()
  {
    return shortCode;
  }

  public void setShortCode(String shortCode)
  {
    this.shortCode = shortCode;
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public String getContactEmail()
  {
    return contactEmail;
  }

  public void setContactEmail(String contactEmail)
  {
    this.contactEmail = contactEmail;
  }

  public String getType()
  {
    return type;
  }

  public void setType(String type)
  {
    this.type = type;
  }

  public String getLogoUrl()
  {
    return logoUrl;
  }

  public void setLogoUrl(String logoUrl)
  {
    this.logoUrl = logoUrl;
  }

}
