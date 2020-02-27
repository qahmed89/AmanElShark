package com.example.amanelshark.model.detailsrequest;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("center_name")
	private String centerName;

	@SerializedName("car")
	private Car car;

	@SerializedName("id")
	private int id;

	@SerializedName("package_id")
	private int packageId;

	@SerializedName("reply")
	private Object reply;

	@SerializedName("client_id")
	private int clientId;

	@SerializedName("status")
	private String status;

	@SerializedName("car_center_id")
	private int carCenterId;

	public void setCenterName(String centerName){
		this.centerName = centerName;
	}

	public String getCenterName(){
		return centerName;
	}

	public void setCar(Car car){
		this.car = car;
	}

	public Car getCar(){
		return car;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPackageId(int packageId){
		this.packageId = packageId;
	}

	public int getPackageId(){
		return packageId;
	}

	public void setReply(Object reply){
		this.reply = reply;
	}

	public Object getReply(){
		return reply;
	}

	public void setClientId(int clientId){
		this.clientId = clientId;
	}

	public int getClientId(){
		return clientId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	public void setCarCenterId(int carCenterId){
		this.carCenterId = carCenterId;
	}

	public int getCarCenterId(){
		return carCenterId;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"center_name = '" + centerName + '\'' + 
			",car = '" + car + '\'' + 
			",id = '" + id + '\'' + 
			",package_id = '" + packageId + '\'' + 
			",reply = '" + reply + '\'' + 
			",client_id = '" + clientId + '\'' + 
			",status = '" + status + '\'' + 
			",car_center_id = '" + carCenterId + '\'' + 
			"}";
		}
}