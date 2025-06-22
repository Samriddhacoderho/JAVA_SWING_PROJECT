package javaapplication6.Test;

import javaapplication6.dao.RegisterVenueDAO;
import javaapplication6.model.VenueModel;
import javaapplication6.model.LoginModel;
import javaapplication6.model.BookVenueModel;
import org.junit.Assert;
import org.junit.Test;

public class RegisterVenueDAOTest {

    @Test
    public void testRegisterVenue() {
        RegisterVenueDAO dao = new RegisterVenueDAO();
        VenueModel venue = new VenueModel("Test Venue", "Test City", "venue@example.com", "1234567890", 1000.0, "Unbooked");
        boolean result = dao.registerVenue(venue);
        Assert.assertTrue("Venue registration should be successful", result);
    }

    @Test
    public void testUpdateVenueDetails() {
        RegisterVenueDAO dao = new RegisterVenueDAO();
        VenueModel venue = new VenueModel("Test Venue", "Test City", "venue@example.com", "1234567890", 1200.0, "Unbooked");
        venue.setId(1);
        boolean result = dao.updateVenueDetails(venue);
        Assert.assertTrue("Venue update should be successful", result);
    }
}
