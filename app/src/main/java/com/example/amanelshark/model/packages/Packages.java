package com.example.amanelshark.model.packages;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Packages{

	@SerializedName("data")
	private List<DataItemPackages> data;

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("links")
	private Links links;

	public void setData(List<DataItemPackages> data){
		this.data = data;
	}

	public List<DataItemPackages> getData(){
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
			"Packages{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			",links = '" + links + '\'' + 
			"}";
		}
}