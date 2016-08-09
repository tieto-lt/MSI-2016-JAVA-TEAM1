package lt.tieto.msi2016.mission.model;


import java.util.List;

public class MissionResult {

    public String missionId;
    public List<MissionNavigationData> navigationData;
    public List<MissionImage> images;
    public String videoBase64;

    public String getMissionId() {
        return missionId;
    }

    public void setMissionId(String missionId) {
        this.missionId = missionId;
    }

    public List<MissionNavigationData> getNavigationData() {
        return navigationData;
    }

    public void setNavigationData(List<MissionNavigationData> navigationData) {
        this.navigationData = navigationData;
    }

    public List<MissionImage> getImages() {
        return images;
    }

    public void setImages(List<MissionImage> images) {
        this.images = images;
    }

    public String getVideoBase64() {
        return videoBase64;
    }

    public void setVideoBase64(String videoBase64) {
        this.videoBase64 = videoBase64;
    }
}
