package powerplant.controller;

import com.datastax.driver.core.utils.UUIDs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import powerplant.model.Solar;
import powerplant.repository.SolarRepository;

import java.util.Collections;
import java.util.List;

@RestController
public class SolarController {

    @Autowired
    SolarRepository solarRepository;

    @GetMapping("/")
    public boolean saveSolar(){
        Solar solar = new Solar();
        solar.setId(UUIDs.timeBased());
        solar.setTitle("Solar 1");
        try {


        solarRepository.save(solar);
        return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }

    @GetMapping("/solar")
    public ResponseEntity<List<Solar>> getSolarInfo(){
        try{
            List<Solar> solars =   solarRepository.findAll();
            return new ResponseEntity<>(solars, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(Collections.EMPTY_LIST,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
