package com.example.amanelshark.model.subbrands;

import com.google.gson.annotations.SerializedName;

public class SubDataItem{

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("brand_id")
	private int brandId;

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

	public void setBrandId(int brandId){
		this.brandId = brandId;
	}

	public int getBrandId(){
		return brandId;
	}

	@Override
 	public String toString(){
		return name;
		}
}