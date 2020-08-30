package com.uber.driver.model;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class RegistrationCertificate {

    private String registrationNumber;
    private String ownerName;
    private String model;
    private String dateOfRegistration;
    private String registrationValidUpTo;
    private Integer seatCapacity;

    public RegistrationCertificate(String json) {
        Gson gson = new Gson();
        RegistrationCertificate request = gson.fromJson(json, RegistrationCertificate.class);
        this.registrationNumber = request.getRegistrationNumber();
        this.ownerName = request.getOwnerName();
        this.model = request.getModel();
        this.dateOfRegistration = request.getDateOfRegistration();
        this.registrationValidUpTo = request.getRegistrationValidUpTo();
        this.seatCapacity = request.getSeatCapacity();
    }

}
