package lt.tieto.msi2016.mission.model;

import lt.tieto.msi2016.mission.model.operator.MissionImage;
import lt.tieto.msi2016.mission.model.operator.MissionNavigationData;

import java.math.BigDecimal;
import java.util.List;

public class MissionResultUI {

    private Long id;
    private Long missionId;
    private Long orderId;
    private MissionNavigationData startNavigationData;
    private MissionNavigationData finishNavigationData;
    private List<MissionImage> images;
    private BigDecimal batteryStatus;
    private String missionName;

    public Long getId() {
        return id;
    }

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
