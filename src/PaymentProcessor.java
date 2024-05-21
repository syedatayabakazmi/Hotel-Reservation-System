public class PaymentProcessor {

    public boolean processPayment(String creditCardNumber, double amount) {
     
        System.out.println("Processing payment of $" + amount + " for card " + creditCardNumber);
        return true;
    }
}
