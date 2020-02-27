package com.example.amanelshark.model.listcars;

import com.google.gson.annotations.SerializedName;

public class DataItemCarList {

	@SerializedName("year")
	private int year;

	@SerializedName("chassis_number")
	private String chassisNumber;

	@SerializedName("year_id")
	private int yearId;

	@SerializedName("type")
	private String type;

	@SerializedName("warranty")
	private Warranty warranty;

	@SerializedName("model")
	private String model;

	@SerializedName("id")
	private int id;

	@SerializedName("plate_number")
	private String plateNumber;

	@SerializedName("motor_number")
	private String motorNumber;

	@SerializedName("category")
	private String category;

	@SerializedName("brand")
	private String brand;

	@SerializedName("name_packages")
	private Object namePackages;

	@SerializedName("meter_reading")
	private String meterReading;

	public void setYear(int year){
		this.year = year;
	}

	public int getYear(){
		return year;
	}

	public void setChassisNumber(String chassisNumber){
		this.chassisNumber = chassisNumber;
	}

	public String getChassisNumber(){
		return chassisNumber;
	}

	public void setYearId(int yearId){
		this.yearId = yearId;
	}

	public int getYearId(){
		return yearId;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setWarranty(Warranty warranty){
		this.warranty = warranty;
	}

	public Warranty getWarranty(){
		return warranty;
	}

	public void setModel(String model){
		this.model = model;
	}

	public String getModel(){
		return model;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setPlateNumber(String plateNumber){
		this.plateNumber = plateNumber;
	}

	public String getPlateNumber(){
		return plateNumber;
	}

	public void setMotorNumber(String motorNumber){
		this.motorNumber = motorNumber;
	}

	public String getMotorNumber(){
		return motorNumber;
	}

	public void setCategory(String category){
		this.category = category;
	}

	public String getCategory(){
		return category;
	}

	public void setBrand(String brand){
		this.brand = brand;
	}

	public String getBrand(){
		return brand;
	}

	public void setNamePackages(Object namePackages){
		this.namePackages = namePackages;
	}

	public Object getNamePackages(){
		return namePackages;
	}

	public void setMeterReading(String meterReading){
		this.meterReading = meterReading;
	}

	public String getMeterReading(){
		return meterReading;
	}

	@Override
 	public String toString(){
		return 
			"DataItemCarList{" +
			"year = '" + year + '\'' + 
			",chassis_number = '" + chassisNumber + '\'' + 
			",year_id = '" + yearId + '\'' + 
			",type = '" + type + '\'' + 
			",warranty = '" + warranty + '\'' + 
			",model = '" + model + '\'' + 
			",id = '" + id + '\'' + 
			",plate_number = '" + plateNumber + '\'' + 
			",motor_number = '" + motorNumber + '\'' + 
			",category = '" + category + '\'' + 
			",brand = '" + brand + '\'' + 
			",name_packages = '" + namePackages + '\'' + 
			",meter_reading = '" + meterReading + '\'' + 
			"}";
		}
}