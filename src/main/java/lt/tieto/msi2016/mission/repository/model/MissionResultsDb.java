package lt.tieto.msi2016.mission.repository.model;

import lt.tieto.msi2016.utils.repository.model.DbModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created by it11 on 16.8.9.
 */



public class MissionResultsDb extends DbModel {

    private Long missionId;
    private Long orderId;
    private Long executedBy;
    private DateTime executionDate;
    private BigDecimal batteryStatus;
    private String videoBase64;
    private String images;
    private String navigationData;
    private MissionState missionState;
    private String missionName;


    public enum MissionState {
        Initialized, InProgress, Completed, Error
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
    }

    public Long getMissionId() {
        return missionId;
    }

    public void setExecutedBy(Long executedBy) {
        this.executedBy = executedBy;
    }

    public Long getExecutedBy() {
        return executedBy;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public DateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(DateTime executionDate) {
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


    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("missionId", missionId)
                .append("orderId", orderId)
                .append("executedBy", executedBy)
                .append("executionDate", executionDate)
                .append("batteryStatus", batteryStatus)
                .append("videoBase64", videoBase64)
                .append("images", images)
                .append("navigationData", navigationData)
                .append("state", missionState)
                .append("missionName", missionName)
                .toString();
    }
}