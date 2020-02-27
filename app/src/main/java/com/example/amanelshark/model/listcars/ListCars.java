package com.example.amanelshark.model.listcars;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ListCars{

	@SerializedName("data")
	private List<DataItemCarList> data;

	public void setData(List<DataItemCarList> data){
		this.data = data;
	}

	public List<DataItemCarList> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"ListCars{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}