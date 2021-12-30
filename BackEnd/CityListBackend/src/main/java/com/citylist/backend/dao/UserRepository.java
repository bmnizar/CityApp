package com.citylist.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citylist.backend.entities.ApplicationUser;

/**
 ** @BMN 2021
 **
 **/
@Repository
public interface UserRepository extends JpaRepository<ApplicationUser, Long> {
	ApplicationUser findByUsername(String username);
	ApplicationUser findByUsernameAndPassword(String username,String password);

}
