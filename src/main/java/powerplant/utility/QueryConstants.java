package powerplant.utility;

public interface QueryConstants {
     static String WINDQUERY = "Select * from wind;";
     static String WINDDAYQUERY = "Select * from wind where time >= :startTime and time <= :endTime ;";

}
