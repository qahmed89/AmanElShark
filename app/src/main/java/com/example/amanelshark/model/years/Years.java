package com.example.amanelshark.model.years;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Years{

	@SerializedName("code")
	private int code;

	@SerializedName("data")
	private List<DataItemYears> data;

	@SerializedName("message")
	private String message;

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
	}

	public void setData(List<DataItemYears> data){
		this.data = data;
	}

	public List<DataItemYears> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"Years{" + 
			"code = '" + code + '\'' + 
			",data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}