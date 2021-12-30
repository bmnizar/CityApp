package com.citylist.backend.services.impl;

import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.input.CharSequenceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citylist.backend.core.CityListBackendException;
import com.citylist.backend.core.CityUrlException;
import com.citylist.backend.dao.CityDao;
import com.citylist.backend.entities.City;
import com.citylist.backend.mapstruct.MapStructMapper;
import com.citylist.backend.rest.dto.CityDTO;
import com.citylist.backend.services.CityService;
import com.citylist.backend.utils.CityAppUtils;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

/**
 ** @BMN 2021
 **
 **/
@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityDao cityDao;
	@Autowired
	private MapStructMapper mapstructMapper;

	@Override
	public void uploadCityFile(byte[] bytes) throws CityListBackendException, CityUrlException {

		Reader targetReader = new CharSequenceReader(new String(bytes));
		CSVReader reader = new CSVReader(targetReader);

		List<String[]> r;
		try {
			r = reader.readAll();
			for (String[] csvValues : r) {
				processCityUrlUploadedFile(csvValues);
			}
		} catch (IOException | CsvException e) {

			throw new CityListBackendException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				throw new CityListBackendException(e);
			}
		}

	}

	private Object processCityUrlUploadedFile(String[] x) throws CityUrlException {

		String firstColumn = x[0];
		try {
			Integer.valueOf(firstColumn);
			String cityName = x[1];
			String cityUrlString = x[2];
			URL cityUrl = null;
			try {
				cityUrl = new URL(cityUrlString);
			} catch (MalformedURLException e) {
				throw new CityUrlException("Can not open url " + cityUrlString);
			}
			byte[] cityImageBytes = CityAppUtils.copyUrlToBytes(cityUrl);
			City city = new City();
			city.setName(cityName);
			city.setImage(cityImageBytes);
			cityDao.save(city);

		} catch (NumberFormatException ex) {
//we don't care for the first row
		}

		return null;
	}
	@Override
	public List<CityDTO> listCities() {
		List<City> findAll = cityDao.findAll();
		return mapstructMapper.convertListCityTOCtiyDTO(findAll);

	}

}
