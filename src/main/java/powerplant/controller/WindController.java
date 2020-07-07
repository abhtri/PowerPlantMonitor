package powerplant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import powerplant.model.WindEntity;
import powerplant.repository.WindRepository;

import java.util.Collections;
import java.util.List;

@RestController
public class WindController {

    @Autowired
    WindRepository windRepository;

    @GetMapping("/wind/{userid}/")
    public List<WindEntity> getWindbetweenTime(@RequestParam("startTime") String startTime,@RequestParam("endTime") String endTime){
        return windRepository.getWindList(startTime,endTime);
        //return Collections.EMPTY_LIST;

    }


    // Instant
    @GetMapping("/wind/instance/{userid}")
    public List<WindEntity> getWindInstance(){
        return Collections.EMPTY_LIST;
    }

    @GetMapping("/wind/aggregate/{userid}")
    public List<WindEntity> getWindInstance(@RequestParam("startDate") String startDate){
        return Collections.EMPTY_LIST;
    }
}
