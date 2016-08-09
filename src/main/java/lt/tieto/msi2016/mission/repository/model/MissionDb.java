package lt.tieto.msi2016.mission.repository.model;

import lt.tieto.msi2016.utils.repository.model.DbModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by it11 on 16.8.9.
 */



public class MissionDb extends DbModel {

    private Long id;
    private Long submitedBy;
    private Long executedBy;
    private String submmissionDate;
    private String executionDate;
    private String videoBase64;
    private String image;
    private String navigationData;
    private MissionState missionState;


    public enum MissionState {
        Initialized, InProgress, Completed, Error
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubmitedBy() {
        return submitedBy;
    }

    public void setSubmitedBy(Long submitedBy) {
        this.submitedBy = submitedBy;
    }

    public Long getExecutedBy() {
        return executedBy;
    }

    public void setExecutedBy(Long executedBy) {
        this.executedBy = executedBy;
    }

    public String getSubmmissionDate() {
        return submmissionDate;
    }

    public void setSubmmissionDate(String submmissionDate) {
        this.submmissionDate = submmissionDate;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getVideoBase64() {
        return videoBase64;
    }

    public void setVideoBase64(String videoBase64) {
        this.videoBase64 = videoBase64;
    }

    public String getNavigationData() {
        return navigationData;
    }

    public void setNavigationData(String navigationData) {
        this.navigationData = navigationData;
    }

    public MissionState getMissionState() {
        return missionState;
    }

    public void setMissionState(MissionState missionState) {
        this.missionState = missionState;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("submitedBy", submitedBy)
                .append("executedBy", executedBy)
                .append("submmissionDate", submmissionDate)
                .append("executionDate", executionDate)
                .append("videoBase64", videoBase64)
                .append("image", image)
                .append("navigationData", navigationData)
                .append("state", missionState)
                .toString();
    }
}