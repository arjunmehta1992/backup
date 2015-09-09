package com.example.gsondemo;

import com.google.gson.annotations.SerializedName;

public class CountryList {

	@SerializedName("code")
	String code;
	@SerializedName("name")
	String name;
	@SerializedName("continent")
	String continent;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

}
