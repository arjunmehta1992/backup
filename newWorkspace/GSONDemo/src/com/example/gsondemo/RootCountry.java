package com.example.gsondemo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class RootCountry {

	@SerializedName("success")
	String success;
	
	

	List<CountryList> list_cntryList;

	public List<CountryList> getList_cntryList() {
		return list_cntryList;
	}

	public void setList_cntryList(List<CountryList> list_cntryList) {
		this.list_cntryList = list_cntryList;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}


	

}
