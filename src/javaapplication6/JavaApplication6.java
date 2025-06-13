/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package javaapplication6;

import javaapplication6.controller.EntryController;
import javaapplication6.view.EntryView;

/**
 *
 * @author suhritsatyal
 */
public class JavaApplication6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EntryView homeView=new EntryView();
        EntryController homeController=new EntryController(homeView);
        homeController.open();
        
    }
    
}
