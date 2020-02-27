package com.example.amanelshark.model.years;

import com.google.gson.annotations.SerializedName;

public class DataItemYears {

	@SerializedName("year")
	private int year;

	@SerializedName("id")
	private int id;

	public void setYear(int year){
		this.year = year;
	}

	public int getYear(){
		return year;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return
			String.valueOf(year);
		}
}