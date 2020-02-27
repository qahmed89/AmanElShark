package com.example.amanelshark.model.warranty;

import com.google.gson.annotations.SerializedName;

public class Warrenty{

	@SerializedName("warranty")
	private Warranty warranty;

	public Warrenty(Warranty warranty) {
		this.warranty = warranty;
	}

	public void setWarranty(Warranty warranty){
		this.warranty = warranty;
	}

	public Warranty getWarranty(){
		return warranty;
	}

	@Override
 	public String toString(){
		return 
			"Warrenty{" + 
			"warranty = '" + warranty + '\'' + 
			"}";
		}
}