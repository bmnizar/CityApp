package com.citylist.backend.services;

import com.citylist.backend.entities.ApplicationUser;
import com.citylist.backend.rest.dto.ApplicationUserDTO;

/**
 ** @BMN 2021
 **
 **/
public interface UserService {

	void save(ApplicationUser applicationUser);

	Boolean login(ApplicationUserDTO applicationUser);

}
