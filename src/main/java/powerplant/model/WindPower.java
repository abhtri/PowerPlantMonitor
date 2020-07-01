package powerplant.model;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;

@Table
public class WindPower {

    @PrimaryKeyColumn(name = "id", type = PrimaryKeyType.PARTITIONED)
    private Date eventTime;
    @Column
    private Double activePower;
    @Column
    private Double theoreticalPower;
    @Column
    private Double windSpeed;
    @Column
    private Double windDirection;
    public  WindPower(){

    }

    public WindPower(Date eventTime, Double activePower, Double theoreticalPower, Double windSpeed, Double windDirection) {
        this.eventTime = eventTime;
        this.activePower = activePower;
        this.theoreticalPower = theoreticalPower;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public Date getEventTime() {
        return eventTime;
    }

    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    public Double getActivePower() {
        return activePower;
    }

    public void setActivePower(Double activePower) {
        this.activePower = activePower;
    }

    public Double getTheoreticalPower() {
        return theoreticalPower;
    }

    public void setTheoreticalPower(Double theoreticalPower) {
        this.theoreticalPower = theoreticalPower;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Double windDirection) {
        this.windDirection = windDirection;
    }

    @Override
    public String toString() {
        return "WindPower{" +
                "eventTime=" + eventTime +
                ", activePower=" + activePower +
                ", theoreticalPower=" + theoreticalPower +
                ", windSpeed=" + windSpeed +
                ", windDirection=" + windDirection +
                '}';
    }
}
