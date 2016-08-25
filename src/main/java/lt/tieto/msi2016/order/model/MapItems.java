package lt.tieto.msi2016.order.model;

import java.math.BigDecimal;

/**
 * Created by it11 on 16.8.22.
 */
public class MapItems {

    private ObjectName name;
    private CameraPosition cameraPosition;

    public enum CameraPosition{
        FRONT, BOTTOM
    }

    public enum ObjectName{
        HOUSE, LAKE, CASTLE, GARDEN
    }

    public ObjectName getName() {
        return name;
    }

    public void setName(ObjectName name) {
        this.name = name;
    }

    public CameraPosition getCameraPosition() {
        return cameraPosition;
    }

    public void setCameraPosition(CameraPosition cameraPosition) {
        this.cameraPosition = cameraPosition;
    }
}
