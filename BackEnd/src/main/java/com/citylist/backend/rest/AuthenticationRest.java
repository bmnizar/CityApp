package com.citylist.backend.rest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.citylist.backend.entities.ApplicationUser;
import com.citylist.backend.mapstruct.MapStructMapper;
import com.citylist.backend.rest.dto.ApplicationUserDTO;
import com.citylist.backend.services.UserService;
import com.citylist.backend.utils.CityAppUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(CityAppUtils.GENERAL_REST_URL + "/AuthenticationRest")
public class AuthenticationRest {
	private static final String USER_PERSISTED_SUCCESSFULLY = "User Persisted Successfully";
	@Autowired
	private UserService userService;
	@Autowired
	private MapStructMapper mapstructMapper;
	public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@PostMapping(value = "/signup") //
	@ResponseBody
	public String signup(@RequestBody ApplicationUserDTO applicationUser) {
		ApplicationUser applicationUserDTOToEntity = mapstructMapper.applicationUserDTOToEntity(applicationUser);
		userService.save(applicationUserDTOToEntity);
		return JSONObject.quote(USER_PERSISTED_SUCCESSFULLY);

	}

	@PostMapping(value = "/login") //
	@ResponseBody
	public String login(@RequestBody ApplicationUserDTO applicationUser) throws JsonProcessingException {
		ApplicationUserDTO validUser = userService.login(applicationUser);
 
		String toBeReterned = null;
		if (validUser == null) {
			toBeReterned = JSONObject.quote("false");
		} else {
			validUser.setPassword(null);  
			toBeReterned = OBJECT_MAPPER.writeValueAsString(validUser);

		}
		return toBeReterned;

	}

}
