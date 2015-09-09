package com.example.imageretrivegallary;

public class ImageModel {

	int id;
	int id_path;
	public int getId_path() {
		return id_path;
	}

	public void setId_path(int id_path) {
		this.id_path = id_path;
	}

	public String getName_path() {
		return name_path;
	}

	public void setName_path(String name_path) {
		this.name_path = name_path;
	}

	public String getImage_path() {
		return image_path;
	}

	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}

	String name_path,image_path;
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	String name;
	byte[] image;

}
