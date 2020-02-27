package com.example.amanelshark.model.addcar;

import com.google.gson.annotations.SerializedName;

public class AddCars{

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;
	@SerializedName("error")
	private String error;

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setCode(int code){
		this.code = code;
	}

	public int getCode(){
		return code;
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
			"AddCars{" + 
			"code = '" + code + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}