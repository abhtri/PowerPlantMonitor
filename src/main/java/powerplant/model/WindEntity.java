package powerplant.model;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Measurement(name = "wind", timeUnit = TimeUnit.MILLISECONDS)
public class WindEntity {
    @Column(name = "time")
    private Instant time;

    @Column(name = "ActivePower")
    private Double activePower;

    @Column(name = "Theoretical_Power_Curve")
    private Double theoreticalPowerCurve;

    @Column(name = "Wind Direction")
    private Double windDirection;

    @Column(name = "Wind_Speed")
    private Double windSpeed;

    @Column(name = "windMillId")
    private String  windMillId;

    @Column(name = "WindPlantId")
    private String  WindPlantId;

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Double getActivePower() {
        return activePower;
    }

    public void setActivePower(Double activePower) {
        this.activePower = activePower;
    }

    public Double getTheoreticalPowerCurve() {
        return theoreticalPowerCurve;
    }

    public void setTheoreticalPowerCurve(Double theoreticalPowerCurve) {
        this.theoreticalPowerCurve = theoreticalPowerCurve;
    }

    public Double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Double windDirection) {
        this.windDirection = windDirection;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getWindMillId() {
        return windMillId;
    }

    public void setWindMillId(String windMillId) {
        this.windMillId = windMillId;
    }

    public String getWindPlantId() {
        return WindPlantId;
    }

    public void setWindPlantId(String windPlantId) {
        WindPlantId = windPlantId;
    }
}
