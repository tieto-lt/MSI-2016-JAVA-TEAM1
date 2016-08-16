package lt.tieto.msi2016.order.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Order {

    private String missionName;
    private String fullName;
    private String email;
    private String phone;
    private String details;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("missionName", missionName)
                .append("fullName", fullName)
                .append("email", email)
                .append("phone", phone)
                .append("details", details)
                .toString();
    }
}
