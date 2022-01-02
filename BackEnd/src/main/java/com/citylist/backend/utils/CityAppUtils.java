package com.citylist.backend.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.io.IOUtils;

import com.citylist.backend.core.CityBackendRuntimeException;
import com.citylist.backend.core.CityListConfig;
import com.citylist.backend.core.CityUrlException;
import com.citylist.backend.entities.City;
import com.citylist.backend.rest.dto.CityDTO;

/**
 ** @BMN 2021
 **
 **/
public class CityAppUtils {
	public static final String GENERAL_REST_URL = "CityBackend";
	public static MessageDigest SHA256_HASH;
	static {
		try {
			SHA256_HASH = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
	}

	public static CityDTO fromEntity(City c) {
		return CityListConfig.getMapStructMapper().convertCityTOCtiyDTO(c);

	}

	public static String hash256String(String word) {
		try {
			byte[] encodedhash = SHA256_HASH.digest(word.getBytes(StandardCharsets.UTF_8));
			return bytesToHex(encodedhash);
		} catch (Exception e) {

			throw new CityBackendRuntimeException("A critial exception is done when haching using SHA 256");
		}

	}

	private static String bytesToHex(byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}

	public static byte[] copyUrlToBytes(boolean byPassUnresolvedCity, URL url) throws CityUrlException {
		try {
			InputStream openStream = url.openStream();
			return IOUtils.toByteArray(openStream);
		} catch (IOException e) {

		}

		if (byPassUnresolvedCity) {
			return null;
		} else {
			throw new CityUrlException("Can not open url " + url);
		}

	}
}
