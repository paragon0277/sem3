package se.kth.iv1350.progExe.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.progExe.integration.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Controller class.
 */
class ControllerTest {

    // Declare controller and handlers for various systems
    Controller controller;
    PrinterHandler prnt = new PrinterHandler();
    DiscountDatabaseHandler disc = new DiscountDatabaseHandler();
    InventorySystemHandler inv = new InventorySystemHandler();
    AccountingSystemHandler acc = new AccountingSystemHandler();

    /**
     * Sets up a new Controller instance before each test.
     */
    @BeforeEach
    void setUp() {
        controller = new Controller(prnt, acc, disc, inv);
        controller.startSale();
    }

    /**
     * Cleans up the Controller instance after each test.
     */
    @AfterEach
    void tearDown() {
        controller = null;
    }

    /**
     * Tests that the sale object is properly initialized when a new sale is started.
     */
    @Test
    void testStartSale() {
        assertNotNull(controller.getSale(), "Sale object should be initialized after starting a new sale.");
    }

    /**
     * Tests entering a valid item into the sale.
     */
    @Test
    void testEnterValidItem() {
        ItemDTO itemDTO = null;
        try {
            itemDTO = controller.enterItem(1, 1);
            assertNotNull(itemDTO, "ItemDTO should be initialized after entering a valid item.");
        } catch (Exception e) {
            fail("A valid item threw an unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Tests entering an invalid item, expecting an UnknownItemIDException.
     */
    @Test
    void testEnterInvalidItem() {
        int invalidItemID = -2;
        ItemDTO itemDTO = null;
        try {
            itemDTO = controller.enterItem(invalidItemID, 1);
            fail("An invalid item ID should throw UnknownItemIDException.");
        } catch (UnknownItemIDException e) {
            assertEquals("Unknown item ID: " + invalidItemID, e.getMessage(),
                    "Exception message did not match expected value.");
            assertEquals(invalidItemID, e.getItemIDThatIsUnknown(),
                    "Exception did not return the correct unknown item ID.");
        } catch (OperationFailedException e) {
            fail("UnknownItemIDException should have been thrown instead of OperationFailedException.");
        }
    }

    /**
     * Tests simulating a failed connection when entering an item, expecting an OperationFailedException.
     */
    @Test
    public void testEnterItemFailedConnection() {
        int simulateFailedConnectionID = InventorySystemHandler.ITEM_ID_TO_SIMULATE_FAILED_CONNECTION;
        try {
            controller.enterItem(simulateFailedConnectionID, 1);
            fail("Expected an OperationFailedException due to simulated failed connection.");
        } catch (OperationFailedException e) {
            System.out.println("Caught exception: " + e.getMessage());
            assertEquals("Failed to retrieve item details", e.getMessage(),
                    "Exception message did not match expected value.");
        } catch (Exception e) {
            fail("Expected an OperationFailedException, but got a different exception.");
        }
    }

    /**
     * Tests entering duplicate items into the sale.
     */
    @Test
    void testEnterDuplicateItem() {
        try {
            controller.enterItem(1, 1);
            ItemDTO itemDTO = controller.enterItem(1, 1);
            assertNotNull(itemDTO, "ItemDTO should be returned even when adding duplicate items.");
        } catch (Exception e) {
            fail("A valid item threw an unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Tests closing a sale with no items, expecting a total price of 0.
     */
    @Test
    void testCloseSaleNoItems() {
        double totalPrice = controller.closeSale();
        assertEquals(0, totalPrice, "Total price should be 0 when no items are added to the sale.");
    }

    /**
     * Tests closing a sale with items, expecting a positive total price.
     */
    @Test
    void testCloseSaleWithItems() {
        try {
            controller.enterItem(1, 1);
            double totalPrice = controller.closeSale();
            assertTrue(totalPrice > 0, "Total price should be positive when items are added to the sale.");
        } catch (Exception e) {
            fail("A valid item threw an unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Tests entering a payment and calculating the change.
     */
    @Test
    void testEnterPayment() {
        ItemDTO itemDTO = new ItemDTO(9999, 10, "test item", 0.10);
        controller.getSale().addItem(itemDTO, 1);
        controller.closeSale();
        double change = controller.enterPayment(20);
        double expectedChange = 9;
        assertEquals(change, expectedChange, "The calculated change did not match the expected value.");
    }
}
