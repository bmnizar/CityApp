package com.citylist.backend.rest.dto;

import java.util.Collection;

/**
 ** @BMN 2021
 **
 **/
public class ApplicationRoleDTO {
	private String name;

	private Collection<ApplicationUserDTO> applicationUsers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<ApplicationUserDTO> getApplicationUsers() {
		return applicationUsers;
	}

	public void setApplicationUsers(Collection<ApplicationUserDTO> applicationUsers) {
		this.applicationUsers = applicationUsers;
	}

}
