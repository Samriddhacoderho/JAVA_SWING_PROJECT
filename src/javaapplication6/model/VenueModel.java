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
    private int id;
    private String name;
    private String location;
    private String email;
    private String contact_number;
    private double price_per_plate;
    private String status;
    private byte[] image;
    
    public VenueModel(String name,String location,String email,String contact_number,double price_per_plate,String status)
    {
        this.name=name;
        this.location=location;
        this.price_per_plate=price_per_plate;
        this.email=email;
        this.contact_number=contact_number;
        this.status=status;
    }
    
    public VenueModel(int id,String name,String location,String email,String contact_number,double price_per_plate,String status)
    {
        this.id=id;
        this.name=name;
        this.location=location;
        this.price_per_plate=price_per_plate;
        this.email=email;
        this.contact_number=contact_number;
        this.status=status;
    }


    public VenueModel (String name, String location) {
        this.name = name;
        this.location = location;

    }
    
    public VenueModel(String location)
    {
        this.location=location;
    }
    
    
    
    public VenueModel(int id,String name,String location,String email,String contact_number,double price_per_plate,byte[] image)
    {
        this.id=id;
        this.name=name;
        this.location=location;
        this.price_per_plate=price_per_plate;
        this.email=email;
        this.contact_number=contact_number;
        this.image=image;
    }

    public VenueModel() {
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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the image
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(byte[] image) {
        this.image = image;
    }
    
       
}