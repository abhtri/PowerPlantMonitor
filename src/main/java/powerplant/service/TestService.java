package powerplant.service;

import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class TestService {

    @Autowired
    InfluxDBTemplate<Point> influxDBTemplate;

    public boolean putdata(){
        // Create database...
        influxDBTemplate.createDatabase();

        // Create some data...
        try {
            final Point p1 = Point.measurement("cpu")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .tag("tenant", "default")
                    .addField("idle", 90L)
                    .addField("user", 9L)
                    .addField("system", 1L)
                    .build();
            influxDBTemplate.write(p1);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
