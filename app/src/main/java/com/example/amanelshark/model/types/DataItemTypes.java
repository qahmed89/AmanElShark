package com.example.amanelshark.model.types;

import com.google.gson.annotations.SerializedName;

public class DataItemTypes {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private String type;

	@SerializedName("brand_model_id")
	private int brandModelId;

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setBrandModelId(int brandModelId){
		this.brandModelId = brandModelId;
	}

	public int getBrandModelId(){
		return brandModelId;
	}

	@Override
 	public String toString(){
		return 
			name ;
		}
}