package com.example.amanelshark.model.notifications;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Notifications{

	@SerializedName("data")
	private List<DataItemNotifications> data;

	public void setData(List<DataItemNotifications> data){
		this.data = data;
	}

	public List<DataItemNotifications> getData(){
		return data;
	}

	@Override
 	public String toString(){
		return 
			"Notifications{" + 
			"data = '" + data + '\'' + 
			"}";
		}
}