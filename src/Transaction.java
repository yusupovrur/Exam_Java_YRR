import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
    private Card wallet;

    private double amount;

    public Transaction(Card wallet, double amount) {
        this.wallet = wallet;
        this.amount = amount;
    }
}
