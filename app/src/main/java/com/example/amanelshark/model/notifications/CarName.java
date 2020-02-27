package com.example.amanelshark.model.notifications;

import com.google.gson.annotations.SerializedName;

public class CarName{

	@SerializedName("model_name")
	private String modelName;

	@SerializedName("brand_name")
	private String brandName;

	public void setModelName(String modelName){
		this.modelName = modelName;
	}

	public String getModelName(){
		return modelName;
	}

	public void setBrandName(String brandName){
		this.brandName = brandName;
	}

	public String getBrandName(){
		return brandName;
	}

	@Override
 	public String toString(){
		return 
			"CarName{" + 
			"model_name = '" + modelName + '\'' + 
			",brand_name = '" + brandName + '\'' + 
			"}";
		}
}