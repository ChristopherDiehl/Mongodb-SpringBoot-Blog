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
          .antMatchers("/","/portfolio","/BlogPost","/index","/aboutUs","/searchIndex","/comment","/resume").permitAll()
          .anyRequest().authenticated()
          .and()
        .formLogin()
          .loginPage("/login")
          .failureUrl("/login-error")
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
  		        .password("admin")
  		        .roles("ADMIN");
  	  } catch (Exception e) {
  	    e.printStackTrace();
  	  }
    }
}