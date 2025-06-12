/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.dao;

import javaapplication6.database.DBConn;
import javaapplication6.model.RegisterModel;

/**
 *
 * @author ishan-college
 */
public class VenueManagerDAO {
    private final DBConn dbConn;
    
    public VenueManagerDAO() {
        dbConn = new DBConn();
    }
    
    public boolean registerVM(RegisterModel model)
    {
        return true;
        //write dao logic here
    }
}
