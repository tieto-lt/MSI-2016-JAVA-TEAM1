package lt.tieto.msi2016.order.repository.model;

import lt.tieto.msi2016.utils.repository.model.DbModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;



public class OrdersDb extends DbModel {

    private Long createdBy;
    private String missionName;
    private String fullName;
    private String phone;
    private String email;
    private DateTime submissionDate;
    private String details;
    private OrderState orderState;

    public enum OrderState {
        Accepted, Declined, Pending, InProgress, Completed, Error
    }


    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(DateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("createdBy", createdBy)
                .append("missionName", missionName)
                .append("fullName", fullName)
                .append("phone", phone)
                .append("email", email)
                .append("submissionDate", submissionDate)
                .append("details", details)
                .append("orderState", orderState)
                .toString();
    }
}