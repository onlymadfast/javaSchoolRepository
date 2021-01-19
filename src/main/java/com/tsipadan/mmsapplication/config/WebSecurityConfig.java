package com.tsipadan.mmsapplication.config;

import com.tsipadan.mmsapplication.authentication.MyDBAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final MyDBAuthenticationService myDBAuthenticationService;

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(myDBAuthenticationService);
  }

  protected void configure(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.csrf().disable();

    httpSecurity.authorizeRequests()
        .antMatchers("/orderList/", "/order", "/product")
        .access("hasAnyRole('ROLE_ADMINISTRATOR')");

    httpSecurity.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

    httpSecurity.authorizeRequests().and().formLogin()
        .loginProcessingUrl("/j_spring_security_check")
        .loginPage("/log_in")
        .defaultSuccessUrl("/accountInfo")
        .failureUrl("/log_in?error=true")
        .usernameParameter("userName")
        .passwordParameter("password")
        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/");

  }

}
