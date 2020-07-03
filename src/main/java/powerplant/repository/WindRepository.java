package powerplant.repository;

import org.influxdb.annotation.Column;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Repository;
import powerplant.model.WindEntity;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static powerplant.utility.QueryConstants.WINDQUERY;

@Repository
public class WindRepository {

    @Autowired
    InfluxDBTemplate influxDBTemplate;

    public Map<String, List> getWind(){
        influxDBTemplate.createDatabase();
        final Query q = new Query(WINDQUERY, influxDBTemplate.getDatabase());
        List<QueryResult.Result> results = new ArrayList<>();
        QueryResult queryResult = influxDBTemplate.query(q);
        results = queryResult.getResults();
        Map<String, List> map = null;
        for(QueryResult.Result result: results){
            if(!result.hasError()){
                List<QueryResult.Series> series = result.getSeries();
                for (QueryResult.Series s:series){
                   map = formEntityfromSeries(s);
                }
            }
        }
        return map;
       /* influxDBTemplate.query(q,2,queryResult -> {
           results.addAll(((QueryResult) queryResult).getResults());
            for(QueryResult.Result result: results){
                result.getSeries().get(0).getValues();
                Optional<Stream<Object>> s =  getColumnFromSeries(result.getSeries().get(0),"ActivePower");
                if(s.isPresent()){
                    Stream stream = s.get();
                    stream.forEach(i->System.out.println(i));
                }
            }
        });*/

    }

    private Map<String, List> formEntityfromSeries(QueryResult.Series series){
        /*WindEntity windEntity = new WindEntity();
        for(Field field : WindEntity.class.getDeclaredFields()){
            Column column  = field.getAnnotation(Column.class);
            if(column!=null){
                getColumnFromSeries(s,column.name());
            }

        }*/

        List<String> columnNames= series.getColumns();
        Map<String , List> map = new HashMap<>();
        for(String columnName: columnNames){
            Optional<Stream<Object>> s =  getColumnFromSeries(series,columnName);
            if(s.isPresent()){
                List<Object> list = s.get().collect(Collectors.toList());
                map.put(columnName,list);
            }
        }
        return map;
    }

    protected static Optional<Integer> getColumnIndexFromSeries(QueryResult.Series series, String columnName) {
        return Optional.of(series.getColumns().indexOf(columnName))
                .filter(idx -> idx >= 0);
    }

    protected static Optional<Stream<Object>> getColumnFromSeries(QueryResult.Series series, String columnName) {
        return getColumnIndexFromSeries(series, columnName)
                .map(idx -> {
                    return series.getValues().stream()
                            .map(row -> row.get(idx));
                });
    }
}
