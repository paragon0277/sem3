package se.kth.iv1350.progExe.startup;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the Main class.
 */
class MainTest {
    private Main instanceToTest;
    private ByteArrayOutputStream printoutBuffer;
    private PrintStream originalSysOut;

    /**
     * Sets up a new Main instance and redirects system output before each test.
     */
    @BeforeEach
    void setUp() {
        instanceToTest = new Main();

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
        instanceToTest = null;
        printoutBuffer = null;
        System.setOut(originalSysOut);
    }

    /**
     * Tests that the UI starts correctly when the main method is called.
     */
    @Test
    void testUIHasStarted() throws Exception {
        Main.main(null);
        String printout = printoutBuffer.toString();
        String expectedOutput = "Application has started";
        assertTrue(printout.contains(expectedOutput), "The UI did not start correctly.");
    }
}
