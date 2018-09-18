package com.talentappstore.apps.demoapp.config;

import com.talentappstore.apps.demoapp.service.TasCoreInService;
import com.talentappstore.apps.demoapp.tazzy.TazzySamlAuthenticationProvider;
import com.talentappstore.apps.demoapp.tazzy.filter.TazzySamlFilter;
import com.talentappstore.apps.demoapp.tazzy.filter.TazzySecretFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{

  @Autowired
  private TasCoreInService tasCoreInService;

  @Autowired
  private Environment environment;

  @Bean
  public TazzySecretFilter tazzySecretFilter()
  {
    return new TazzySecretFilter(environment, matchers());
  }

  @Bean
  public TazzySamlFilter tazzySamlFilter() throws Exception
  {
    return new TazzySamlFilter(authenticationManager(), tasCoreInService);
  }

  @Bean
  public TazzySamlAuthenticationProvider tazzySamlAuthenticationProvider()
  {
    return new TazzySamlAuthenticationProvider();
  }

  @Bean
  public PasswordEncoder passwordEncoder()
  {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(WebSecurity web)
  {
    List<AntPathRequestMatcher> matchers = matchers();
    web.ignoring().requestMatchers(matchers.toArray(new AntPathRequestMatcher[0]));
  }

  private List<AntPathRequestMatcher> matchers()
  {
    List<AntPathRequestMatcher> matchers = new ArrayList<>();
    matchers.add(new AntPathRequestMatcher("/"));
    matchers.add(new AntPathRequestMatcher("/error"));
    matchers.add(new AntPathRequestMatcher("/tas-logo.svg"));
    matchers.add(new AntPathRequestMatcher("/favicon-32x32.png"));

    return matchers;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception
  {
    http.csrf().disable();
    http.authenticationProvider(tazzySamlAuthenticationProvider());
    http.logout().logoutSuccessUrl("/");
    http.authorizeRequests().antMatchers("/**").permitAll();
    http.addFilterAfter(tazzySecretFilter(), AbstractPreAuthenticatedProcessingFilter.class);
    http.addFilterAfter(tazzySamlFilter(), AbstractPreAuthenticatedProcessingFilter.class);
  }

}
