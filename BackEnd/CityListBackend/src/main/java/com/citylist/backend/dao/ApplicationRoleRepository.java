package com.citylist.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citylist.backend.entities.ApplicationRole;

/**
 ** @BMN 2021
 **
 **/
@Repository
public interface ApplicationRoleRepository extends JpaRepository<ApplicationRole, Long> {

	ApplicationRole findByName(String name);

}
