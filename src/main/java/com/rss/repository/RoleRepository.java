package com.rss.repository;

import org.springframework.data.jpa.repository.JpaRepository; 

import com.rss.model.security.Role;
 
public interface RoleRepository extends JpaRepository<Role, Long> {

}
