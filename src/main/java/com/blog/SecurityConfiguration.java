package com.blog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
        .authorizeRequests()
          .antMatchers("/","/videos","/BlogPost","/index","/aboutUs","/searchIndex").permitAll()
          //.anyRequest().authenticated()
          .anyRequest().authenticated()
          .and()
        .formLogin()
          .loginPage("/login")
          .failureUrl("/login-error.html")
          .permitAll()
          .defaultSuccessUrl("/newPost");
    } 
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
    {
      try 
      {
		    auth 
  		    .inMemoryAuthentication()
  		      .withUser("admin")
  		        .password("password")
  		        .roles("ADMIN");
  	  } catch (Exception e) {
  	    e.printStackTrace();
  	  }
    }
}