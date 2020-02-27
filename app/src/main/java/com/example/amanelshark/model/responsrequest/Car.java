package com.example.amanelshark.model.responsrequest;

import com.google.gson.annotations.SerializedName;

public class Car{

	@SerializedName("client_car_id")
	private int clientCarId;

	@SerializedName("model")
	private String model;

	@SerializedName("type")
	private String type;

	@SerializedName("category")
	private String category;

	@SerializedName("brand")
	private String brand;

	public void setClientCarId(int clientCarId){
		this.clientCarId = clientCarId;
	}

	public int getClientCarId(){
		return clientCarId;
	}

	public void setModel(String model){
		this.model = model;
	}

	public String getModel(){
		return model;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}

	public String getBrand(){
		return brand;
	}

	@Override
 	public String toString(){
		return 
			"Car{" + 
			"client_car_id = '" + clientCarId + '\'' + 
			",model = '" + model + '\'' + 
			",type = '" + type + '\'' + 
			",category = '" + category + '\'' + 
			",brand = '" + brand + '\'' + 
			"}";
		}
}