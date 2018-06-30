package com.rss;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rss.config.SecurityUtility;
import com.rss.model.User;
import com.rss.model.security.Role;
import com.rss.model.security.UserRole;
import com.rss.services.UserService; 


@SpringBootApplication
public class RssApplication implements CommandLineRunner {
 
    private UserService userService; 
    
    public RssApplication(UserService userService) { 
	this.userService = userService;
    }

    public static void main(String[] args) {
	SpringApplication.run(RssApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

	User user1 = new User();
	user1.setFirstName("Yashu");
	user1.setLastName("g");
	user1.setUsername("yashu");
	user1.setPassword(SecurityUtility.passwordEncoder().encode("pass"));
	user1.setEmail("yashu@g.com");
	Set<UserRole> userRoles = new HashSet<>();
	Role role1 = new Role();
	role1.setRoleId(1);
	role1.setName("ROLE_ADMIN");
	userRoles.add(new UserRole(user1, role1));

	userService.createUser(user1, userRoles);

	userRoles.clear();

	User user2 = new User();
	user2.setFirstName("Sheetal");
	user2.setLastName("s");
	user2.setUsername("sheetal");
	user2.setPassword(SecurityUtility.passwordEncoder().encode("password"));
	user2.setEmail("s@g.com");
	Role role2 = new Role();
	role2.setRoleId(0);
	role2.setName("ROLE_USER");
	userRoles.add(new UserRole(user2, role2));

	userService.createUser(user2, userRoles);

    }
}
