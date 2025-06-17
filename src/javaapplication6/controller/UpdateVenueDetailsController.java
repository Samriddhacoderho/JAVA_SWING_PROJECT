package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javaapplication6.model.LoginModel;
import javaapplication6.view.UpdateVenueDetailsView;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class UpdateVenueDetailsController {
    
    private UpdateVenueDetailsView view;
    private LoginModel model;

    public UpdateVenueDetailsController(UpdateVenueDetailsView view,LoginModel model) {
        this.view = view;
        this.view.addUploadImageListener(new addUploadImageListener()); 
        this.model=model;
    }

    public void open()
    {
        this.view.setVisible(true);
    }
    
    public void close()
    {
        this.view.dispose();
    }
    class addUploadImageListener implements ActionListener {
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
