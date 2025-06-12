package javaapplication6.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingModel {
    private int bookingId;
    private int venueId;
    private int userId;
    private String venueName;
    private String venueLocation;
    private String userName;
    private String userEmail;
    private String userPhone;
    private int guestCount;
    private double totalPrice;
    private LocalDate eventDate;
    private LocalDateTime bookingDateTime;
    private String status; 
    private String rejectionReason;
    private String eventType;
    private String specialRequirements;
    
    // Default constructor
    public BookingModel() {}
    
//    constructor
    public BookingModel(int bookingId, int venueId, int userId, String venueName, 
                       String venueLocation, String userName, String userEmail, 
                       String userPhone, int guestCount, double totalPrice, 
                       LocalDate eventDate, String status) {
        this.bookingId = bookingId;
        this.venueId = venueId;
        this.userId = userId;
        this.venueName = venueName;
        this.venueLocation = venueLocation;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.guestCount = guestCount;
        this.totalPrice = totalPrice;
        this.eventDate = eventDate;
        this.status = status;
        this.bookingDateTime = LocalDateTime.now();
    }
    
    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }
    
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    
    public int getVenueId() {
        return venueId;
    }
    
    public void setVenueId(int venueId) {
        this.venueId = venueId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getVenueName() {
        return venueName;
    }
    
    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
    
    public String getVenueLocation() {
        return venueLocation;
    }
    
    public void setVenueLocation(String venueLocation) {
        this.venueLocation = venueLocation;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserEmail() {
        return userEmail;
    }
    
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    
    public String getUserPhone() {
        return userPhone;
    }
    
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
    
    public int getGuestCount() {
        return guestCount;
    }
    
    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public LocalDate getEventDate() {
        return eventDate;
    }
    
    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }
    
    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }
    
    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getRejectionReason() {
        return rejectionReason;
    }
    
    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
    
    public String getEventType() {
        return eventType;
    }
    
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    
    public String getSpecialRequirements() {
        return specialRequirements;
    }
    
    public void setSpecialRequirements(String specialRequirements) {
        this.specialRequirements = specialRequirements;
    }
    
    @Override
    public String toString() {
        return "BookingModel{" +
                "bookingId=" + bookingId +
                ", venueName='" + venueName + '\'' +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", eventDate=" + eventDate +
                ", status='" + status + '\'' +
                '}';
    }
}
