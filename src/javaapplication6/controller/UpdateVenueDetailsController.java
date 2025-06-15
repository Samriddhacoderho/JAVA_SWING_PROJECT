package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javaapplication6.view.UpdateVenueDetailsView;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class UpdateVenueDetailsController {
    
    private UpdateVenueDetailsView view;

    public UpdateVenueDetailsController(UpdateVenueDetailsView view) {
        this.view = view;
        this.view.UploadImageListener(new UploadImageListener()); 
    }

    class UploadImageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(view);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (file.exists() && file.isFile()) {
                    view.setSelectedFile(file);
                } else {
                    JOptionPane.showMessageDialog(view,
                            "Invalid file selected.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
