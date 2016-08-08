package lt.tieto.msi2016.operator.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class OperatorModel {

    private Long operatorID;
    private String token;
    private Status status;

    public enum Status{VERIFIED, NOTVERIFIED, TOKENISSUE}

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getOperatorStatus() {
        return status;
    }

    public void setOperatorID(Long operatorID) {
        this.operatorID = operatorID;
    }

    public Long getOperatorID() {
        return operatorID;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("operatorID", operatorID)
                .append("token", token)
                .append("status", status)
                .toString();
    }
}
