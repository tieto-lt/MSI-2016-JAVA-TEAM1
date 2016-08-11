package lt.tieto.msi2016.mission.repository.model;

import lt.tieto.msi2016.utils.repository.model.DbModel;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.math.BigDecimal;

public class MissionResultsDb extends DbModel {

    private Long id;
    private Long missionId;
    private Long orderId;
    private Long executedBy;
    private String executionDate;
    private BigDecimal batteryStatus;
    private String videoBase64;
    private String images;
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

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getExecutedBy() {
        return executedBy;
    }

    public void setExecutedBy(Long executedBy) {
        this.executedBy = executedBy;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public BigDecimal getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(BigDecimal batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public String getVideoBase64() {
        return videoBase64;
    }

    public void setVideoBase64(String videoBase64) {
        this.videoBase64 = videoBase64;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("missionId", missionId)
                .append("orderId", orderId)
                .append("executedBy", executedBy)
                .append("executionDate", executionDate)
                .append("batteryStatus", batteryStatus)
                .append("videoBase64", videoBase64)
                .append("images", images)
                .append("navigationData", navigationData)
                .append("state", missionState)
                .toString();
    }
}