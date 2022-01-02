package com.citylist.backend.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.citylist.backend.entities.City;

/**
 ** @BMN 2021
 **/
@Repository
public interface CityDao extends PagingAndSortingRepository<City, Long>, JpaSpecificationExecutor<City> {

	City findByName(String cityName);

	@Modifying
	@Transactional
	@Query("update City c set c.name= :cityNewName WHERE c.id = :cityId")
	public void updateCityName(@Param("cityNewName") String cityNewName, @Param("cityId") Long cityId);

}
