package com.talentappstore.apps.demoapp.tazzy.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


/**
 * Filter to check that requests contain the tazzy-secret header and that it is equal to what we expect it to be.
 * This is how we ascertain that a request has been passed to us from Tazzy.
 * <p>
 * It is critical for this to occur otherwise anyone could be calling your app without the correct authentication/privileges.
 */
public class TazzySecretFilter extends GenericFilterBean
{

  public static final String TAZZY_SECRET = "tazzy-secret";
  private Environment environment;
  private List<AntPathRequestMatcher> allowMatchers;

  public TazzySecretFilter(Environment environment, List<AntPathRequestMatcher> allowMatchers)
  {
    this.environment = environment;
    this.allowMatchers = allowMatchers;
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
  {
    // Dont apply filter to any of the allowed matchers
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    if (allowMatchers.stream().anyMatch(matcher -> matcher.matches(httpServletRequest)))
    {
      chain.doFilter(request, response);
      return;
    }

    String tazzySecret = ((HttpServletRequest) request).getHeader(TAZZY_SECRET);
    if (StringUtils.isBlank(tazzySecret))
    {
      throw new BadCredentialsException(TAZZY_SECRET + " header was not found");
    }

    if (!StringUtils.equals(tazzySecret, environment.getProperty("tas.app.secret")))
    {
      throw new BadCredentialsException(TAZZY_SECRET + " was invalid");
    }

    chain.doFilter(request, response);

  }

}
