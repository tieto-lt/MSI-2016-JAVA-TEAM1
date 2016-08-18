package lt.tieto.msi2016.order.model;

import lt.tieto.msi2016.order.repository.model.OrderDb;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

public class Order {

    private Long id;
    private String missionName;
    private String fullName;
    private String email;
    private String phone;
    private String details;
    private OrderDb.OrderStatus orderStatus;
    private DateTime submissionDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public OrderDb.OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderDb.OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public DateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(DateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("missionName", missionName)
                .append("fullName", fullName)
                .append("email", email)
                .append("phone", phone)
                .append("details", details)
                .append("orderStatus", orderStatus.toString())
                .append("submissionDate", DateTimeFormat.forPattern("yyyy-MM-dd").print(submissionDate))
                .toString();
    }
}
