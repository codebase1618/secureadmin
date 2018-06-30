package com.rss.config;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.rss.services.UserSecurityService; 

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
	  prePostEnabled = true, 
	  securedEnabled = true, 
	  jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
    
    @Autowired
    private UserSecurityService userSecurityService;
    
    private BCryptPasswordEncoder passwordEncoder()
    {
	return SecurityUtility.passwordEncoder();
    }
   
    
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
	http.csrf().disable().cors().disable()
	.httpBasic() 
	.and()
	.formLogin()
	.defaultSuccessUrl("/welcome",true)
	.and()
	.authorizeRequests() 
	.anyRequest().authenticated();
	 
    }
    
   

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
	auth.userDetailsService(userSecurityService).passwordEncoder(passwordEncoder());
    }
     
    
}
