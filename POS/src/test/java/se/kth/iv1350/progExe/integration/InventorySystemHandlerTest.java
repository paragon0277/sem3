package se.kth.iv1350.progExe.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the InventorySystemHandler class.
 */
class InventorySystemHandlerTest {

    private InventorySystemHandler inventorySystemHandler;

    /**
     * Sets up a new InventorySystemHandler instance before each test.
     */
    @BeforeEach
    void setUp() {
        inventorySystemHandler = new InventorySystemHandler();
    }

    /**
     * Tests retrieving item details with a valid item ID.
     */
    @Test
    void testGetItemDetailsValid() {
        int validItemID = 1;
        ItemDTO itemDTO = null;

        try {
            itemDTO = inventorySystemHandler.getItemDetails(validItemID);
            assertNotNull(itemDTO, "A valid itemDTO should be returned for the given item ID.");
            assertEquals(validItemID, itemDTO.getItemID(),
                    "The returned item should have the same ID as the searched item.");
        } catch (Exception e) {
            fail("A valid item threw an unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Tests retrieving item details with an invalid item ID, expecting an UnknownItemIDException.
     */
    @Test
    void testGetItemDetailsInvalid() {
        int invalidItemID = -2;
        ItemDTO itemDTO;

        try {
            itemDTO = inventorySystemHandler.getItemDetails(invalidItemID);
            fail("An invalid item ID should throw UnknownItemIDException.");
        } catch (UnknownItemIDException e) {
            assertEquals("Unknown item ID: " + invalidItemID, e.getMessage(),
                    "Exception message did not match expected value.");
            assertEquals(invalidItemID, e.getItemIDThatIsUnknown(),
                    "Exception did not return the correct unknown item ID.");
        } catch (InventorySystemException e) {
            fail("An invalid item ID should throw UnknownItemIDException, not InventorySystemException.");
        }
    }

    /**
     * Tests simulating a failed connection when retrieving item details, expecting an InventorySystemException.
     */
    @Test
    public void testGetItemDetailsFailedConnection() {
        int simulateFailedConnectionID = InventorySystemHandler.ITEM_ID_TO_SIMULATE_FAILED_CONNECTION;
        try {
            inventorySystemHandler.getItemDetails(simulateFailedConnectionID);
            fail("Expected an InventorySystemException due to simulated failed connection.");
        } catch (InventorySystemException e) {
            assertEquals("Could not connect to external inventory system", e.getMessage(),
                    "Exception message did not match expected value.");
        } catch (UnknownItemIDException e) {
            fail("Expected an InventorySystemException, but got a different exception.");
        }
    }
}
