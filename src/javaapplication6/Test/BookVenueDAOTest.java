package javaapplication6.Test;

import javaapplication6.dao.BookVenueDAO;
import javaapplication6.model.BookVenueModel;
import javaapplication6.model.VenueModel;
import javaapplication6.model.VenueDetailsFetchModel;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class BookVenueDAOTest {

    @Test
    public void testSearchParticularVenue() {
        BookVenueDAO dao = new BookVenueDAO();
        VenueModel venue = new VenueModel();
        venue.setName("Test Venue");
        venue.setLocation("Test City");
        VenueModel result = dao.searchParticularVenue(venue);
        Assert.assertNotNull("Venue should be found", result);
    }

    @Test
    public void testSearchLocationVenue() {
        BookVenueDAO dao = new BookVenueDAO();
        VenueModel venue = new VenueModel();
        venue.setLocation("Test City");
        ArrayList<VenueModel> venues = dao.searchLocationVenue(venue);
        Assert.assertNotNull("Venue list should not be null", venues);
    }

    @Test
    public void testBookVenue() {
        BookVenueDAO dao = new BookVenueDAO();
        BookVenueModel bookModel = new BookVenueModel("user@example.com", "50", 5000.0);
        VenueModel venue = new VenueModel();
        venue.setId(1);
        venue.setName("Test Venue");
        venue.setLocation("Test City");
        boolean result = dao.bookVenue(bookModel, venue);
        Assert.assertTrue("Booking should be successful", result);
    }

    @Test
    public void testCancelBookingWithFullConstructor() {
        BookVenueDAO dao = new BookVenueDAO();
        VenueDetailsFetchModel model = new VenueDetailsFetchModel(
                1,
                "user@example.com",
                "Test Venue",
                "Test City",
                "venue@example.com",
                "1234567890",
                300,
                "Pending",
                1000,
                300000L,
                "No",
                "No",
                "pending"
        );
        boolean result = dao.cancelBooking(model);
        Assert.assertTrue("Booking cancellation should be successful", result);
    }

}
