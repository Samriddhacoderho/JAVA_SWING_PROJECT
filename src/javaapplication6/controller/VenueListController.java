/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javaapplication6.dao.BookVenueDAO;
import javaapplication6.model.VenueModel;
import javaapplication6.view.VenueListView;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author suhritsatyal
 */
public class VenueListController {
    
    private final VenueListView venuelistView;
    private final String location;
    private BookVenueDAO bookVenueDAO=new BookVenueDAO();
    
    public VenueListController(VenueListView venuelistView,String location)
    {
        this.venuelistView=venuelistView;
        this.location=location;
        this.venuelistView.jComboAction(new sortActionListner());
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
            model.addRow(new Object[]{sn++,venueModel.getName(),venueModel.getLocation(),venueModel.getPrice_per_plate(),"Unbooked"});
        }
    }
    
    class sortActionListner implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(!venuelistView.getjComboBox1().getSelectedItem().equals("Sort Price:"))
            {
               ArrayList<VenueModel> result=bookVenueDAO.sortVenues(location, venuelistView.getjComboBox1().getSelectedItem().toString());
               if(!result.isEmpty())
               {
                setTableContent(result);
               }
            }
        }
        
    }
}
   
