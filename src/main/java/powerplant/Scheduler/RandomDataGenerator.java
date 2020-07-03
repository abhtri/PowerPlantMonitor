package powerplant.Scheduler;

import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RandomDataGenerator {
    @Autowired
    InfluxDBTemplate<Point> influxDBTemplate;

    @Scheduled(fixedRate = 1000*60)
    public void putWindData(){
        influxDBTemplate.createDatabase();

        double winddirection = Math.random()*360;
        double windspeed = Math.random()*50;
        double theoreticalPowerCurve =0.0;
        double activePower = 0.0d;
        if(windspeed <5){
            theoreticalPowerCurve = 0.5d;
            activePower = 0.2d;
        }else if(windspeed >=5 && windspeed <= 15 ){
            theoreticalPowerCurve = 4.0d;
            activePower = 3.6d;
        }else{
            theoreticalPowerCurve = windspeed*50;
            activePower = windspeed*48;
        }


        try {
            final Point p1 = Point.measurement("wind")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .tag("windMillId", "WM1")
                    .tag("WindPlantId", "WP1")
                    .addField("ActivePower", activePower)
                    .addField("Wind_Speed", windspeed)
                    .addField("Theoretical_Power_Curve", theoreticalPowerCurve)
                    .addField("Wind Direction", winddirection)
                    .build();
            influxDBTemplate.write(p1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
