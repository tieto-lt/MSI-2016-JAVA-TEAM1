package lt.tieto.msi2016.order.model;

import lt.tieto.msi2016.mission.model.MissionImage;
import lt.tieto.msi2016.mission.model.MissionNavigationData;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.List;

public class OrderResults {

    private Long id;
    private String missionId;
    private Long orderId;
    private MissionNavigationData startNavigationData;
    private MissionNavigationData finishNavigationData;
    private List<MissionImage> images;
    private BigDecimal batteryStatus;
    private String missionName;
    private DateTime executionDate;
    private String videoBase64;

    public String getVideoBase64() {
        return videoBase64;
    }

    public void setVideoBase64(String videoBase64) {
        this.videoBase64 = videoBase64;
    }

    public DateTime getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(DateTime executionDate) {
        this.executionDate = executionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public MissionNavigationData getStartNavigationData() {
        return startNavigationData;
    }

    public void setStartNavigationData(MissionNavigationData startNavigationData) {
        this.startNavigationData = startNavigationData;
    }

    public MissionNavigationData getFinishNavigationData() {
        return finishNavigationData;
    }

    public void setFinishNavigationData(MissionNavigationData finishNavigationData) {
        this.finishNavigationData = finishNavigationData;
    }

    public List<MissionImage> getImages() {
        return images;
    }

    public void setImages(List<MissionImage> images) {
        this.images = images;
    }

    public BigDecimal getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(BigDecimal batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }
}
