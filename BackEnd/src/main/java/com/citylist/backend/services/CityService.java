package com.citylist.backend.services;

import java.util.List;

import com.citylist.backend.core.CityListBackendException;
import com.citylist.backend.core.CityUrlException;
import com.citylist.backend.rest.CityObject;
import com.citylist.backend.rest.dto.CityDTO;
import com.citylist.backend.shared.SearchCriteriaUI;

/**
 ** @BMN 2021
 **
 **/

public interface CityService {
	public void uploadCityFile(byte[] bytes) throws CityListBackendException, CityUrlException;
 
	List<CityDTO> listCitiesPerPage(String from,String to);
	public byte[] getCityPictureByName(String cityName);

	List<CityDTO> listCitiesPerPageAndFilters(String from, String to, SearchCriteriaUI searchCriteriaUI);

	public void updateCityName(CityObject cityObject);
}
