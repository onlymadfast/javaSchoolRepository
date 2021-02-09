//package com.tsipadan.mmsapplication.config;
//
//import com.tsipadan.mmsapplication.authentication.MyDBAuthenticationService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//  private final DataSource dataSource;
//
//  private final MyDBAuthenticationService myDBAuthenticationService;
//
//  @Bean
//  public PasswordEncoder encoder(){
//    return new BCryptPasswordEncoder();
//  }
//
//  @Autowired
//  public void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//
//    authenticationManagerBuilder.userDetailsService(myDBAuthenticationService);
//    authenticationManagerBuilder.jdbcAuthentication().dataSource(dataSource)
//        .passwordEncoder(encoder())
//        .usersByUsernameQuery("SELECT u from Account u WHERE u.userName")
//        .authoritiesByUsernameQuery("SELECT r from Account r WHERE r.userRole");
//  }
//
//  protected void configure(HttpSecurity httpSecurity) throws Exception {
//
//    httpSecurity.csrf().disable();
//
//    httpSecurity.authorizeRequests()
//        .antMatchers("/index","/log_in","productList"
//            ,"shoppingCart","shoppingCartConfirmation","shoppingCartCustomer","shoppingCartFinalize")
//        .permitAll();
//
//    httpSecurity.authorizeRequests()
//        .antMatchers("/orderHistoryList","/changePasswordPage")
//        .access("hasAnyRole('ROLE_CUSTOMER')");
//
//    httpSecurity.authorizeRequests()
//        .antMatchers("/accountInfo")
//        .access("hasAnyRole('ROLE_ADMINISTRATOR','ROLE_CUSTOMER')");
//
//    httpSecurity.authorizeRequests()
//        .antMatchers("/orderList", "/order", "/product")
//        .access("hasAnyRole('ROLE_ADMINISTRATOR')");
//
//    httpSecurity.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
//
//    httpSecurity.authorizeRequests().and().formLogin()
//        .loginProcessingUrl("/j_spring_security_check")
//        .loginPage("/log_in")
//        .defaultSuccessUrl("/accountInfo")
//        .failureUrl("/log_in?error=true")
//        .usernameParameter("userName")
//        .passwordParameter("password")
//        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/home");
//
//  }
//
//}
