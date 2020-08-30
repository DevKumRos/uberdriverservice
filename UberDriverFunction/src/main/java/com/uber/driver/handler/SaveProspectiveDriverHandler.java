package com.uber.driver.handler;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import com.google.gson.JsonParser;
import com.uber.driver.model.ProspectiveDriver;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;


import java.io.*;
import java.util.Random;

public class SaveProspectiveDriverHandler implements RequestStreamHandler {

    private JSONParser parser = new JSONParser();
    private static final String DYNAMO_DB_TABLE = System.getenv("TABLE_NAME");

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        JSONObject jsonResponse = new JSONObject();

        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
        DynamoDB dynamoDB = new DynamoDB(client);

        final int id = new Random().nextInt(100);

        try {
            JSONObject event = (JSONObject)parser.parse(reader);
            if(event.get("body") != null) {
                ProspectiveDriver prospectiveDriver = new ProspectiveDriver((String)event.get("body"));

                Item item = new Item().withPrimaryKey("id", id).withMap("document",
                        new ValueMap()
                                .withString("name", prospectiveDriver.getName())
                                .withString("password", prospectiveDriver.getPassword())
                                .withInt("age", prospectiveDriver.getAge())
                                .withMap("registration_certificate", new ValueMap()
                                        .withString("registrationNumber", prospectiveDriver.getRegistrationNumber())
                                        .withString("ownerName", prospectiveDriver.getOwnerName())
                                        .withString("model", prospectiveDriver.getModel())
                                        .withString("dateOfRegistration", prospectiveDriver.getDateOfRegistration())
                                        .withString("registrationValidUpTo", prospectiveDriver.getRegistrationValidUpTo())
                                        .withInt("seatCapacity", prospectiveDriver.getSeatCapacity()) )
                                .withMap("authorization", new ValueMap()
                                        .withString("ownerName", prospectiveDriver.getOwnerName())
                                        .withString("driverName", prospectiveDriver.getDriverName())
                                        .withString("registrationNumber", prospectiveDriver.getRegistrationNumber())
                                        .withString("status", prospectiveDriver.getStatus())  )
                                .withMap( "driving_license", new ValueMap()
                                        .withString("licenseNumber", prospectiveDriver.getLicenseNumber())
                                        .withString("dateOfIssue", prospectiveDriver.getDateOfIssue())
                                        .withString("validTill", prospectiveDriver.getValidTill())
                                        .withString("authToDrive", prospectiveDriver.getAuthToDrive())
                                        .withString("driverName", prospectiveDriver.getDriverName())
                                )
                );
                dynamoDB.getTable(DYNAMO_DB_TABLE).putItem(item);
            }

            JSONObject responseBody = new JSONObject();
            responseBody.put("message", "New Item created & ID is "+id);

            JSONObject headerJson = new JSONObject();
            headerJson.put("x-custom-header", "My Customer header value");

            jsonResponse.put("statusCode", 200);
            jsonResponse.put("headers", headerJson);
            jsonResponse.put("body", responseBody.toString());



        } catch (ParseException e) {
            jsonResponse.put("statusCode", 500);
            jsonResponse.put("body", "Error while storing data to dynamo DB");
        }

        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8" );
        writer.write(jsonResponse.toString());
        writer.close();
    }
}
