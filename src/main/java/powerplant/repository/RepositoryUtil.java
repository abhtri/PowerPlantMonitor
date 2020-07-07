package powerplant.repository;

import org.influxdb.annotation.Column;
import org.influxdb.dto.QueryResult;
import powerplant.model.WindEntity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.*;
import java.util.stream.Stream;

public class RepositoryUtil {
    public static List<WindEntity> getEntityList(QueryResult.Series series){
        List<WindEntity> windEntityList = new ArrayList<>();
        //WindEntity windEntity = new WindEntity();

        Map<String, Integer> map = new HashMap<>();
        Map<String,String> fieldAnnotationMap = new HashMap<>();
        for(Field field : WindEntity.class.getDeclaredFields()){
            Column column  = field.getAnnotation(Column.class);
            field.getName();
            if(column!=null){
                String columnName = column.name();
                List<Object> list = null;
                Optional<Integer> index = getColumnIndexFromSeries(series, columnName);
                if(index.isPresent()) {
                    map.put(columnName, index.get());
                }
                fieldAnnotationMap.put(field.getName(),columnName);
            }
        }

        series.getValues().stream().forEach(row->{

            WindEntity windEntity = new WindEntity();
            for(String fieldName : fieldAnnotationMap.keySet()) {
                try {
                    Field field = WindEntity.class.getDeclaredField(fieldName);

                    field.setAccessible(true);
                    String columnName = fieldAnnotationMap.get(fieldName);
                    Integer i = map.get(columnName);
                    Class value = field.getType();
                    String methodName = getSetterFieldName(fieldName);
                    if(fieldName.equals("time")){
                        Instant instant = Instant.parse(row.get(i).toString());
                        windEntity.setTime(instant);
                    }else {
                        Method method = WindEntity.class.getDeclaredMethod(methodName, value);
                        method.invoke(windEntity, row.get(i));
                    }

                }catch (Exception e ){
                    e.printStackTrace();
                }
            }
            windEntityList.add(windEntity);
        });

        return windEntityList;
    }

    public static Optional<Integer> getColumnIndexFromSeries(QueryResult.Series series, String columnName) {
        return Optional.of(series.getColumns().indexOf(columnName))
                .filter(idx -> idx >= 0);
    }

    public static Optional<Stream<Object>> getColumnFromSeries(QueryResult.Series series, String columnName) {
        return getColumnIndexFromSeries(series, columnName)
                .map(idx -> {
                    return series.getValues().stream()
                            .map(row -> row.get(idx));
                });
    }

    public static String getSetterFieldName(String fieldName){
        return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(
                1, fieldName.length());
    }
}
