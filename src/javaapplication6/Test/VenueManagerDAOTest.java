package javaapplication6.Test;

import javaapplication6.dao.VenueManagerDAO;
import javaapplication6.model.RegisterModel;
import javaapplication6.model.LoginModel;
import javaapplication6.model.VenueModel;
import javaapplication6.model.BookVenueModel;
import javaapplication6.model.VenueDetailsFetchModel;
import org.junit.Assert;
import org.junit.Test;

public class VenueManagerDAOTest {
    private String testName = "Venue Manager";
    private String testEmail = "manager" + System.currentTimeMillis() + "@venue.com";
    private String testPassword = "venuepass";

    @Test
    public void testRegisterVM() {
        VenueManagerDAO dao = new VenueManagerDAO();
        RegisterModel model = new RegisterModel(testName, testEmail, testPassword, testPassword);
        boolean result = dao.registerVM(model);
        Assert.assertTrue("Venue manager registration should succeed", result);
    }

    @Test
    public void testLoginManager() {
        VenueManagerDAO dao = new VenueManagerDAO();
        RegisterModel model = new RegisterModel(testName, testEmail, testPassword, testPassword);
        dao.registerVM(model);

        LoginModel login = new LoginModel(testEmail, testPassword);
        boolean result = dao.loginManager(login);
        Assert.assertTrue("Venue manager login should succeed", result);
    }

    @Test
    public void testRegisterVenue() {
        VenueManagerDAO dao = new VenueManagerDAO();
        VenueModel venue = new VenueModel(
            "Test Venue",
            "Test City",
            testEmail,
            "1234567890",
            1000.0,
            "Unbooked"
        );
        boolean result = dao.registerVenue(venue);
        Assert.assertTrue("Venue registration should succeed", result);
    }

    @Test
    public void testApproveRequest() {
        VenueManagerDAO dao = new VenueManagerDAO();
        LoginModel login = new LoginModel(testEmail, testPassword);
        BookVenueModel bookModel = new BookVenueModel("user@example.com", "50", 5000.0);
        VenueModel venue = new VenueModel("Test Venue", "Test City", testEmail, "1234567890", 1000.0, "Pending");
        VenueDetailsFetchModel details = new VenueDetailsFetchModel(
            1, "user@example.com", "Test Venue", "Test City",
            testEmail, "1234567890", 50, "Pending", 1000.0, 50000L,
            "No", "No", "pending"
        );
        boolean result = dao.approveRequest(login, bookModel, venue, details);
        Assert.assertTrue("Approve request should succeed", result);
    }

    @Test
    public void testRejectRequest() {
        VenueManagerDAO dao = new VenueManagerDAO();
        LoginModel login = new LoginModel(testEmail, testPassword);
        BookVenueModel bookModel = new BookVenueModel("user@example.com", "50", 5000.0);
        VenueModel venue = new VenueModel("Test Venue", "Test City", testEmail, "1234567890", 1000.0, "Pending");
        VenueDetailsFetchModel details = new VenueDetailsFetchModel(
            1, "user@example.com", "Test Venue", "Test City",
            testEmail, "1234567890", 50, "Pending", 1000.0, 50000L,
            "No", "No", "pending"
        );
        boolean result = dao.rejectRequest(login, bookModel, venue, details);
        Assert.assertTrue("Reject request should succeed", result);
    }
}
