package com.citylist.backend.rest.dto;

import java.util.Collection;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.citylist.backend.entities.ApplicationRole;

/**
 ** @BMN 2021
 **
 **/
public class ApplicationUserDTO {
	private String username;
	private String password;

	private Collection<ApplicationRoleDTO> applicationRoles;

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

	public Collection<ApplicationRoleDTO> getApplicationRoles() {
		return applicationRoles;
	}

	public void setApplicationRoles(Collection<ApplicationRoleDTO> applicationRoles) {
		this.applicationRoles = applicationRoles;
	}

}
