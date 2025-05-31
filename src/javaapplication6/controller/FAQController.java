/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6.controller;
import javaapplication6.view.FAQView;

/**
 *
 * @author ishan-college
 */
public class FAQController {
    private final FAQView faqView;

    public FAQController(FAQView faqView) {
        this.faqView = faqView;
        loadFAQContent();
    }

    private void loadFAQContent() {
        String faqText = """
            1) Where are we?
            Kathmandu, Dillibazar.

            2) What is the capacity of the venue?
            Max up to 20,000.

            3) What is the price of the venue?
            According to the guest.

            4) What other services do you have?
            We have cameraman service and more.
        """;

        faqView.setFAQText(faqText);
    }

    public void showFAQ() {
        faqView.setVisible(true);
    }
}