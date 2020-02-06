package com.example.amanelshark.model.center;

import com.google.gson.annotations.SerializedName;

public class DataItemCenters {

	@SerializedName("address")
	private String address;

	@SerializedName("working_days")
	private String workingDays;

	@SerializedName("rate")
	private Float rate;

	@SerializedName("phone")
	private String phone;

	@SerializedName("working_hours")
	private String workingHours;

	@SerializedName("name")
	private String name;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("city_id")
	private int cityId;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setWorkingDays(String workingDays){
		this.workingDays = workingDays;
	}

	public String getWorkingDays(){
		return workingDays;
	}

	public void setRate(Float rate){
		this.rate = rate;
	}

	public Float getRate(){
		return rate;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setWorkingHours(String workingHours){
		this.workingHours = workingHours;
	}

	public String getWorkingHours(){
		return workingHours;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setCityId(int cityId){
		this.cityId = cityId;
	}

	public int getCityId(){
		return cityId;
	}

	@Override
 	public String toString(){
		return 
			"DataItemCenters{" +
			"address = '" + address + '\'' + 
			",working_days = '" + workingDays + '\'' + 
			",rate = '" + rate + '\'' + 
			",phone = '" + phone + '\'' + 
			",working_hours = '" + workingHours + '\'' + 
			",name = '" + name + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			",city_id = '" + cityId + '\'' + 
			"}";
		}
}