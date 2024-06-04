package se.kth.iv1350.progExe.model;

/**
 * The PaymentObserver interface should be implemented by any class that needs to be notified of new payments.
 */
public interface PaymentObserver {

    /**
     * This method is called when a new payment is made.
     *
     * @param payment The amount of the new payment.
     */
    void newPayment(double payment);
}
