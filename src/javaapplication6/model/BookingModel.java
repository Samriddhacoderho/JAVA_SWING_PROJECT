package javaapplication6.model;

public class BookingModel {
    private int id;
    private int venueId;
    private String userEmail;
    private String estimatedGuests;
    private float totalPrice;
    
    // Venue details (from venue_table)
    private String venueName;
    private String venueLocation;
    private String venueEmail;
    private String venueContactNumber;
    private float pricePerPlate;
    private String venueStatus;
    private byte[] image;

    // User details (from users table)
    private String userName;
    
    // Default constructor
    public BookingModel() {}
    
    // Constructor matching your database
    public BookingModel(int id, int venueId, String userEmail, String estimatedGuests, float totalPrice) {
        this.id = id;
        this.venueId = venueId;
        this.userEmail = userEmail;
        this.estimatedGuests = estimatedGuests;
        this.totalPrice = totalPrice;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public int getVenueId() { return venueId; }
    public void setVenueId(int venueId) { this.venueId = venueId; }
    
    public String getUserEmail() { return userEmail; }
    public void setUserEmail(String userEmail) { this.userEmail = userEmail; }
    
    public String getEstimatedGuests() { return estimatedGuests; }
    public void setEstimatedGuests(String estimatedGuests) { this.estimatedGuests = estimatedGuests; }
    
    public float getTotalPrice() { return totalPrice; }
    public void setTotalPrice(float totalPrice) { this.totalPrice = totalPrice; }
    
    // Venue details
    public String getVenueName() { return venueName; }
    public void setVenueName(String venueName) { this.venueName = venueName; }
    
    public String getVenueLocation() { return venueLocation; }
    public void setVenueLocation(String venueLocation) { this.venueLocation = venueLocation; }
    
    public String getVenueEmail() { return venueEmail; }
    public void setVenueEmail(String venueEmail) { this.venueEmail = venueEmail; }
    
    public String getVenueContactNumber() { return venueContactNumber; }
    public void setVenueContactNumber(String venueContactNumber) { this.venueContactNumber = venueContactNumber; }
    
    public float getPricePerPlate() { return pricePerPlate; }
    public void setPricePerPlate(float pricePerPlate) { this.pricePerPlate = pricePerPlate; }
    
    public String getVenueStatus() { return venueStatus; }
    public void setVenueStatus(String venueStatus) { this.venueStatus = venueStatus; }
    
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

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
