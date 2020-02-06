package com.example.amanelshark.model.center;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Centers{

	@SerializedName("data")
	private List<DataItemCenters> data;

	@SerializedName("meta")
	private Meta meta;

	@SerializedName("links")
	private Links links;

	public void setData(List<DataItemCenters> data){
		this.data = data;
	}

	public List<DataItemCenters> getData(){
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
			"Centers{" + 
			"data = '" + data + '\'' + 
			",meta = '" + meta + '\'' + 
			",links = '" + links + '\'' + 
			"}";
		}
}