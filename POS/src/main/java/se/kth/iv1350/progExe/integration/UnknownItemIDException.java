package se.kth.iv1350.progExe.integration;

/**
 * This class represents an exception that is thrown when an unknown item ID is encountered in the inventory system.
 */
public class UnknownItemIDException extends Exception {
    private int itemIDThatIsUnknown;

    /**
     * Creates a new instance of the exception with the specified unknown item ID.
     *
     * @param itemIDThatIsUnknown The item ID that is unknown.
     */
    public UnknownItemIDException(int itemIDThatIsUnknown) {
        super("Unknown item ID: " + itemIDThatIsUnknown);
        this.itemIDThatIsUnknown = itemIDThatIsUnknown;
    }

    /**
     * Gets the item ID that is unknown.
     *
     * @return The unknown item ID.
     */
    public int getItemIDThatIsUnknown() {
        return itemIDThatIsUnknown;
    }
}
