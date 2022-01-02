package com.citylist.backend.services.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.citylist.backend.dao.ApplicationRoleRepository;
import com.citylist.backend.dao.UserRepository;
import com.citylist.backend.entities.ApplicationRole;
import com.citylist.backend.entities.ApplicationUser;
import com.citylist.backend.mapstruct.MapStructMapper;
import com.citylist.backend.rest.dto.ApplicationUserDTO;
import com.citylist.backend.services.UserService;
import com.citylist.backend.utils.CityAppUtils;

/**
 ** @BMN 2021
 **
 **/
@Service("userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {
	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private ApplicationRoleRepository applicationRoleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private MapStructMapper mapstructMapper;

	@Override
	public void save(ApplicationUser applicationUser) {
		String kle = CityAppUtils.hash256String(applicationUser.getPassword());
		applicationUser.setPassword(kle);
		Collection<ApplicationRole> applicationRoles = applicationUser.getApplicationRoles();
		ApplicationRole applicationRole = applicationRoles.iterator().next();
		ApplicationRole applicationRoleFromDb = applicationRoleRepository.findByName(applicationRole.getName());
		Collection<ApplicationRole> fetchedapplicationRoles = new ArrayList<>();
		fetchedapplicationRoles.add(applicationRoleFromDb);
		applicationUser.setApplicationRoles(fetchedapplicationRoles);
		LOG.info("Persisting new UserRepository for user {} ", applicationUser.getUsername());
		userRepository.save(applicationUser);
	}

	@Override
	public ApplicationUserDTO login(ApplicationUserDTO applicationUser) {
		/**
		 * We did name it like this to obuscute a little bit in case some hacker
		 * decompile our code :) :)
		 */
		String kle = CityAppUtils.hash256String(applicationUser.getPassword());
		ApplicationUser findByUsernameAndPassword = userRepository
				.findByUsernameAndPassword(applicationUser.getUsername(), kle);
		if (findByUsernameAndPassword == null) {
			return null;
		}
		UserDetails loadUserByUsername = userDetailsService.loadUserByUsername(applicationUser.getUsername());
		if (loadUserByUsername == null) {
			return null;
		}
		final Authentication auth = new UsernamePasswordAuthenticationToken(applicationUser.getUsername(), null,
				loadUserByUsername.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		return mapstructMapper.applicationUserEntityToDTO(findByUsernameAndPassword);

	}

}
