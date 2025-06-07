/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.model;

/**
 *
 * @author ishan-college
 */
public class VenueModel {
    private String name;
    private String location;
    private String email;
    private String contact_number;
    private double price_per_plate;
    
    public VenueModel(String name,String location,String email,String contact_number,double price_per_plate)
    {
        this.name=name;
        this.location=location;
        this.price_per_plate=price_per_plate;
        this.email=email;
        this.contact_number=contact_number;
    }


    public VenueModel (String name, String location) {
        this.name = name;
        this.location = location;

    }
    
    public VenueModel(String location)
    {
        this.location=location;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the price_per_plate
     */
    public double getPrice_per_plate() {
        return price_per_plate;
    }

    /**
     * @param price_per_plate the price_per_plate to set
     */
    public void setPrice_per_plate(double price_per_plate) {
        this.price_per_plate = price_per_plate;
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
     * @return the contact_number
     */
    public String getContact_number() {
        return contact_number;
    }

    /**
     * @param contact_number the contact_number to set
     */
    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }
    
    
    
}