package com.example.amanelshark.model.listcars;

import com.google.gson.annotations.SerializedName;

public class Warranty{

	@SerializedName("end_date")
	private String endDate;

	@SerializedName("warranty_period")
	private String warrantyPeriod;

	@SerializedName("agreement_id")
	private int agreementId;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("client_car_id")
	private int clientCarId;

	@SerializedName("id")
	private int id;

	@SerializedName("agent_branch_id")
	private int agentBranchId;

	@SerializedName("package_id")
	private int packageId;

	@SerializedName("packages")
	private Packages packages;

	@SerializedName("km_meter")
	private int kmMeter;

	@SerializedName("start_date")
	private String startDate;

	public void setEndDate(String endDate){
		this.endDate = endDate;
	}

	public String getEndDate(){
		return endDate;
	}

	public void setWarrantyPeriod(String warrantyPeriod){
		this.warrantyPeriod = warrantyPeriod;
	}

	public String getWarrantyPeriod(){
		return warrantyPeriod;
	}

	public void setAgreementId(int agreementId){
		this.agreementId = agreementId;
	}

	public int getAgreementId(){
		return agreementId;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setClientCarId(int clientCarId){
		this.clientCarId = clientCarId;
	}

	public int getClientCarId(){
		return clientCarId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setAgentBranchId(int agentBranchId){
		this.agentBranchId = agentBranchId;
	}

	public int getAgentBranchId(){
		return agentBranchId;
	}

	public void setPackageId(int packageId){
		this.packageId = packageId;
	}

	public int getPackageId(){
		return packageId;
	}

	public void setPackages(Packages packages){
		this.packages = packages;
	}

	public Packages getPackages(){
		return packages;
	}

	public void setKmMeter(int kmMeter){
		this.kmMeter = kmMeter;
	}

	public int getKmMeter(){
		return kmMeter;
	}

	public void setStartDate(String startDate){
		this.startDate = startDate;
	}

	public String getStartDate(){
		return startDate;
	}

	@Override
 	public String toString(){
		return 
			"Warranty{" + 
			"end_date = '" + endDate + '\'' + 
			",warranty_period = '" + warrantyPeriod + '\'' + 
			",agreement_id = '" + agreementId + '\'' + 
			",updated_at = '" + updatedAt + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",client_car_id = '" + clientCarId + '\'' + 
			",id = '" + id + '\'' + 
			",agent_branch_id = '" + agentBranchId + '\'' + 
			",package_id = '" + packageId + '\'' + 
			",packages = '" + packages + '\'' + 
			",km_meter = '" + kmMeter + '\'' + 
			",start_date = '" + startDate + '\'' + 
			"}";
		}
}