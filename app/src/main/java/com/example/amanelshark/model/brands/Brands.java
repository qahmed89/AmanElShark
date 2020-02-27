package com.example.amanelshark.model.brands;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Brands{

	@SerializedName("data")
	private List<DataItemBrands> data;

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("links")
	private Links links;

	public void setData(List<DataItemBrands> data){
		this.data = data;
	}

	public List<DataItemBrands> getData(){
		return data;
	}

	public void setMeta(Meta meta){
		this.meta = meta;
	}

	public Meta getMeta(){
		return meta;
	}

	public void setLinks(Links links){
		this.links = links;
	}

	public Links getLinks(){
		return links;
	}

	@Override
 	public String toString(){
		return 
			"Brands{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			",links = '" + links + '\'' + 
			"}";
		}
}