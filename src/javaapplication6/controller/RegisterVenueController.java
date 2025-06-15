/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javaapplication6.view.UpdateVenueDetailsView;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author suhritsatyal
 */
public class RegisterVenueController {
    
}
    class UploadImageListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(UpdateVenueDetailsView);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (file.exists() && file.isFile()) {
                UpdateVenueDetailsView.setSelectedFile(file);
            } else {
                JOptionPane.showMessageDialog(UpdateVenueDetailsView,
                    "Invalid file selected.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
}
