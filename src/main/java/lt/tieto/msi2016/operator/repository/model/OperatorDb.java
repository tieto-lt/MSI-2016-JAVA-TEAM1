package lt.tieto.msi2016.operator.repository.model;

import lt.tieto.msi2016.operator.OperatorStatus;
import lt.tieto.msi2016.utils.repository.model.DbModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class OperatorDb extends DbModel {

    private Long id;
    private String token;
    private Long userId;
    private OperatorStatus.Status status;

    @Override
    public boolean isNew() {
        return true;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public OperatorStatus.Status getStatus() {
        return status;
    }

    public void setStatus(OperatorStatus.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("token", token)
                .append("userId", userId)
                .append("status", status)
                .toString();
    }
}