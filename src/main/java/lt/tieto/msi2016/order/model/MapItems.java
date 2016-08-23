package lt.tieto.msi2016.order.model;

/**
 * Created by it11 on 16.8.22.
 */
public class MapItems {

    private String name;
    private CameraPosition cameraPosition;

    public enum CameraPosition{
        FRONT, BOTTOM
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CameraPosition getCameraPosition() {
        return cameraPosition;
    }

    public void setCameraPosition(CameraPosition cameraPosition) {
        this.cameraPosition = cameraPosition;
    }
}
