package com.restful.booker.model;

import java.util.HashMap;

public class BookingPojo {
    private String firstname;
    private String lastname;
    private int totalprice;
    private boolean depositpaid;


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public HashMap<String, Object> getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(HashMap<String, Object> bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    private HashMap<String, Object> bookingdates;
    private String additionalneeds;

    public static BookingPojo getBookingPojo(String firstname, String lastname, int totalprice, boolean depositpaid, String additionalneeds, HashMap<String, Object> bookingdates) {
        HashMap<String, Object> booking = new HashMap<String, Object>();
        booking.put("checkin", "2022-01-01");
        booking.put("checkout", "2022-02-01");

        BookingPojo bookingPojo = new BookingPojo();

        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setBookingdates(booking);
        bookingPojo.setAdditionalneeds(additionalneeds);
        return bookingPojo;

    }

    public static BookingPojo getBookingPojo(String firstname) {
        HashMap<String, Object> booking = new HashMap<String, Object>();
        booking.put("checkin", "2022-01-01");
        booking.put("checkout", "2022-02-01");

        BookingPojo bookingPojo = new BookingPojo();

        bookingPojo.setFirstname("Zeel");

        return bookingPojo;

    }
}