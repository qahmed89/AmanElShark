package com.example.amanelshark.model.notifications;

import com.google.gson.annotations.SerializedName;

public class DataItemNotifications {

	@SerializedName("car_name")
	private CarName carName;

	@SerializedName("package")
	private JsonMemberPackage jsonMemberPackage;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("time")
	private String time;

	@SerializedName("title")
	private String title;

	@SerializedName("request_id")
	private int requestId;

	@SerializedName("status")
	private String status;

	public void setCarName(CarName carName){
		this.carName = carName;
	}

	public CarName getCarName(){
		return carName;
	}

	public void setJsonMemberPackage(JsonMemberPackage jsonMemberPackage){
		this.jsonMemberPackage = jsonMemberPackage;
	}

	public JsonMemberPackage getJsonMemberPackage(){
		return jsonMemberPackage;
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

	public void setTime(String time){
		this.time = time;
	}

	public String getTime(){
		return time;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setRequestId(int requestId){
		this.requestId = requestId;
	}

	public int getRequestId(){
		return requestId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"DataItemNotifications{" +
			"car_name = '" + carName + '\'' + 
			",package = '" + jsonMemberPackage + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			",time = '" + time + '\'' + 
			",title = '" + title + '\'' + 
			",request_id = '" + requestId + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}