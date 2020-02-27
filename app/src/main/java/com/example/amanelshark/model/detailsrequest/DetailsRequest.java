package com.example.amanelshark.model.detailsrequest;

import com.google.gson.annotations.SerializedName;

public class DetailsRequest{

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
			"DetailsRequest{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}