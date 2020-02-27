package com.example.amanelshark.model.responsrequest;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsRequest{

	@SerializedName("data")
	private List<DataItemResponseRequest> data;



	public void setData(List<DataItemResponseRequest> data){
		this.data = data;
	}

	public List<DataItemResponseRequest> getData(){
		return data;
	}



	@Override
 	public String toString(){
		return 
			"ResponsRequest{" + 
			"data = '" + data + '\'' + 

			"}";
		}
}