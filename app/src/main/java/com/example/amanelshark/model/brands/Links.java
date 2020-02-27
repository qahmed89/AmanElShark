package com.example.amanelshark.model.brands;

import com.google.gson.annotations.SerializedName;

public class Links{

	@SerializedName("next")
	private Object next;

	@SerializedName("last")
	private String last;

	@SerializedName("prev")
	private Object prev;

	@SerializedName("first")
	private String first;

	public void setNext(Object next){
		this.next = next;
	}

	public Object getNext(){
		return next;
	}

	public void setLast(String last){
		this.last = last;
	}

	public String getLast(){
		return last;
	}

	public void setPrev(Object prev){
		this.prev = prev;
	}

	public Object getPrev(){
		return prev;
	}

	public void setFirst(String first){
		this.first = first;
	}

	public String getFirst(){
		return first;
	}

	@Override
 	public String toString(){
		return 
			"Links{" + 
			"next = '" + next + '\'' + 
			",last = '" + last + '\'' + 
			",prev = '" + prev + '\'' + 
			",first = '" + first + '\'' + 
			"}";
		}
}