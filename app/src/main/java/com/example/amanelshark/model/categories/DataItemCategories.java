package com.example.amanelshark.model.categories;

import com.google.gson.annotations.SerializedName;

public class DataItemCategories {

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("model_type_id")
	private int modelTypeId;

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

	public void setModelTypeId(int modelTypeId){
		this.modelTypeId = modelTypeId;
	}

	public int getModelTypeId(){
		return modelTypeId;
	}

	@Override
 	public String toString(){
		return 
			name;
		}
}