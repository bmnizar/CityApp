package com.citylist.backend.services.impl;

import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.input.CharSequenceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.citylist.backend.core.CityListBackendException;
import com.citylist.backend.core.CityUrlException;
import com.citylist.backend.dao.CityDao;
import com.citylist.backend.dao.CitySpecification;
import com.citylist.backend.dao.SpecSearchCriteria;
import com.citylist.backend.entities.City;
import com.citylist.backend.rest.CityObject;
import com.citylist.backend.rest.dto.CityDTO;
import com.citylist.backend.services.CityService;
import com.citylist.backend.shared.SearchCriteriaUI;
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

	 
	@Value("${byPassUnresolvedCity}")
	private boolean byPassUnresolvedCity;

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

	private void processCityUrlUploadedFile(String[] x) throws CityUrlException {

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
			byte[] cityImageBytes = CityAppUtils.copyUrlToBytes(byPassUnresolvedCity, cityUrl);
			if (cityImageBytes == null)
				return;
			City city = new City();
			city.setName(cityName);
			city.setImage(cityImageBytes);
			cityDao.save(city);

		} catch (NumberFormatException ex) {
//we don't care for the first row
		}

	}

	@Override
	public byte[] getCityPictureByName(String cityName) {
		City city = cityDao.findByName(cityName);
		return city.getImage();
	}

	@Override
	public List<CityDTO> listCitiesPerPage(String from, String to) {
		PageRequest pageable = PageRequest.of(Integer.parseInt(from), Integer.parseInt(to), Sort.by("name"));
	 
		Page<City> findAll = cityDao.findAll(pageable);
		Page<CityDTO> entities = findAll.map(CityAppUtils::fromEntity);
		List<CityDTO> content = entities.getContent();
		if (!content.isEmpty()) {
			Long count = cityDao.count();
			content.get(0).setTotalRows(count.intValue());
		}
		return content;

	}

	@Override
	public List<CityDTO> listCitiesPerPageAndFilters(String from, String to, SearchCriteriaUI searchCriteriaUI) {

		PageRequest pageable = PageRequest.of(Integer.parseInt(from), Integer.parseInt(to), Sort.by("name"));
		Page<City> findAll;
		if (searchCriteriaUI != null) {      
			CitySpecification spec = new CitySpecification(new SpecSearchCriteria(searchCriteriaUI));
			findAll = cityDao.findAll(spec, pageable);
		} else {
			findAll = cityDao.findAll(pageable);    
		}

		Page<CityDTO> entities = findAll.map(CityAppUtils::fromEntity);
		List<CityDTO> content = entities.getContent();
		if (!content.isEmpty()) {
			Long count = cityDao.count();
			content.get(0).setTotalRows(count.intValue());
		}
		return content;

	}

	@Override
	public void updateCityName(CityObject cityObject) {

		cityDao.updateCityName(cityObject.getCityNewName(), Long.valueOf(cityObject.getCityId()));

	}

}
