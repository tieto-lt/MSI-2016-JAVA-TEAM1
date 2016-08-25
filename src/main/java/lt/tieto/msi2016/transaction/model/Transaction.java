package lt.tieto.msi2016.transaction.model;

/**
 * Created by it11 on 16.8.25.
 */
public class Transaction {
    private Long id;
    private Long userId;
    private float transaction;
    private float balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getTransaction() {
        return transaction;
    }

    public void setTransaction(float transaction) {
        this.transaction = transaction;
    }
}
