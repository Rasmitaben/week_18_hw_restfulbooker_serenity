package com.restful.booker.studentinfo;

import com.restful.booker.Constants.EndPoints;
import com.restful.booker.model.AuthPojo;
import com.restful.booker.model.BookingPojo;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;

public class BookingSteps {
    @Step("Get token with userName : {0}, password : {1}")
    public String getToken(String username, String password) {
        AuthPojo authPojo = AuthPojo.getAuthPojo(username,password);
        Response response = SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(authPojo)
                .when()
                .post("https://restful-booker.herokuapp.com/auth")
                .then().extract().response();
        String token = response.jsonPath().get("token").toString();
        return token;


//        String jsonString = response.asString();
//        token = JsonPath.from(jsonString).get("token");
//        System.out.println("Token is: " + token);
//
    }

    @Step("Getting all booking Ids")
    public ValidatableResponse getAllBookingIds() {
        return SerenityRest.given().log().all()
                .when()
                .get()
                .then();

    }

    @Step("Create new bookind with  firstname : {0}, lastname : {1}, totalprice : {2}, depositepaid : {3}, additionalneeds : {4} bookingdates : {5}, ")

    public Response createNewBooking(String firstname, String lastname, int totalprice, boolean depositpaid, String additionalneeds, HashMap<String, Object> bookingdates) {
//        HashMap<String, Object> booking = new HashMap<String, Object>();
//        booking.put("checkin", "2022-01-01");
//        booking.put("checkout", "2022-02-01");

        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstname, lastname, totalprice, depositpaid, additionalneeds, bookingdates);


        return SerenityRest.given().log().all()
                .contentType(ContentType.JSON)
                .header("Accept", "application/json")
                .body(bookingPojo)
                .when()
                .post();


    }

    @Step("Getting single booking information by booking Id : {0}")
    public ValidatableResponse getSingleBookingById(int bookingId) {
        return SerenityRest.given().log().all()
                //.header("Accept", "application/json")
                .pathParam("bookingId", bookingId)
                .when()
                .get(EndPoints.GET_BOOKING_BY_ID)
                .then();

    }

    @Step("Update Booking information with bookingId : {0}, firstname : {1}, lastname : {2}, totalprice : {3}, depositepaid : {4}, additionalneeds : {5}, bookingdates : {6} ")
    public Response updateBookingById(int bookingId, String token, String firstname, String lastname, int totalprice, boolean depositpaid, String additionalneeds, HashMap<String, Object> bookingdates) {
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstname, lastname, totalprice, depositpaid, additionalneeds, bookingdates);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("Cookie", "token=" + token)
                .pathParam("bookingId", bookingId)
                .body(bookingPojo)
                .when()
                .put(EndPoints.UPDATE_BOOKING_BY_ID);

    }

    @Step("Update with firstname : {0}, booking Id : {1}")
    public Response updatePartialBookingById(int bookingId, String token, String firstname) {
        BookingPojo bookingPojo = BookingPojo.getBookingPojo(firstname);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("cookie", "token=" + token)
                .pathParam("bookingId", bookingId)
                .body("{ \"firstname\": \"Patch firstname\" }")
                .when()
                .patch(EndPoints.PARTIALLY_UPDATE_BOOKING_BY_ID);

    }

    public ValidatableResponse deleteBookingById(int bookingId, String token) {
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("cookie", "token=" + token)
                .pathParam("bookingId", bookingId)
                .when()
                .delete(EndPoints.DELETE_BOOKING_BY_ID)
                .then();


    }

}
