package com.citylist.backend.mapstruct;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.citylist.backend.entities.ApplicationUser;
import com.citylist.backend.entities.City;
import com.citylist.backend.rest.dto.ApplicationUserDTO;
import com.citylist.backend.rest.dto.CityDTO;

/**
 ** @BMN 2021
 **
 **/

@Mapper(componentModel = "spring")
public interface MapStructMapper {

	ApplicationUserDTO applicationUserEntityToDTO(ApplicationUser applicationUser);

	@InheritInverseConfiguration
	ApplicationUser applicationUserDTOToEntity(ApplicationUserDTO applicationUser);
	CityDTO convertCityTOCtiyDTO(City city);
	List<CityDTO> convertListCityTOCtiyDTO(List<City> city);
}
