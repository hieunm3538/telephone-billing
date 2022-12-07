package com.example.telephonebillingapplication.controller;

import com.example.telephonebillingapplication.IntegrationTest;
import com.example.telephonebillingapplication.dto.request.CallRecordRequest;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class BillingControllerTest extends IntegrationTest {
    @Override
    protected void clearDb() {}

    @Test
    @DisplayName("getBilling : should return 200 OK")
    public void getBilling() {
        given()
                .when()
                .get("/mobile/hieunm3538/billing")
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("getCallRecord : should return 200 OK")
    public void getCallRecordShouldSuccess() {
        CallRecordRequest req = new CallRecordRequest();
        req.setDuration(3000L);
        given()
                .when()
                .body(req)
                .post("/mobile/hieunm3538/call")
                .then()
                .statusCode(HttpStatus.OK.value());
    }
}
