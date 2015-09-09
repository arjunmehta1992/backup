package com.zaptech.model;

public class DataObject {

	String mTextONe;
	String mTextTwo;

	public DataObject(String textOne, String textTwo) {
		super();

		mTextONe = textOne;
		mTextTwo = textTwo;

	}

	public String getmTextONe() {
		return mTextONe;
	}

	public void setmTextONe(String mTextONe) {
		this.mTextONe = mTextONe;
	}

	public String getmTextTwo() {
		return mTextTwo;
	}

	public void setmTextTwo(String mTextTwo) {
		this.mTextTwo = mTextTwo;
	}

}
