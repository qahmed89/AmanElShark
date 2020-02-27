package com.example.amanelshark.model.cardetails;

import com.google.gson.annotations.SerializedName;

public class Packages{

	@SerializedName("duration")
	private int duration;

	@SerializedName("km")
	private int km;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("price")
	private int price;

	@SerializedName("name")
	private String name;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("type")
	private String type;

	public void setDuration(int duration){
		this.duration = duration;
	}

	public int getDuration(){
		return duration;
	}

	public void setKm(int km){
		this.km = km;
	}

	public int getKm(){
		return km;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public int getPrice(){
		return price;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
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

	@Override
 	public String toString(){
		return 
			"Packages{" + 
			"duration = '" + duration + '\'' + 
			",km = '" + km + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",price = '" + price + '\'' + 
			",name = '" + name + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}