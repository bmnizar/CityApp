package com.citylist.backend.rest;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.SSLEngineResult.Status;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.citylist.backend.core.CityListBackendException;
import com.citylist.backend.core.CityUrlException;
import com.citylist.backend.rest.dto.CityDTO;
import com.citylist.backend.services.CityService;
import com.citylist.backend.shared.SearchCriteriaUI;
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

	@GetMapping(value = "/listCitiesPerPage")
	@ResponseBody
	public List<CityDTO> listCitiesPerPage(String from, String to) {
		return cityService.listCitiesPerPage(from, to);
	}

	@GetMapping(value = "/listCitiesPerPageAndFilters")
	@ResponseBody
	public List<CityDTO> listCitiesPerPageAndFilters(String from, String to, String key, String operation,
			String value) {
		SearchCriteriaUI searchCriteriaUI = null;
		if (key != null) {
			searchCriteriaUI = new SearchCriteriaUI();
			searchCriteriaUI.setKey(key);
			searchCriteriaUI.setSearchOperationString(operation);
			searchCriteriaUI.setValue(value);
		}

		return cityService.listCitiesPerPageAndFilters(from, to, searchCriteriaUI);
	}

	@GetMapping(value = "/getCityPictureByName")
	public ResponseEntity<byte[]> getCityPictureByName(String cityName) {
		byte[] cityBlob = cityService.getCityPictureByName(cityName);

		HttpHeaders headers = new HttpHeaders();
		return new ResponseEntity<>(cityBlob, headers, HttpStatus.OK);

	}

	@PostMapping(value = "/updateCityName")
	@ResponseBody
	public void updateCityName(@RequestBody CityObject cityObject) {
		cityService.updateCityName(cityObject);
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
		String toBeReturned = Status.OK.toString();
		try {
			cityService.uploadCityFile(bytes);
		} catch (CityListBackendException | CityUrlException e) {
			toBeReturned = e.getMessage();
		}

		return JSONObject.quote(toBeReturned);
	}

}
