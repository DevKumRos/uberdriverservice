package com.uber.driver.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class DrivingLicense {

    private String licenseNumber;
    private Date dateOfIssue;
    private Date validTill;
    private String authToDrive;
    private String driverName;

    public DrivingLicense(String json) {
        Gson gson = new Gson();
        DrivingLicense request = gson.fromJson(json, DrivingLicense.class);
        this.licenseNumber = request.getLicenseNumber();
        this.authToDrive = request.getAuthToDrive();
        this.dateOfIssue = request.getDateOfIssue();
        this.driverName = request.getDriverName();
        this.validTill = request.getValidTill();
    }
}
