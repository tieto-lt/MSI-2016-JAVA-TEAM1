package lt.tieto.msi2016.order.repository.model;

import lt.tieto.msi2016.mission.model.MissionCommand;
import lt.tieto.msi2016.utils.repository.model.DbModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.List;


public class OrderDb extends DbModel {

    private Long submittedBy;
    private String missionId;
    private DateTime submissionDate;
    private String details;
    private Status status;
    private String commands;
    private String fullName;
    private String email;
    private String phone;


    public enum Status {
        Pending, Accepted, Declined, InProgress, Executed, Failed, Completed
    }

    public Long getSubmittedBy() {
        return submittedBy;
    }

    public void setSubmittedBy(Long submittedBy) {
        this.submittedBy = submittedBy;
    }

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("submittedBy", submittedBy)
                .append("missionId", missionId)
                .append("submissionDate", DateTimeFormat.forPattern("yyyy-MM-dd").print(submissionDate))
                .append("details", details)
                .append("status", status.toString())
                .append("commands", commands)
                .append("fullName", fullName)
                .append("email", email)
                .append("phone", phone)
                .toString();
    }
}