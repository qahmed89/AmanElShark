package com.example.amanelshark.model.categories;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Categories{

	@SerializedName("data")
	private List<DataItemCategories> data;

	public void setData(List<DataItemCategories> data){
		this.data = data;
	}

	public List<DataItemCategories> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"Categories{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}