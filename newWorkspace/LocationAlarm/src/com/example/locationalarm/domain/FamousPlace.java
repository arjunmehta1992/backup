package com.example.locationalarm.domain;

import java.io.Serializable;

public class FamousPlace implements Serializable {

	String ID;
	String NAME;
	String ADDRESS;
	Double LATITUDE;
	Double LONGITUDE;
	String Description;

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

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
