package com.citylist.backend.core;

import java.io.IOException;

/**
 ** @BMN 2021
 **
 **/
public class CityListBackendException extends Exception {

	public CityListBackendException(String message) {
		super(message);
	}

	public CityListBackendException(Exception e) {
		super(e);
	}

}
