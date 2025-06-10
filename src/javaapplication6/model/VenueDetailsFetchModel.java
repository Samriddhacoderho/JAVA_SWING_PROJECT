/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.model;

/**
 *
 * @author suhritsatyal
 */
public class VenueDetailsFetchModel {
    private int venue_id;
    private String user_email;
    private String venue_name;
    private String venue_location;
    private String venue_email;
    private String venue_contactnum;
    private int estimated_guests;
    private String status;
    private double price_per_plate;
    private Long total_price;
    
    public VenueDetailsFetchModel(
        int venue_id,
        String user_email,
        String venue_name,
        String venue_location,
        String venue_email,
        String venue_contactnum,
        int estimated_guests,
        String status,
        double price_per_plate,
        Long total_price
    ) {
        this.venue_id = venue_id;
        this.user_email = user_email;
        this.venue_name = venue_name;
        this.venue_location = venue_location;
        this.venue_email = venue_email;
        this.venue_contactnum = venue_contactnum;
        this.estimated_guests = estimated_guests;
        this.status = status;
        this.price_per_plate = price_per_plate;
        this.total_price = total_price;
    }

    /**
     * @return the venue_id
     */
    public int getVenue_id() {
        return venue_id;
    }

    /**
     * @param venue_id the venue_id to set
     */
    public void setVenue_id(int venue_id) {
        this.venue_id = venue_id;
    }

    /**
     * @return the user_email
     */
    public String getUser_email() {
        return user_email;
    }

    /**
     * @param user_email the user_email to set
     */
    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    /**
     * @return the venue_name
     */
    public String getVenue_name() {
        return venue_name;
    }

    /**
     * @param venue_name the venue_name to set
     */
    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    /**
     * @return the venue_location
     */
    public String getVenue_location() {
        return venue_location;
    }

    /**
     * @param venue_location the venue_location to set
     */
    public void setVenue_location(String venue_location) {
        this.venue_location = venue_location;
    }

    /**
     * @return the venue_email
     */
    public String getVenue_email() {
        return venue_email;
    }

    /**
     * @param venue_email the venue_email to set
     */
    public void setVenue_email(String venue_email) {
        this.venue_email = venue_email;
    }

    /**
     * @return the venue_contactnum
     */
    public String getVenue_contactnum() {
        return venue_contactnum;
    }

    /**
     * @param venue_contactnum the venue_contactnum to set
     */
    public void setVenue_contactnum(String venue_contactnum) {
        this.venue_contactnum = venue_contactnum;
    }

    /**
     * @return the estimated_guests
     */
    public int getEstimated_guests() {
        return estimated_guests;
    }

    /**
     * @param estimated_guests the estimated_guests to set
     */
    public void setEstimated_guests(int estimated_guests) {
        this.estimated_guests = estimated_guests;
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
     * @return the total_price
     */
    public Long getTotal_price() {
        return total_price;
    }

    /**
     * @param total_price the total_price to set
     */
    public void setTotal_price(Long total_price) {
        this.total_price = total_price;
    }
    
    
    
    
}
