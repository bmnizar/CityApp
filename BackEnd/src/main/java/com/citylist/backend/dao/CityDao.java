package com.citylist.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citylist.backend.entities.City;

/**
 ** @BMN 2021
 **
 **/
@Repository
public interface CityDao extends JpaRepository<City, Long> {

}
