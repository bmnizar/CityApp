package com.citylist.backend.rest.dto;

/**
 ** @BMN 2021
 **
 **/
public class CityDTO {
	private String name;
	private byte[] image;

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
