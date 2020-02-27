package com.example.amanelshark.model.register;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Error{

	@SerializedName("password")
	private List<String> password;

	@SerializedName("c_password")
	private List<String> cPassword;

	@SerializedName("phone")
	private List<String> phone;

	@SerializedName("name")
	private List<String> name;

	@SerializedName("email")
	private List<String> email;

	public void setPassword(List<String> password){
		this.password = password;
	}

	public List<String> getPassword(){
		return password;
	}

	public void setCPassword(List<String> cPassword){
		this.cPassword = cPassword;
	}

	public List<String> getCPassword(){
		return cPassword;
	}

	public void setPhone(List<String> phone){
		this.phone = phone;
	}

	public List<String> getPhone(){
		return phone;
	}

	public void setName(List<String> name){
		this.name = name;
	}

	public List<String> getName(){
		return name;
	}

	public void setEmail(List<String> email){
		this.email = email;
	}

	public List<String> getEmail(){
		return email;
	}

	@Override
 	public String toString(){
		return 
			"Error{" + 
			"password = '" + password + '\'' + 
			",c_password = '" + cPassword + '\'' + 
			",phone = '" + phone + '\'' + 
			",name = '" + name + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		}
}