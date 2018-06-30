package com.rss.services;

import java.util.Set; 

import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.stereotype.Service;

import com.rss.model.User;
import com.rss.model.security.UserRole;
import com.rss.repository.RoleRepository;
import com.rss.repository.UserRepository;
import com.rss.services.UserService;

@Service
public class UserServiceImpl implements UserService{
    
    private static final Logger LOG=LoggerFactory.getLogger(UserServiceImpl.class);
 
    private UserRepository userRepository;
 
    private RoleRepository roleRepository; 
    
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
	super();
	this.userRepository = userRepository;
	this.roleRepository = roleRepository;
    } 

    @Override
    @Transactional
    public User createUser(User user, Set<UserRole> userRoles) {
	User localUser = userRepository.findByUsername(user.getUsername());
	
	if(localUser != null) {
		LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
	} else {
		for (UserRole ur : userRoles) {
			roleRepository.save(ur.getRole());
		}
		
		user.getUserRoles().addAll(userRoles);
		
		localUser = userRepository.save(user);
	}
	
	return localUser;
    }

}
