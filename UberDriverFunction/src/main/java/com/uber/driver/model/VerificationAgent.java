package com.uber.driver.model;

import com.google.gson.Gson;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class VerificationAgent {
    private String comment;

    public VerificationAgent(String json) {
        Gson gson = new Gson();
        VerificationAgent request = gson.fromJson(json, VerificationAgent.class);
        this.comment = request.getComment();
    }

}
