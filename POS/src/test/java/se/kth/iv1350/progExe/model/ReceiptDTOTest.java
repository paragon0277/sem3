package se.kth.iv1350.progExe.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.progExe.integration.ItemDTO;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ReceiptDTO class.
 */
class ReceiptDTOTest {

    ReceiptDTO receiptDTO;
    Sale sale;
    ItemDTO testItemDTO = new ItemDTO(999, 40, "test Item", 0.1);

    /**
     * Sets up a new Sale instance and creates a ReceiptDTO before each test.
     */
    @BeforeEach
    void setUp() {
        sale = new Sale();
        sale.addItem(testItemDTO, 1);
        sale.closeSale();
        receiptDTO = sale.enterPayment(100);
    }

    /**
     * Cleans up the ReceiptDTO and Sale instances after each test.
     */
    @AfterEach
    void tearDown() {
        receiptDTO = null;
        sale = null;
    }

    /**
     * Tests the toString method to ensure the receipt string representation contains all necessary information.
     */
    @Test
    void testToString() {
        String resultString = receiptDTO.toString();

        // Check that the receipt string contains the necessary information
        assertTrue(resultString.contains("------------- Receipt -------------"), "The receipt title is missing.");
        assertTrue(resultString.contains("Time of Sale:"), "The time of sale is missing.");
        assertTrue(resultString.contains("Items Bought:"), "The items bought section is missing.");
        assertTrue(resultString.contains("Total Price: 44.00 EUR"),  "The total price is missing or incorrect.");
        assertTrue(resultString.contains("Total VAT: 4.00 EUR"), "The total VAT is missing or incorrect.");
        assertTrue(resultString.contains("Amount Paid: 100.00 EUR"), "The amount paid is missing or incorrect.");
        assertTrue(resultString.contains("Change: 56.00 EUR"), "The change amount is missing or incorrect.");
    }
}
