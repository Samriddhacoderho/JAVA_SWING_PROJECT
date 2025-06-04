/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javaapplication6.view.BookVenue;

/**
 *
 * @author manoj
 */
public class BookVenueController {
    private final BookVenue bookVenue;
//    book venue dao object part
    
    public BookVenueController(BookVenue bookVenue){
        this.bookVenue=bookVenue;
       
        
    }
//    Book venue view open and close methods
    public void open(){
        this.bookVenue.setVisible(true);
    }
    public void close(){
        this.bookVenue.dispose();
    }
    
    
}
