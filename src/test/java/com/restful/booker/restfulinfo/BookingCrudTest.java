package com.restful.booker.restfulinfo;

import com.restful.booker.studentinfo.BookingSteps;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
@RunWith(SerenityRunner.class)
public class BookingCrudTest extends TestBase {
  static String username = "admin";
  static String password = "password123";
  static String token;
  static String firstname = "Zara" + TestUtils.getRandomValue();
    static String lastname = "Lee" + TestUtils.getRandomValue();;
    static int totalprice = 100;
    static boolean depositpaid = true;
    static HashMap<String, Object> bookingdates;
    static String additionalneeds = "Vegetarian";

    static  int bookingId;

    @Steps
    BookingSteps bookingSteps;

    @Title("This method will generate the token")
    @Test
    public void test001(){
       token = bookingSteps.getToken(username,password);
        System.out.println(token);
    }
    @Title("This method will get all booking Ids")
    @Test

    public void test002(){
       ValidatableResponse response = bookingSteps.getAllBookingIds();
              response.log().all().statusCode(200);
    }
    @Title("This method will add new booking information to the application and verify the booking has been added")
    @Test

    public void test003(){
      HashMap<String,Object> bookingDates = new HashMap<String,Object>();
      bookingDates.put("checkin", "2022-01-01");
      bookingDates.put("checkout", "2022-02-01");
      Response response =  bookingSteps.createNewBooking(firstname,lastname,totalprice,depositpaid,additionalneeds,bookingdates);
      bookingId = response.then().log().all().extract().path("bookingid");
      System.out.println(bookingId);
    }
    @Title("This method will get single booking Id")
    @Test
    public void test004(){
      ValidatableResponse response = bookingSteps.getSingleBookingById(bookingId);
      response.log().all().statusCode(200);
    }
    @Title("This method will update booking information")
    @Test
    public void test005(){
      HashMap<String,Object> bookingDates = new HashMap<String,Object>();
      bookingDates.put("checkin", "2022-01-01");
      bookingDates.put("checkout", "2022-02-01");
      Response response = bookingSteps.updateBookingById(bookingId,token,firstname,lastname,totalprice,depositpaid,additionalneeds,bookingDates);
      response.then().log().all().statusCode(200);
    }
    @Title("This method will update partial booking information")
    @Test
    public void test006(){
//      HashMap<String,Object> bookingDates = new HashMap<String,Object>();
//      bookingDates.put("checkin", "2022-01-01");
//      bookingDates.put("checkout", "2022-02-01");
      firstname = firstname + "_updated";
     Response response = bookingSteps.updatePartialBookingById(bookingId,token,firstname);
              response.then().log().all().statusCode(200);
//      HashMap<String,Object> bookingMap = bookingSteps.getSingleBookingById(bookingId);
//      Assert.assertThat(bookingMap,hasValue(bookingId));

    }
    @Title("This method will delete booking information")
    @Test
    public void test007(){
      bookingSteps.deleteBookingById(bookingId,token).statusCode(201);
    }




}


