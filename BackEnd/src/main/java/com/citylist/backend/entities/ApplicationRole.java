package com.citylist.backend.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

/**
 ** @BMN 2021
 **
 **/
@Entity
public class ApplicationRole {
	@Id
	@SequenceGenerator(name = "application_role_seq", sequenceName = "application_role_seq", allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_role_seq")
	private Long id;
	private String name;
	@ManyToMany 
	private Collection<ApplicationUser> applicationUsers;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<ApplicationUser> getApplicationUsers() {
		return applicationUsers;
	}

	public void setApplicationUsers(Collection<ApplicationUser> applicationUsers) {
		this.applicationUsers = applicationUsers;
	}

}
