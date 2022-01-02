package com.citylist.backend.rest.dto;

import com.citylist.backend.entities.City;

/**
 ** @BMN 2021
 **
 **/
public class CityDTO {
	private String name;
	private byte[] image;
	private Integer id ;
	private Integer totalRows ;
	
	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
