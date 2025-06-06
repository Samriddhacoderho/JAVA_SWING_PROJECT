/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.util.ArrayList;
import javaapplication6.model.VenueModel;
import javaapplication6.view.VenueListView;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author suhritsatyal
 */
public class VenueListController {
    
    private final VenueListView venuelistView;
    
    public VenueListController(VenueListView venuelistView)
    {
        this.venuelistView=venuelistView;
    }
    
    public void open()
    {
        this.venuelistView.setVisible(true);
    }
    
    public void close()
    {
        this.venuelistView.dispose();
    }
    
    public void setTableContent(ArrayList<VenueModel> venuelist)
    {
        DefaultTableModel model=(DefaultTableModel) venuelistView.getjTable1().getModel();
        model.setRowCount(0);
        
        int sn=1;
        for(VenueModel venueModel:venuelist)
        {
            model.addRow(new Object[]{sn++,venueModel.getName(),venueModel.getLocation(),"Book Now"});
        }
    }
    
}
