import java.io.Serializable;
import java.util.List;

public abstract class Card implements Serializable {
    protected String cardHolder;
    protected int cardNumber;
    protected double balance;
    protected double amount;

    protected transient List<Transaction> transactions;



    public abstract void spendMoney (double amount);


    public void setAmount(double amount) {
        this.amount = amount;
    }


    public void transferMoney(Card other, double amount) {
    }
}
