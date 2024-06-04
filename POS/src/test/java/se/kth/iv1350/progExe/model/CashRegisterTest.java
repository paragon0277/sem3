package se.kth.iv1350.progExe.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the CashRegister class.
 */
class CashRegisterTest {

    private CashRegister cashRegister;

    /**
     * Sets up a new CashRegister instance before each test.
     */
    @BeforeEach
    void setUp() {
        cashRegister = new CashRegister();
    }

    /**
     * Cleans up the CashRegister instance after each test.
     */
    @AfterEach
    void tearDown() {
        cashRegister = null;
    }

    /**
     * Tests the updateBalance method to ensure the balance is updated correctly.
     */
    @Test
    void testUpdateBalance() {
        double totalPrice = 20;
        cashRegister.updateBalance(totalPrice);
        cashRegister.updateBalance(totalPrice);

        // Check that the balance is updated correctly after two transactions
        assertEquals(totalPrice * 2, cashRegister.getBalance(), "The cash register balance was not updated correctly.");
    }
}
