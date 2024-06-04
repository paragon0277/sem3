package se.kth.iv1350.progExe.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.progExe.model.ReceiptDTO;
import se.kth.iv1350.progExe.model.Sale;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the PrinterHandler class.
 */
class PrinterHandlerTest {
    private PrinterHandler printerHandler;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    /**
     * Sets up a new PrinterHandler instance and redirects system output before each test.
     */
    @BeforeEach
    void setUp() {
        printerHandler = new PrinterHandler();

        // Redirect system output to a buffer
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);
    }

    /**
     * Cleans up and restores system output after each test.
     */
    @AfterEach
    void tearDown() {
        printerHandler = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    /**
     * Tests the print method to ensure it prints the receipt correctly.
     */
    @Test
    void print_PrintsReceiptCorrectly() {
        Sale sale = new Sale();
        sale.closeSale();
        ReceiptDTO receipt = sale.enterPayment(100);
        printerHandler.print(receipt);
        String printout = printoutBuffer.toString();

        // Check that the printout contains the receipt's string representation
        assertTrue(printout.contains(receipt.toString()), "The printout does not match the receipt contents.");
    }
}
