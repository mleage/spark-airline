package net.suncaper.sparkairline.service.ServiceImpl;

import net.suncaper.sparkairline.entity.Flights;
import net.suncaper.sparkairline.service.FlightsService;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * @author Jia,Dian
 * @version 1.0
 */

@Service
public class FlightsServiceImpl implements FlightsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param departureCityName
     * @param arrivalCityName
     * @param departureTime
     * @return
     * 输入出发地点，目的地点，以及出发时间，返回按价格排序的单程航班的maplist
     * 往返和多程只需将地点调换多次调用即可
     */
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
    /**
     *
     * @param departureCityName
     * @param arrivalCityName
     * @param departureTime
     * @return
     * 输入出发地点，目的地点，以及出发时间，返回按持续时间排序的单程航班的maplist
     * 往返和多程只需将地点调换多次调用即可
     */
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
    /**
     *
     * @param departureCityName
     * @param arrivalCityName
     * @param departureTime
     * @return
     * 输入出发地点，目的地点，以及出发时间，返回按出发时间排序的单程航班的maplist
     * 往返和多程只需将地点调换多次调用即可
     */
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
    /**
     *
     * @param departureCityName
     * @param arrivalCityName
     * @param departureTime
     * @return
     * 输入出发地点，目的地点，以及出发时间，返回按到达时间排序的单程航班的maplist
     * 往返和多程只需将地点调换多次调用即可
     */
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

    /**
     *
     * @param departureCityName
     * @param arrivalCityName
     * @param year
     * @return
     * 输入出发地点和目的地点和年份，输出所属年份一年按月最低价格排布，可用作何时飞条形图数据源
     * 注意此函数并无预测功能，仅显示爬取好的数据
     */
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

    /**
     *
     * @param departureCityName
     * @param arrivalCityName
     * @param year
     * @param month
     * @return
     * 输入出发地和目的地，以及年份和月份，输出当月的按天最低价格排布，可作为何时飞条形图数据源
     * 注意此函数并无预测功能，仅显示爬取好的数据
     */
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
    /**
     *
     * @param departureCityName
     * @param departureTime
     * @return
     * 输入出发地和出发时间，获取各城市最低价的航程信息
     *
     */
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

    @Override
    public List<Map<String, Object>> predictModelTraningYear(String departureCityName, String arrivalCityName, String year) throws IOException {
        String sql = "select departure_cityid,arrival_cityid,month(departure_time) as month, price from d20200616";
        System.out.println("sql=" + sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        File file = new File("predictYear.data");
        //如果没有文件就创建
        if (!file.isFile()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("predictYear.data"));
        for(Map<String,Object> map:flights){
            String strItems="";
            for (String key : map.keySet()) {
                strItems = strItems+map.get(key)+"\t";
            }
            writer.write(strItems.trim());
            writer.write("\n");
        }
        System.out.println("写入成功");
        writer.close();

        System.out.println(flights);
        for (int i = 0; i < flights.size(); i++) {
            System.out.println(flights.get(i));
        }


        return flights;
    }

    @Override
    public List<Map<String, Object>> predictModelTraningMonth(String departureCityName, String arrivalCityName, String year, String month) {
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


}
