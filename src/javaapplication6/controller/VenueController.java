/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.util.ArrayList;
import javaapplication6.dao.UserDAO;
import javaapplication6.model.VenueModel;


/**
 *
 * @author ishan-college
 */

public class VenueController {
    private UserDAO userDAO;

    public VenueController() {
        this.userDAO = new UserDAO();
    }

    public ArrayList<VenueModel> searchVenues(String name, String location) {
        VenueModel model = new VenueModel();
        model.setName(name);
        model.setLocation(location);

        return userDAO.checkVenue(model);
    }
}
