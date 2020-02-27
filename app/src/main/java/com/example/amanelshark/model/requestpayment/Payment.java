package com.example.amanelshark.model.requestpayment;

import com.google.gson.annotations.SerializedName;

public class Payment{

	@SerializedName("package_id")
	private int packageId;

	@SerializedName("request_id")
	private int requestId;

	public Payment(int packageId, int requestId) {
		this.packageId = packageId;
		this.requestId = requestId;
	}

	public void setPackageId(int packageId){
		this.packageId = packageId;
	}

	public int getPackageId(){
		return packageId;
	}

	public void setRequestId(int requestId){
		this.requestId = requestId;
	}

	public int getRequestId(){
		return requestId;
	}

	@Override
 	public String toString(){
		return 
			"Payment{" + 
			"package_id = '" + packageId + '\'' + 
			",request_id = '" + requestId + '\'' + 
			"}";
		}
}