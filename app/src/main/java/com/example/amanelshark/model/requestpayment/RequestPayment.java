package com.example.amanelshark.model.requestpayment;

import com.google.gson.annotations.SerializedName;

public class RequestPayment{

	@SerializedName("payment")
	private Payment payment;
	public RequestPayment(Payment payment) {
		this.payment = payment;
	}

	public void setPayment(Payment payment){
		this.payment = payment;
	}

	public Payment getPayment(){
		return payment;
	}


	@Override
 	public String toString(){
		return 
			"RequestPayment{" + 
			"payment = '" + payment + '\'' + 
			"}";
		}
}