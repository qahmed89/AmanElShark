package com.example.amanelshark.model.cardetails;

import com.google.gson.annotations.SerializedName;

public class CarDetails{

	@SerializedName("data")
	private Data data;

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"CarDetails{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}