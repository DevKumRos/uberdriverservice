package com.uber.driver.model;


import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Authorization {

    private String ownerName;
    private String driverName;
    private String registrationNumber;
    private String status;

    public Authorization(String json) {
        Gson gson = new Gson();
        Authorization request = gson.fromJson(json, Authorization.class);
        this.ownerName = request.getOwnerName();
        this.driverName = request.getDriverName();
        this.registrationNumber = request.getRegistrationNumber();
        this.status = request.getStatus();


    }
}
