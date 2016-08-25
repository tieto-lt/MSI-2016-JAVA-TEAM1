package lt.tieto.msi2016.transaction.model;

import java.math.BigDecimal;


public class Transaction {
    private Long id;
    private Long userId;
    private BigDecimal transaction;

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

    public BigDecimal getTransaction() {
        return transaction;
    }

    public void setTransaction(BigDecimal transaction) {
        this.transaction = transaction;
    }
}
