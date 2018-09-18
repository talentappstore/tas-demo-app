package com.talentappstore.apps.demoapp.tazzy;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class TasSamlPrinciple implements Authentication
{

  private static final long serialVersionUID = 1L;

  private final String entityId;
  private final String nameId;
  private final String email;
  private final String givenName;
  private final String familyName;
  private final String image;
  private final String tenant;
  private List<GrantedAuthority> authorities;
  private boolean authenticated;

  public TasSamlPrinciple(String entityId, String nameId, String email, String givenName, String familyName,
                          String image, List<GrantedAuthority> authorities, String tenant)
  {
    this.entityId = entityId;
    this.nameId = nameId;
    this.email = email;
    this.givenName = givenName;
    this.familyName = familyName;
    this.image = image;
    this.authorities = authorities;
    this.tenant = tenant;
  }

  public String getEntityId()
  {
    return entityId;
  }

  public String getNameId()
  {
    return nameId;
  }

  public String getEmail()
  {
    return email;
  }

  public String getGivenName()
  {
    return givenName;
  }

  public String getFamilyName()
  {
    return familyName;
  }

  public String getImage()
  {
    return image;
  }


  public String getTenant()
  {
    return tenant;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities()
  {
    return authorities;
  }

  @Override
  public String getName()
  {
    return givenName + " " + familyName;
  }

  @Override
  public Object getCredentials()
  {
    return null;
  }

  @Override
  public Object getDetails()
  {
    return null;
  }

  @Override
  public Object getPrincipal()
  {
    return entityId + "/" + nameId;
  }

  @Override
  public boolean isAuthenticated()
  {
    return authenticated;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException
  {
    this.authenticated = isAuthenticated;
  }

}
