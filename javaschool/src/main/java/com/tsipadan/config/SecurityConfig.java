package com.tsipadan.config;

import com.tsipadan.authentication.UserDetailsServiceImpl;
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

  private final UserDetailsServiceImpl userService;

  //bCrypt password encoder
  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    return new BCryptPasswordEncoder();
  }

  //config userDetailsService and save admin in memory
  @Autowired
  public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    auth.inMemoryAuthentication().withUser("admin").password(bCryptPasswordEncoder().encode("123")).roles("ADMIN");
  }

  //Security configuration
  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.csrf().disable();

    //for all
    http.authorizeRequests().antMatchers("/newRegistration", "/login",
        "/home", "/store").permitAll();

    //for customer
    http.authorizeRequests()
        .antMatchers("/accountInformation", "/viewAccInfo",
            "/editAccInfo", "/editPassword", "/cart", "/cartCustomerForm", "/cartConfirm",
            "/cartFinalPage", "/editAddressInfo",
            "/userOrderHistory"
        )
        .access("hasAnyRole('ROLE_CUSTOMER')");

    //for admin
    http.authorizeRequests()
        .antMatchers("/adminPage", "/createProduct","/updateProduct",
            "/cart", "/cartCustomerForm", "/cartConfirm", "/cartFinalPage",
            "/itemCategory", "/newItemCategory", "/editItemCategory",
            "/orderListAdmin", "/orderListAdminDetails"
        )
        .access("hasAnyRole('ROLE_ADMIN')");

    //catch errors
    http.exceptionHandling().and().exceptionHandling().accessDeniedPage("/error");

    //login form
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
