package com.talentappstore.apps.demoapp.tazzy.filter;

import com.talentappstore.apps.demoapp.service.TasCoreInService;
import com.talentappstore.apps.demoapp.tas.model.SamlAssertions;
import com.talentappstore.apps.demoapp.tazzy.TasSamlPrinciple;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * Filter to extract the SAML details from a request from Tazzy.
 * <p>
 * Tazzy adds SAML details to a tazzy-saml header when requests are passed through it.
 * You can use this to find out the details of the current user.
 */
public class TazzySamlFilter extends AbstractAuthenticationProcessingFilter
{

  private static final String TAZZY_SAML = "tazzy-saml";

  private TasCoreInService tasCoreInService;

  public TazzySamlFilter(AuthenticationManager authenticationManager, TasCoreInService tasCoreInService)
  {
    super(new AndRequestMatcher(new AntPathRequestMatcher("/t/**"),
                                new NegatedRequestMatcher(new AntPathRequestMatcher("/t/**/tas/logout")),
                                new NegatedRequestMatcher(new AntPathRequestMatcher("/t/**/tas/devs/**"))));

    setAuthenticationManager(authenticationManager);
    this.tasCoreInService = tasCoreInService;

    SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
    successHandler.setRedirectStrategy((request, response, url) ->
                                       {
                                       });

    setAuthenticationSuccessHandler(successHandler);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException
  {
    String tazzySaml = request.getHeader(TAZZY_SAML);

    if (StringUtils.isBlank(tazzySaml))
    {
      throw new BadCredentialsException(TAZZY_SAML + " header was not found");
    }
    String tenant = getTenant(request);
    SamlAssertions samlAssertions = tasCoreInService.getSamlAssertions(tenant, tazzySaml);
    return new TasSamlPrinciple(samlAssertions.getEntityId(), samlAssertions.getNameId(), samlAssertions.getEmail(),
                                samlAssertions.getGivenName(), samlAssertions.getFamilyName(), samlAssertions.getImage(),
                                Collections.emptyList(), tenant);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                          Authentication authResult) throws IOException, ServletException
  {
    super.successfulAuthentication(request, response, chain, authResult);
    chain.doFilter(request, response);
  }

  private String getTenant(HttpServletRequest request)
  {
    return StringUtils.substringBefore(StringUtils.substringAfter(request.getServletPath(), "/t/"), "/");
  }

}
