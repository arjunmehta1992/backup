package com.example.locationalarm.domain;

import java.io.Serializable;


public class BusRoute implements Serializable {

	double ROUTE;
	String STATION;
	double LATITUDE;
	double LONGITUDE;

	public double getROUTE() {
		return ROUTE;
	}

	public void setROUTE(double rOUTE) {
		ROUTE = rOUTE;
	}

	public String getSTATION() {
		return STATION;
	}

	public void setSTATION(String sTATION) {
		STATION = sTATION;
	}

	public double getLATITUDE() {
		return LATITUDE;
	}

	public void setLATITUDE(double lATITUDE) {
		LATITUDE = lATITUDE;
	}

	public double getLONGITUDE() {
		return LONGITUDE;
	}

	public void setLONGITUDE(double lONGITUDE) {
		LONGITUDE = lONGITUDE;
	}

}
