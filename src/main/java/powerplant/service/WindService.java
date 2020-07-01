package powerplant.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import powerplant.model.WindPower;
import powerplant.repository.WindPowerRepository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class WindService {

    @Autowired
    WindPowerRepository windPowerRepository;

    public List<WindPower> insertWinddata(){
        try{

            BufferedReader br = new BufferedReader(new FileReader("F:\\Idea Workspace\\powerplant\\src\\main\\resources\\T1.csv"));
            String line = br.readLine();
            List<WindPower> windPowers = new ArrayList<>();
        while ((line = br.readLine()) != null)   //returns a Boolean value
        {
            String[] windLine = line.split(",");    // use comma as separator
            if(windLine.length==5){
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(windLine[0],formatter);
                Date date =  Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
                WindPower windPower = new WindPower(date,Double.parseDouble(windLine[1]),Double.parseDouble(windLine[2]),Double.parseDouble(windLine[3]),Double.parseDouble(windLine[4]));
                //System.out.println(windPower.toString());
                windPowers.add(windPower);
            }

        }
        windPowerRepository.saveAll(windPowers);
        return windPowers;
        } catch (IOException e){
            e.printStackTrace();
        }
        return Collections.EMPTY_LIST;
    }
}
