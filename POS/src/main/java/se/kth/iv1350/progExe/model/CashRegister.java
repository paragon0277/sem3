package se.kth.iv1350.progExe.model;

/**
 * The CashRegister class represents a cash register used in a retail system.
 */
public class CashRegister {
    private double balance;

    /**
     * Gets the current balance of the cash register.
     *
     * @return The current balance.
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Updates the balance of the cash register.
     *
     * @param totalPrice The total price of the items in a transaction.
     */
    public void updateBalance(double totalPrice) {
        // Add the total price of the transaction to the current balance
        balance += totalPrice;
    }
}
