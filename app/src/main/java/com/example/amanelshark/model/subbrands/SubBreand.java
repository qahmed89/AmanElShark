package com.example.amanelshark.model.subbrands;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class SubBreand{

	@SerializedName("data")
	private List<SubDataItem> data;

	public void setData(List<SubDataItem> data){
		this.data = data;
	}

	public List<SubDataItem> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"SubBreand{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}