package com.example.amanelshark.model.warranty;

import com.google.gson.annotations.SerializedName;

public class Warranty {

    @SerializedName("start_date")
    private String startDate;
    @SerializedName("package_id")
    private int packageId;
    @SerializedName("client_car_id")
    private int clientCarId;

    public Warranty(String startDate, int packageId, int clientCarId) {
        this.startDate = startDate;
        this.packageId = packageId;
        this.clientCarId = clientCarId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public int getClientCarId() {
        return clientCarId;
    }

    public void setClientCarId(int clientCarId) {
        this.clientCarId = clientCarId;
    }


    @Override
    public String toString() {
        return
                "Warranty{" +
                        "package_id = '" + packageId + '\'' +
                        ",start_date = '" + startDate + '\'' +
                        ",client_car_id = '" + clientCarId + '\'' +
                        "}";
    }
}