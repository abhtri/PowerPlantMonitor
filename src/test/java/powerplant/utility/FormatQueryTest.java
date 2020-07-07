package powerplant.utility;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FormatQueryTest {
    @Test
    public void test1(){
        String query = "Select * from wind where time >= :startTime and time <= :endTime;";
        String startDate = "2020-01-02";

        QueryFormatter formatQuery = new QueryFormatter();
        Map<String, String> map = new HashMap<>();
        map.put("startTime",startDate);
        map.put("endTime",startDate);
        String output = formatQuery.formatQuery(query,map);
        assertEquals (output , "Select * from wind where time >= 2020-01-02 and time <= 2020-01-02;");
    }
}
