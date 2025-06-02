package javaapplication6.model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class ReportProblemModel {
//     private int id;
//    private int bookingId;
    private String email;
    private String subject;
    private String description;
    private String status;


    public ReportProblemModel( String email, String subject, String description) {
        
//      this.bookingId = bookingId;
        this.email = email;
        this.subject = subject;
        this.description = description;
    }

    // Getters and Setters
    
//    just in case if required later , undocument the below methods
    
//    public int getId() { return id; }
//    public void setId(int id) { this.id = id; }
//
//    public int getBookingId() { return bookingId; }
//    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public String getEmail() 
    { 
        return email; 
    }
    public void setUserEmail(String email)
    { 
        this.email = email;
    }

    public String getSubject() 
    { 
        return subject;
    }
    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public String getDescription()
    { 
        return description;
    }
    public void setDescription(String description) 
    { 
        this.description = description;
    }
}
