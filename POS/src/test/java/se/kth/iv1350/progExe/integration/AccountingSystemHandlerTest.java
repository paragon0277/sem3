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
 * Test class for the AccountingSystemHandler class.
 */
class AccountingSystemHandlerTest {

    private AccountingSystemHandler accountingSystemHandler;
    private ItemDTO testItemDTO = new ItemDTO(999, 40, "test Item", 0.1);
    private ReceiptDTO receiptDTO;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    /**
     * Sets up a new AccountingSystemHandler instance and initializes the necessary objects before each test.
     */
    @BeforeEach
    void setUp() {
        // Redirect system output to a buffer
        printoutBuffer = new ByteArrayOutputStream();
        PrintStream inMemSysOut = new PrintStream(printoutBuffer);
        originalSysOut = System.out;
        System.setOut(inMemSysOut);

        // Initialize AccountingSystemHandler and create a sale
        accountingSystemHandler = new AccountingSystemHandler();
        Sale sale = new Sale();
        sale.addItem(testItemDTO, 1);
        sale.closeSale();
        receiptDTO = sale.enterPayment(100);
    }

    /**
     * Cleans up and restores system output after each test.
     */
    @AfterEach
    void tearDown() {
        printoutBuffer = null;
        System.setOut(originalSysOut);
        accountingSystemHandler = null;
    }

    /**
     * Tests the updateAccounting method to ensure it updates the external accounting system correctly.
     */
    @Test
    void testUpdateAccounting() {
        accountingSystemHandler.updateAccounting(receiptDTO);
        String printout = printoutBuffer.toString();
        String expectedOutput = "updated";
        assertTrue(printout.contains(expectedOutput), "The external accounting system update message was not found.");
    }
}
