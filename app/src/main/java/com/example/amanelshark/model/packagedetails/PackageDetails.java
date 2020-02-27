package com.example.amanelshark.model.packagedetails;

import com.google.gson.annotations.SerializedName;

public class PackageDetails{

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
			"PackageDetails{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}