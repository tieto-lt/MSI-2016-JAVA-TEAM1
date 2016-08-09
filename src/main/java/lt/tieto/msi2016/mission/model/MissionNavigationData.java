package lt.tieto.msi2016.mission.model;


import java.math.BigDecimal;

public class MissionNavigationData {

    public BigDecimal x;
    public BigDecimal y;
    public BigDecimal z;
    public BigDecimal altitude;
    public BigDecimal altitudeMeters;

    public BigDecimal getX() {
        return x;
    }

    public void setX(BigDecimal x) {
        this.x = x;
    }

    public BigDecimal getY() {
        return y;
    }

    public void setY(BigDecimal y) {
        this.y = y;
    }

    public BigDecimal getZ() {
        return z;
    }

    public void setZ(BigDecimal z) {
        this.z = z;
    }

    public BigDecimal getAltitude() {
        return altitude;
    }

    public void setAltitude(BigDecimal altitude) {
        this.altitude = altitude;
    }

    public BigDecimal getAltitudeMeters() {
        return altitudeMeters;
    }

    public void setAltitudeMeters(BigDecimal altitudeMeters) {
        this.altitudeMeters = altitudeMeters;
    }
}