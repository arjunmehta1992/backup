package com.zaptech.taskapp.model_class;

public class MainModel {

	String datetime;

	ModelLocation obj_ModelLocation;
	ModelBattery obj_ModelBattery;
	ModelAlarm obj_ModelAlarm;

	public ModelLocation getObj_ModelLocation() {
		return obj_ModelLocation;
	}

	public void setObj_ModelLocation(ModelLocation obj_ModelLocation) {
		this.obj_ModelLocation = obj_ModelLocation;
	}

	public ModelBattery getObj_ModelBattery() {
		return obj_ModelBattery;
	}

	public void setObj_ModelBattery(ModelBattery obj_ModelBattery) {
		this.obj_ModelBattery = obj_ModelBattery;
	}

	public ModelAlarm getObj_ModelAlarm() {
		return obj_ModelAlarm;
	}

	public void setObj_ModelAlarm(ModelAlarm obj_ModelAlarm) {
		this.obj_ModelAlarm = obj_ModelAlarm;
	}

	public String getDatetime() {
		return datetime;
	}

	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}

}
