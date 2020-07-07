package powerplant.repository;

import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Repository;
import powerplant.model.WindEntity;
import powerplant.utility.QueryFormatter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static powerplant.repository.RepositoryUtil.getColumnFromSeries;
import static powerplant.repository.RepositoryUtil.getEntityList;
import static powerplant.utility.QueryConstants.WINDDAYQUERY;
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
                   map = getMapfromSeries(s);
                }
            }
        }
        return map;
    }

    public List<WindEntity> getWindList(){
        influxDBTemplate.createDatabase();
        final Query q = new Query(WINDQUERY, influxDBTemplate.getDatabase());
        List<QueryResult.Result> results = new ArrayList<>();
        QueryResult queryResult = influxDBTemplate.query(q);
        results = queryResult.getResults();
        List<WindEntity> windEntityList = null;
        for(QueryResult.Result result: results){
            if(!result.hasError()){
                List<QueryResult.Series> series = result.getSeries();
                for (QueryResult.Series s:series){
                    windEntityList  = getEntityList(s);
                }
            }
        }
        return windEntityList;
    }

    private Map<String, List> getMapfromSeries(QueryResult.Series series){
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

    public List getWindList(String startTime, String endTime){
        influxDBTemplate.createDatabase();
        Map<String, String> map = new HashMap<>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        String influxQuery = new QueryFormatter().formatQuery(WINDDAYQUERY,map);
        final Query q = new Query(influxQuery, influxDBTemplate.getDatabase());
        List<QueryResult.Result> results = new ArrayList<>();
        QueryResult queryResult = influxDBTemplate.query(q);
        results = queryResult.getResults();
        List<WindEntity> windEntityList = null;
        for(QueryResult.Result result: results){
            if(!result.hasError()){
                List<QueryResult.Series> series = result.getSeries();
                for (QueryResult.Series s:series){
                    windEntityList  = getEntityList(s);
                }
            }
        }
        return windEntityList;
    }
}
