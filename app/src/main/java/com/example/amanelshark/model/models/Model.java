package com.example.amanelshark.model.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Model{

	@SerializedName("data")
	private List<DataItemModels> data;

	public void setData(List<DataItemModels> data){
		this.data = data;
	}

	public List<DataItemModels> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"Model{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}