package com.tsipadan.mmsapplication.config;

import com.tsipadan.mmsapplication.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserService userService;

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    auth.inMemoryAuthentication().withUser("admin").password(bCryptPasswordEncoder().encode("123")).roles("ADMIN");
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.csrf().disable();

    http.authorizeRequests().antMatchers("/newRegistration", "/login",
        "/home", "/store").permitAll();

    http.authorizeRequests()
        .antMatchers("/accountInformation", "/viewAccInfo",
            "/editAccInfo", "/editPassword")
        .access("hasAnyRole('ROLE_CUSTOMER')");

    http.authorizeRequests()
        .antMatchers("/adminPage", "/createProduct")
        .access("hasAnyRole('ROLE_ADMIN')");

    http.exceptionHandling().and().exceptionHandling().accessDeniedPage("/error");

    http.authorizeRequests().and().formLogin()
        .loginProcessingUrl("/springSecurity")
        .loginPage("/login")
        .defaultSuccessUrl("/home")
        .failureUrl("/login?error=true")
        .usernameParameter("userName")
        .passwordParameter("userPassword")
        .and().logout().logoutUrl("/logout")
        .logoutSuccessUrl("/home");
  }
}
