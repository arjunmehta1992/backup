package com.example.locationalarm.domain;

import java.io.Serializable;

public class ShoppingMall implements Serializable {

	String ID;
	String NAME;
	String ADDRESS;
	Double LATITUDE;
	Double LONGITUDE;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getNAME() {
		return NAME;
	}

	public void setNAME(String nAME) {
		NAME = nAME;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String aDDRESS) {
		ADDRESS = aDDRESS;
	}

	public Double getLATITUDE() {
		return LATITUDE;
	}

	public void setLATITUDE(Double lATITUDE) {
		LATITUDE = lATITUDE;
	}

	public Double getLONGITUDE() {
		return LONGITUDE;
	}

	public void setLONGITUDE(Double lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}

}