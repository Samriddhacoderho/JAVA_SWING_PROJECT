/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.model;

/**
 *
 * @author Dell
 */
public class InquiryModel {
    private String name;
    private String email;
    private String message;
    private String adminEmail;

    public InquiryModel(String name, String email, String message,String adminEmail) {
        this.name = name;
        this.email = email;
        this.message = message;
        this.adminEmail = adminEmail;
    }

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public void setadminEmail(String adminEmail){
        this.adminEmail = adminEmail;
    }
    public String getadminEmail(){
        return adminEmail;
    }
}

