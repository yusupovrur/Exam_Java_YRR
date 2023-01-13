import java.io.Serializable;
import java.util.ArrayList;

public class DebtCard extends Card implements Serializable {

    public DebtCard(String cardHolder, int cardNumber, double balance) {
        this.cardHolder = cardHolder;
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.transactions=new ArrayList<>();
    }



    public void transferMoney(Card other, double amount) {
        other.balance += amount;
        this.balance-=amount;
        this.transactions.add(new Transaction(this, amount));

    }
    @Override
    public void spendMoney(double amount) {
        this.balance=balance-amount;
    }
}
