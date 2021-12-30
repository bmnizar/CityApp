package com.citylist.backend.rest;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.citylist.backend.core.CityListBackendException;
import com.citylist.backend.core.CityUrlException;
import com.citylist.backend.rest.dto.CityDTO;
import com.citylist.backend.services.CityService;
import com.citylist.backend.utils.CityAppUtils;

/**
 ** @BMN 2021
 **
 **/
@Controller
@RequestMapping(CityAppUtils.GENERAL_REST_URL + "/CityListOperations")
public class CityListOperations {
	@Autowired
	private CityService cityService;
	@GetMapping( value = "/listCities")
	@ResponseBody
	public List<CityDTO> listCities() throws IOException {
		return cityService.listCities();
	}
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, value = "/uploadCityFile")
	@ResponseBody
	public String uploadCityFile(HttpServletRequest request) throws IOException {
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartHttpServletRequest.getFileMap();
		Set<Entry<String, MultipartFile>> entrySet = fileMap.entrySet();
		Iterator<Entry<String, MultipartFile>> iterator = entrySet.iterator();
		Entry<String, MultipartFile> next = iterator.next();
		MultipartFile value = next.getValue();
		byte[] bytes = value.getBytes();
		String toBeReturned = "OK";
		try {
			cityService.uploadCityFile(bytes);
		} catch (CityListBackendException | CityUrlException e) {
			toBeReturned = e.getMessage();
		}

		return JSONObject.quote(toBeReturned);
	}

}
