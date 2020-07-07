package powerplant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import powerplant.service.TestService;

@SpringBootApplication
//@EnableScheduling
public class Application {

    public static void main(String args[]){
        SpringApplication.run(Application.class);
    }
}
