package com.example.amanelshark.model.profile;

import com.google.gson.annotations.SerializedName;

public class Profile{

	@SerializedName("address")
	private String address;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("name")
	private String name;

	@SerializedName("mobile")
	private String mobile;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("agent_branch_id")
	private Object agentBranchId;

	@SerializedName("email")
	private String email;

	@SerializedName("city_id")
	private int cityId;

	public void setAddress(String address){
		this.address = address;
	}

	public String getAddress(){
		return address;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAgentBranchId(Object agentBranchId){
		this.agentBranchId = agentBranchId;
	}

	public Object getAgentBranchId(){
		return agentBranchId;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setCityId(int cityId){
		this.cityId = cityId;
	}

	public int getCityId(){
		return cityId;
	}

	@Override
 	public String toString(){
		return 
			"Profile{" + 
			"address = '" + address + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",name = '" + name + '\'' + 
			",mobile = '" + mobile + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",agent_branch_id = '" + agentBranchId + '\'' + 
			",email = '" + email + '\'' + 
			",city_id = '" + cityId + '\'' + 
			"}";
		}
}