package com.example;

import java.time.LocalDateTime;

public class ServiceType {
    private int id;
    private String serviceName;
    private Float cost;
    private LocalDateTime serviceTime;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public Float getCost() {
        return cost;
    }
    public void setCost(Float cost) {
        this.cost = cost;
    }
    public LocalDateTime getServiceTime() {
        return serviceTime;
    }
    public void setServiceTime(LocalDateTime serviceTime) {
        this.serviceTime = serviceTime;
    }
}
