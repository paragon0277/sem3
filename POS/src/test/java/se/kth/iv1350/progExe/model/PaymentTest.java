package se.kth.iv1350.progExe.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Payment class.
 */
class PaymentTest {

    /**
     * Tests the setAmountPaid method to ensure the amount paid is set correctly
     * and the change is calculated accurately.
     */
    @Test
    void testSetAmountPaid() {
        double totalPrice = 100.0;
        double totalVAT = 25.0;
        double totalToPay = totalPrice + totalVAT;
        double amountPaid = 150.0;

        // Create a new Payment instance
        Payment payment = new Payment(totalPrice, totalVAT, totalToPay);
        // Set the amount paid by the customer
        payment.setAmountPaid(amountPaid);

        // Check that the amount paid is set correctly
        assertEquals(amountPaid, payment.getAmountPaid(), "The amount paid was not set correctly.");
        // Check that the change is calculated correctly
        assertEquals(amountPaid - totalToPay, payment.getChange(), "The change was not calculated correctly.");
    }
}
