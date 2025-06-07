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
    private double price_per_plate;
    
    public VenueModel(String name,String location,double price_per_plate)
    {
        this.name=name;
        this.location=location;
        this.price_per_plate=price_per_plate;
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
    
    
}