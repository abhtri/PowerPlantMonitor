package powerplant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import powerplant.model.Solar;
import powerplant.model.WindPower;
import powerplant.repository.WindPowerRepository;
import powerplant.service.WindService;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
public class WindController {

    @Autowired
    WindPowerRepository windPowerRepository;
    @Autowired
    WindService windService;

    @GetMapping("/wind/insert")
    public ResponseEntity<List<WindPower>> insertDataFromCSV(){
        try {
            List<WindPower> windPowers = windService.insertWinddata();
            return new ResponseEntity<>(windPowers, HttpStatus.OK);
        }catch (Exception e ){
            e.printStackTrace();
            return new ResponseEntity<>(Collections.EMPTY_LIST,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/wind/power")
    public ResponseEntity<List<WindPower>> getSolarInfo(){
        try{
            List<WindPower> windPowers =   windPowerRepository.findAll();

            return new ResponseEntity<>(windPowers, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Collections.EMPTY_LIST,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
