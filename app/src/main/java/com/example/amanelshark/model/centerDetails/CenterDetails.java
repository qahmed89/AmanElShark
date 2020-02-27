package com.example.amanelshark.model.centerDetails;

import com.google.gson.annotations.SerializedName;

public class CenterDetails{

	@SerializedName("data")
	private DataCenter data;

	public void setDataCenter(DataCenter data){
		this.data = data;
	}

	public DataCenter getDataCenter(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"CenterDetails{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}