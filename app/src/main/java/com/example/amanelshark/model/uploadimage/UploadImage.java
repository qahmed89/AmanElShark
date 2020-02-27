package com.example.amanelshark.model.uploadimage;

import com.google.gson.annotations.SerializedName;

public class UploadImage{

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;

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
			"UploadImage{" + 
			"code = '" + code + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}