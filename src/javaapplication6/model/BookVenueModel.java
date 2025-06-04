/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.model;

/**
 *
 * @author manoj
 */
public class BookVenueModel {
    private String bookingName;
    private String email;
    private String contact;
    private String date;
    private String time;
    private String eventType;
    private int estimatedGuests;

    // Constructors
    public BookVenueModel() {}

    public BookVenueModel(String bookingName, String email, String contact, String date, String time, String eventType, int estimatedGuests) {
        this.bookingName = bookingName;
        this.email = email;
        this.contact = contact;
        this.date = date;
        this.time = time;
        this.eventType = eventType;
        this.estimatedGuests = estimatedGuests;
    }

    // Getters and Setters
    public String getBookingName() {
        return bookingName;
    }

    public void setBookingName(String bookingName) {
        this.bookingName = bookingName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public int getEstimatedGuests() {
        return estimatedGuests;
    }

    public void setEstimatedGuests(int estimatedGuests) {
        this.estimatedGuests = estimatedGuests;
    }
}

