package javaapplication6.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javaapplication6.view.UpdateVenueDetailsView;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class RegisterVenueController {
    private UpdateVenueDetailsView view;

    public RegisterVenueController(UpdateVenueDetailsView view) {
        this.view = view;
        this.view.UploadImageListener(new UploadImageListener());
    }

    class UploadImageListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(view.getPanel()); // <-- use jPanel2
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                if (file.exists() && file.isFile()) {
                    view.setSelectedFile(file);
                } else {
                    JOptionPane.showMessageDialog(view.getPanel(),
                        "Invalid file selected.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
