package com.uber.driver.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class ProspectiveDriver {

    private Integer id;
    private String name;
    private String password;
    private Integer age;

    private String licenseNumber;
    private String dateOfIssue;
    private String validTill;
    private String authToDrive;
    private String driverName;
    private String status;

    private String registrationNumber;
    private String ownerName;
    private String model;
    private String dateOfRegistration;
    private String registrationValidUpTo;
    private Integer seatCapacity;




    public ProspectiveDriver(String json ) {
        Gson gson = new Gson();
        ProspectiveDriver request = gson.fromJson(json, ProspectiveDriver.class);
        this.id = request.getId();
        this.age = request.getAge();
        this.name = request.getName();
        this.password = request.getPassword();

        this.licenseNumber = request.getLicenseNumber();
        this.authToDrive = request.getAuthToDrive();
        this.dateOfIssue = request.getDateOfIssue();
        this.driverName = request.getDriverName();
        this.validTill = request.getValidTill();

        this.registrationNumber = request.getRegistrationNumber();
        this.status = request.getStatus();

        this.model = request.getModel();
        this.dateOfRegistration = request.getDateOfRegistration();
        this.registrationValidUpTo = request.getRegistrationValidUpTo();
        this.seatCapacity = request.getSeatCapacity();
    }
}