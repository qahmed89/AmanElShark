package com.example.amanelshark.model.packages;

import com.google.gson.annotations.SerializedName;

public class DataItemPackages {

	@SerializedName("duration")
	private int duration;

	@SerializedName("km")
	private int km;

	@SerializedName("price")
	private int price;

	@SerializedName("name")
	private String name;

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
			"DataItemPackages{" +
			"duration = '" + duration + '\'' + 
			",km = '" + km + '\'' + 
			",price = '" + price + '\'' + 
			",name = '" + name + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			"}";
		}
}