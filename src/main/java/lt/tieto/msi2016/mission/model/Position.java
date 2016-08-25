package lt.tieto.msi2016.mission.model;

import java.math.BigDecimal;


public class Position {
    private BigDecimal x;
    private BigDecimal y;
    private BigDecimal z;
    private BigDecimal yaw;

    public Position(BigDecimal x, BigDecimal y, BigDecimal z, BigDecimal yaw){
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
    }

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

    public BigDecimal getYaw() {
        return yaw;
    }

    public void setYaw(BigDecimal yaw) {
        this.yaw = yaw;
    }
}
