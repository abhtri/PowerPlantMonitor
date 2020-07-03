package powerplant.controller;

import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import powerplant.repository.WindRepository;
import powerplant.service.TestService;

import java.util.List;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    TestService testService;
    @Autowired
    WindRepository windRepository;
    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @GetMapping("/")
    public Map<String, List> getData(){
                 return  windRepository.getWind();
                // return false;
    }
}
