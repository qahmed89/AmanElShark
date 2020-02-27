package com.example.amanelshark.model.login;

import com.google.gson.annotations.SerializedName;

public class Login{

	@SerializedName("code")
	private int code;

	@SerializedName("message")
	private String message;

	@SerializedName("client_account")
	private ClientAccount clientAccount;

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

	public void setClientAccount(ClientAccount clientAccount){
		this.clientAccount = clientAccount;
	}

	public ClientAccount getClientAccount(){
		return clientAccount;
	}

	@Override
 	public String toString(){
		return 
			"Login{" + 
			"code = '" + code + '\'' + 
			",message = '" + message + '\'' + 
			",client_account = '" + clientAccount + '\'' + 
			"}";
		}
}