package se.kth.iv1350.progExe.integration;

/**
 * The ItemDTO class represents items in the inventory system.
 */
public class ItemDTO {
    private int itemID;
    private double price;
    private String itemDescription;
    private double VAT;

    /**
     * Constructs an ItemDTO object with the specified attributes.
     *
     * @param itemID The ID of the item.
     * @param price The price of the item.
     * @param itemDescription The description of the item.
     * @param VAT The VAT (Value Added Tax) rate applicable to the item.
     */
    public ItemDTO(int itemID, double price, String itemDescription, double VAT) {
        this.itemID = itemID;
        this.price = price;
        this.itemDescription = itemDescription;
        this.VAT = VAT;
    }

    /**
     * Gets the ID of the item.
     *
     * @return The item ID.
     */
    public int getItemID() {
        return itemID;
    }

    /**
     * Gets the price of the item.
     *
     * @return The price of the item.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the description of the item.
     *
     * @return The item description.
     */
    public String getItemDescription() {
        return itemDescription;
    }

    /**
     * Gets the VAT rate applicable to the item.
     *
     * @return The VAT rate.
     */
    public double getVAT() {
        return VAT;
    }
}
