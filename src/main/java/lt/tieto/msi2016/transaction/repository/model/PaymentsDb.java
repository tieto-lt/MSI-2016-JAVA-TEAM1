package lt.tieto.msi2016.transaction.repository.model;

import lt.tieto.msi2016.utils.repository.model.DbModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.format.DateTimeFormat;

import java.math.BigDecimal;


public class PaymentsDb extends DbModel {
    private String payseraOrderId;
    private Long userId;
    private BigDecimal amount;
    private Status status;

    public enum Status {
        Pending, Accepted, Cancelled
    }

    public String getPayseraOrderId() {
        return payseraOrderId;
    }

    public void setPayseraOrderId(String payseraOrderId) {
        this.payseraOrderId = payseraOrderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("payseraOrderId", payseraOrderId)
                .append("userId", userId)
                .append("amount", amount)
                .append("status", status.toString())
                .toString();
    }



}
