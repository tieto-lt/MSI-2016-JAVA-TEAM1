package lt.tieto.msi2016.transaction.model;

import java.math.BigDecimal;

public class UserBalance {

    private BigDecimal balance;
    private Long userId;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
