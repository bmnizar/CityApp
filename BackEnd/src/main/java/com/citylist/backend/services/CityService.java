package com.citylist.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.citylist.backend.core.CityListBackendException;
import com.citylist.backend.core.CityUrlException;
import com.citylist.backend.rest.dto.CityDTO;

/**
 ** @BMN 2021
 **
 **/

public interface CityService {
	public void uploadCityFile(byte[] bytes) throws CityListBackendException, CityUrlException;

	List<CityDTO> listCities();
}
