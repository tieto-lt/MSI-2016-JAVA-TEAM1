package lt.tieto.msi2016.mission.model;

import lt.tieto.msi2016.mission.model.operator.*;

import java.util.List;

public class MissionResultInternal {

    public Long missionId;
    public MissionNavigationData startNavigationData;
    public MissionNavigationData finishNavigationData;
    public List<MissionImage> images;
    public Integer batteryStatus;

    public Long getMissionId() {
        return missionId;
    }

    public void setMissionId(Long missionId) {
        this.missionId = missionId;
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

    public Integer getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(Integer batteryStatus) {
        this.batteryStatus = batteryStatus;
    }
}
