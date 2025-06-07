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
    private String email;
    private String estimated_guests;
    private double estimated_price;

    // Constructors
    public BookVenueModel(String email,String estimated_guests,double estimated_price) {
        this.email = email;
        this.estimated_guests = estimated_guests;
        this.estimated_price=estimated_price;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the estimated_guests
     */
    public String getEstimated_guests() {
        return estimated_guests;
    }

    /**
     * @param estimated_guests the estimated_guests to set
     */
    public void setEstimated_guests(String estimated_guests) {
        this.estimated_guests = estimated_guests;
    }

    /**
     * @return the estimated_price
     */
    public double getEstimated_price() {
        return estimated_price;
    }

    /**
     * @param estimated_price the estimated_price to set
     */
    public void setEstimated_price(double estimated_price) {
        this.estimated_price = estimated_price;
    }
    
    
}

