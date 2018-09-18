package com.talentappstore.apps.demoapp.tas.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 */
public class SamlAssertions
{

  @JsonProperty("entityID")
  private String entityId;

  @JsonProperty("nameID")
  private String nameId;

  @JsonProperty("tas.personal.givenName")
  private String givenName;

  @JsonProperty("tas.personal.familyName")
  private String familyName;

  @JsonProperty("tas.personal.email")
  private String email;

  @JsonProperty("tas.personal.image")
  private String image;

  public SamlAssertions()
  {
  }

  public String getEntityId()
  {
    return entityId;
  }

  public void setEntityId(String entityId)
  {
    this.entityId = entityId;
  }

  public String getNameId()
  {
    return nameId;
  }

  public void setNameId(String nameId)
  {
    this.nameId = nameId;
  }

  public String getGivenName()
  {
    return givenName;
  }

  public void setGivenName(String givenName)
  {
    this.givenName = givenName;
  }

  public String getFamilyName()
  {
    return familyName;
  }

  public void setFamilyName(String familyName)
  {
    this.familyName = familyName;
  }

  public String getEmail()
  {
    return email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getImage()
  {
    return image;
  }

  public void setImage(String image)
  {
    this.image = image;
  }

}
