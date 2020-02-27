package com.example.amanelshark.model.types;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Types{

	@SerializedName("data")
	private List<DataItemTypes> data;

	public void setData(List<DataItemTypes> data){
		this.data = data;
	}

	public List<DataItemTypes> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"Types{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}