package net.suncaper.sparkairline.service.ServiceImpl;

import net.suncaper.sparkairline.entity.Flights;
import net.suncaper.sparkairline.service.FlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class FlightsServiceImpl implements FlightsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getFlightsOneWayByPrice(String departureCityName, String arrivalCityName,String departureTime) {
        String sql="select airlineName,departure_airportname,flightNumber,departure_time,departure_terminal,arrival_airportname,arrival_terminal,arrival_time,stop_cityname,price from d20200616 where" +
                " departure_cityname='"+departureCityName+"' and "+
                "arrival_cityname='"+arrivalCityName+
                "' and departure_time like '"+departureTime+
                "%' order by price";
        System.out.println("sql="+sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        System.out.println(flights);
        for( int i = 0; i < flights.size(); i++ ) {
            System.out.println( flights.get(i) );
        }
        return flights;
    }

    @Override
    public List<Map<String, Object>> getFlightsOneWayByDuringTime(String departureCityName, String arrivalCityName,String departureTime) {
        String sql="select airlineName,departure_airportname,flightNumber,departure_time,departure_terminal,arrival_airportname,arrival_terminal,arrival_time,stop_cityname,price from d20200616 where" +
                " departure_cityname='"+departureCityName+"' and "+
                "arrival_cityname='"+arrivalCityName+
                "' and departure_time like '"+departureTime+
                "%' order by duration";
        System.out.println("sql="+sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        System.out.println(flights);
        for( int i = 0; i < flights.size(); i++ ) {
            System.out.println( flights.get(i) );
        }
        return flights;
    }

    @Override
    public List<Map<String, Object>> getFlightsOneWayByDepartureTime(String departureCityName, String arrivalCityName,String departureTime) {
        String sql = "select airlineName,departure_airportname,flightNumber,departure_time,departure_terminal,arrival_airportname,arrival_terminal,arrival_time,stop_cityname,price from d20200616 where" +
                " departure_cityname='" + departureCityName + "' and " +
                "arrival_cityname='" + arrivalCityName +
                "' and departure_time like '" + departureTime +
                "%' order by departure_time";
        System.out.println("sql=" + sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        System.out.println(flights);
        for (int i = 0; i < flights.size(); i++) {
            System.out.println(flights.get(i));
        }
        return flights;
    }

    @Override
    public List<Map<String, Object>> getFlightsOneWayByArrivalTime(String departureCityName, String arrivalCityName,String departureTime) {
        String sql = "select airlineName,departure_airportname,flightNumber,departure_time,departure_terminal,arrival_airportname,arrival_terminal,arrival_time,stop_cityname,price from d20200616 where" +
                " departure_cityname='" + departureCityName + "' and " +
                "arrival_cityname='" + arrivalCityName +
                "' and departure_time like '" + departureTime +
                "%' order by arrival_time";
        System.out.println("sql=" + sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        System.out.println(flights);
        for (int i = 0; i < flights.size(); i++) {
            System.out.println(flights.get(i));
        }
        return flights;
    }

    @Override
    public List<Map<String, Object>> whenToFlightViewYear(String departureCityName, String arrivalCityName, String year) {
        String sql = "select month(departure_time) as month, min(price) as price from d20200616 where" +
                " departure_cityname='" + departureCityName + "' and " +
                "arrival_cityname='" + arrivalCityName +
                "' and year(departure_time)= '"+year+
                "' group by month(departure_time)"+
                " order by month(departure_time)";
        System.out.println("sql=" + sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        System.out.println(flights);
        for (int i = 0; i < flights.size(); i++) {
            System.out.println(flights.get(i));
        }
        return flights;
    }

    @Override
    public List<Map<String, Object>> whenToFlightViewMonth(String departureCityName, String arrivalCityName, String year, String month) {
        String sql = "select day(departure_time) as day, min(price) as price from d20200616 where" +
                " departure_cityname='" + departureCityName + "' and " +
                "arrival_cityname='" + arrivalCityName +
                "' and year(departure_time)= '"+year+
                "' and month(departure_time)= '"+month+
                "' group by day(departure_time)"+
                " order by day(departure_time)";
        System.out.println("sql=" + sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        System.out.println(flights);
        for (int i = 0; i < flights.size(); i++) {
            System.out.println(flights.get(i));
        }
        return flights;
    }

    @Override
    public List<Map<String, Object>> flyToWhere(String departureTime, String departureCityName) {
        String sql="select * from d20200616 where flightnumber in("+
                "select max(flightnumber) from d20200616 where departure_cityname="+departureCityName+
                "and departure_time="+departureTime+
                "group by arrival_cityname"+
                "having min(price)"+
                ")";
        System.out.println("sql=" + sql);
        System.out.println("开始查询");
        List<Map<String, Object>> places = jdbcTemplate.queryForList(sql);
        System.out.println(places);
        for (int i = 0; i < places.size(); i++) {
            System.out.println(places.get(i));
        }
        return places;
    }

}
