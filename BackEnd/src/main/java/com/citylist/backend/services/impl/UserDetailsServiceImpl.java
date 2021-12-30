package com.citylist.backend.services.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citylist.backend.dao.UserRepository;
import com.citylist.backend.entities.ApplicationRole;
import com.citylist.backend.entities.ApplicationUser;

/**
 ** @BMN 2021
 **
 **/
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		ApplicationUser applicationUser = userRepository.findByUsername(username);
		if (applicationUser == null) {
			// throw new CityListBackendException("Unable to find the user in the DB " +
			// username);
			return null;
		}
		Collection<ApplicationRole> applicationRoles = applicationUser.getApplicationRoles();
		Collection<? extends GrantedAuthority> authorities = getAuthorities(applicationRoles);
		return new org.springframework.security.core.userdetails.User(username, applicationUser.getPassword(), true,
				true, true, true, authorities);
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<ApplicationRole> roles) {

		return getGrantedAuthorities(roles);
	}

	private List<GrantedAuthority> getGrantedAuthorities(Collection<ApplicationRole> applicationRoles) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (ApplicationRole applicationRole : applicationRoles) {
			authorities.add(new SimpleGrantedAuthority(applicationRole.getName()));
		}
		return authorities;
	}

}