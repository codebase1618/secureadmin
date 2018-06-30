package com.rss.resources;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;  

@Controller
public class MainResource {

     
    @GetMapping("/welcome")
    public ModelAndView onSuccess(Principal principal)
    { 
	ModelAndView model= new ModelAndView("success");
	model.addObject("username",principal.getName());
	return model;
    } 
      
    
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView adminSetting()
    {
	return new ModelAndView("adminPage");
    }
    
    @ExceptionHandler(value=AccessDeniedException.class)
    @ResponseStatus(code=HttpStatus.FORBIDDEN)
    public ModelAndView accessDenied()
    { 
	 
	return new ModelAndView("noAccess");
    } 
    
}
