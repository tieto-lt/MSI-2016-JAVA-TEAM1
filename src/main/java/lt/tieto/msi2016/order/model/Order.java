package lt.tieto.msi2016.order.model;

import lt.tieto.msi2016.mission.controller.MissionsHolder;
import lt.tieto.msi2016.mission.model.MissionCommand;
import lt.tieto.msi2016.order.repository.model.OrderDb;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.List;

public class Order {

    private Long id;
    private String missionName;
    private String fullName;
    private String email;
    private String phone;
    private String details;
    private OrderDb.Status status;
    private DateTime submissionDate;
    private List<MissionCommand> missionCommands;
    private List<MapItems> mapItems;
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MapItems> getMapItems() {
        return mapItems;
    }

    public void setMapItems(List<MapItems> mapItems) {
        this.mapItems = mapItems;
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

    public OrderDb.Status getStatus() {
        return status;
    }

    public void setStatus(OrderDb.Status status) {
        this.status = status;
    }

    public DateTime getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(DateTime submissionDate) {
        this.submissionDate = submissionDate;
    }

    public List<MissionCommand> getMissionCommands() {
        return missionCommands;
    }

    public void setMissionCommands(List<MissionCommand> missionCommands) {
        this.missionCommands = missionCommands;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                .append("status", status.toString())
                .append("submissionDate", DateTimeFormat.forPattern("yyyy-MM-dd").print(submissionDate))
                .append("username", username)
                .toString();
    }
}
