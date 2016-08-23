package lt.tieto.msi2016.order.repository.model;

import lt.tieto.msi2016.utils.repository.model.DbModel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.math.BigDecimal;

/**
 * Created by it11 on 16.8.9.
 */



public class OrderResultsDb extends DbModel {

    private Long executedBy;
    private Long orderId;
    private String missionName;
    private DateTime executionDate;
    private BigDecimal batteryStatus;
    private String images;
    private String videoBase64;
    private String navigationData;


    public Long getExecutedBy() {
        return executedBy;
    }

    public void setExecutedBy(Long executedBy) {
        this.executedBy = executedBy;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("executedBy", executedBy)
                .append("orderId", orderId)
                .append("missionName", missionName)
                .append("executionDate", DateTimeFormat.forPattern("yyyy-MM-dd").print(executionDate))
                .append("batteryStatus", batteryStatus)
                .append("images", images)
                .append("video_Base64", videoBase64)
                .append("navigationData", navigationData)
                .toString();
    }
}