package com.example.amanelshark.model.responsrequest;

import com.google.gson.annotations.SerializedName;

public class DataItemResponseRequest {


    @SerializedName("id")
    private int id;
    @SerializedName("car")
    private Car car;

    @SerializedName("package_id")
    private int packageId;

    @SerializedName("reply")
    private Object reply;

    @SerializedName("client_id")
    private int clientId;

    @SerializedName("status")
    private String status;

    @SerializedName("car_center_id")
    private int carCenterId;


    @SerializedName("center_name")
    private String centerName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public Object getReply() {
        return reply;
    }

    public void setReply(Object reply) {
        this.reply = reply;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCarCenterId() {
        return carCenterId;
    }

    public void setCarCenterId(int carCenterId) {
        this.carCenterId = carCenterId;
    }

    @Override
    public String toString() {
        return
                "DataItemResponseRequest{" +
                        "car = '" + car + '\'' +
                        ",package_id = '" + packageId + '\'' +
                        ",reply = '" + reply + '\'' +
                        ",client_id = '" + clientId + '\'' +
                        ",status = '" + status + '\'' +
                        ",car_center_id = '" + carCenterId + '\'' +
                        ",center_name = '" + centerName + '\'' +

                        "}";
    }
}