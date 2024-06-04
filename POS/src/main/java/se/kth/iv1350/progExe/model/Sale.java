package se.kth.iv1350.progExe.model;

import se.kth.iv1350.progExe.integration.ItemDTO;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The Sale class represents a sale transaction for one customer.
 */
public class Sale {
    private LocalTime timeOfSale;
    private ArrayList<LineItem> itemsInSale = new ArrayList<>();
    private double totalPrice;
    private double VAT;
    private Payment payment;
    private List<PaymentObserver> paymentObservers = new ArrayList<>();

    /**
     * Constructs a new Sale object for one customer with the current time.
     * The timeOfSale attribute is set to the current system time when the sale is initiated.
     */
    public Sale() {
        timeOfSale = LocalTime.now();
    }

    /**
     * Checks if a line item with the specified item ID exists in the sale.
     *
     * @param searchedItemID The ID of the item to search for.
     * @return The LineItem if found; otherwise, null.
     */
    private LineItem findLineItem(int searchedItemID) {
        for (LineItem lineItem : itemsInSale) {
            if (lineItem.getItemDTO().getItemID() == searchedItemID) {
                return lineItem;
            }
        }
        return null;
    }

    /**
     * Checks if an item with the specified ID is already entered in the sale.
     *
     * @param searchedItemID The ID of the item to search for.
     * @return True if the item is already entered in the sale; otherwise, false.
     */
    public boolean isAlreadyEntered(int searchedItemID) {
        return findLineItem(searchedItemID) != null;
    }

    /**
     * Increments the quantity of an item with the specified ID in the sale.
     *
     * @param itemID The ID of the item whose quantity to increment.
     * @return The item DTO of the incremented item if found; otherwise, null.
     */
    public ItemDTO incrementItemQuantity(int itemID) {
        LineItem lineItem = findLineItem(itemID);
        if (lineItem != null) {
            lineItem.incrementQuantity();
            ItemDTO currentItemDTO = lineItem.getItemDTO();
            updateTotalPriceAndVAT(currentItemDTO);
            return currentItemDTO;
        }
        return null;
    }

    /**
     * Adds a new item to the sale with the provided item DTO and quantity.
     *
     * @param itemDTO  The DTO representing the item to add.
     * @param quantity The quantity of the item to add.
     */
    public void addItem(ItemDTO itemDTO, int quantity) {
        if (itemDTO != null && quantity > 0) {
            LineItem newItem = new LineItem(itemDTO, quantity);
            itemsInSale.add(newItem);
            updateTotalPriceAndVAT(itemDTO);
        }
    }

    /**
     * Closes the current sale, creates a payment object, and calculates the total price including VAT.
     *
     * @return The total price of the sale including VAT.
     */
    public double closeSale() {
        double totalPriceInclVAT = totalPrice + VAT;
        payment = new Payment(totalPrice, VAT, totalPriceInclVAT);
        return totalPriceInclVAT;
    }

    /**
     * Enters the payment amount and generates a receipt.
     *
     * @param amount The amount paid by the customer.
     * @return The receipt DTO containing information about the sale and payment.
     */
    public ReceiptDTO enterPayment(double amount) {
        payment.setAmountPaid(amount);
        notifyObservers();
        ReceiptDTO receipt = new ReceiptDTO(this, payment);
        return receipt;
    }

    /**
     * Notifies all registered payment observers of the new payment.
     */
    private void notifyObservers() {
        for (PaymentObserver paymentObserver : paymentObservers) {
            paymentObserver.newPayment(payment.getTotalToPay());
        }
    }

    /**
     * Updates the total price and VAT of the sale based on the provided itemDTO.
     *
     * @param itemDTO The ItemDTO object containing information about the item being added to the sale.
     */
    private void updateTotalPriceAndVAT(ItemDTO itemDTO) {
        totalPrice += itemDTO.getPrice();
        VAT += itemDTO.getPrice() * itemDTO.getVAT();
    }

    /**
     * Gets the list of items in the sale.
     *
     * @return The list of items in the sale.
     */
    public ArrayList<LineItem> getItemsInSale() {
        return itemsInSale;
    }

    /**
     * Gets the time of the sale.
     *
     * @return The time of the sale.
     */
    public LocalTime getTimeOfSale() {
        return timeOfSale;
    }

    /**
     * Adds a list of payment observers to be notified of new payments.
     *
     * @param paymentObservers The list of PaymentObserver objects to add.
     */
    public void addPaymentObservers(List<PaymentObserver> paymentObservers) {
        this.paymentObservers.addAll(paymentObservers);
    }

    /**
     * The LineItem class represents an item in the sale.
     */
    class LineItem {
        private final ItemDTO itemDTO;
        private int quantity;

        /**
         * Constructs a new LineItem object with the specified item DTO and quantity.
         *
         * @param itemDTO  The DTO representing the item.
         * @param quantity The quantity of the item.
         */
        public LineItem(ItemDTO itemDTO, int quantity) {
            this.itemDTO = itemDTO;
            this.quantity = quantity;
        }

        /**
         * Gets the item DTO of the line item.
         *
         * @return The item DTO.
         */
        public ItemDTO getItemDTO() {
            return itemDTO;
        }

        /**
         * Gets the quantity of the line item.
         *
         * @return The quantity.
         */
        public int getQuantity() {
            return quantity;
        }

        /**
         * Increments the quantity of the line item by one.
         */
        public void incrementQuantity() {
            quantity++;
        }
    }
}
