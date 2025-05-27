/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;

/**
 *
 * @author ishan-college
 */
public class validate_changeps {
    public static String validatePasswordChange(String storedOldPassword, String enteredOldPassword, String newPassword, String confirmNewPassword) {
    if (enteredOldPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
        return "All password fields must be filled out";
    } else if (!enteredOldPassword.equals(storedOldPassword)) {
        return "Old password is incorrect";
    } else if (!newPassword.equals(confirmNewPassword)) {
        return "New passwords do not match";
    } else if (newPassword.length() <= 8) {
        return "New password must be between atleast 9 characters long";
    } else {
        return "Changing password...";
    }
}

    
}


