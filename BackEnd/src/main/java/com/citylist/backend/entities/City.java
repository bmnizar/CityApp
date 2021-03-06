package com.citylist.backend.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/**
** @BMN 2021
**
**/
@Entity
public class City {
	@Id
	@SequenceGenerator(name = "city_seq", sequenceName = "city_seq", allocationSize = 100)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_seq")
	private Long id;
	private String name;
	private byte[] image;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
