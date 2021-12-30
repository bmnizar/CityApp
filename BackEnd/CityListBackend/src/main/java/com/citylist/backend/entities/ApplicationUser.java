package com.citylist.backend.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

/**
 ** @BMN 2021
 **
 **/
@Entity
public class ApplicationUser {

	@Id
	@SequenceGenerator(name = "application_user_seq", sequenceName = "application_user_seq", allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_user_seq")
	private Long id;

	private String username;
	private String password;

	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<ApplicationRole> applicationRoles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<ApplicationRole> getApplicationRoles() {
		return applicationRoles;
	}

	public void setApplicationRoles(Collection<ApplicationRole> applicationRoles) {
		this.applicationRoles = applicationRoles;
	}

}