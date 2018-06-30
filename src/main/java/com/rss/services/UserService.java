package com.rss.services;

import java.util.Set;

import com.rss.model.User;
import com.rss.model.security.UserRole;

 
public interface UserService {
     
    
   User createUser(User user,Set<UserRole> userRoles);

}
