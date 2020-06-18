package net.suncaper.sparkairline.service.ServiceImpl;

import net.suncaper.sparkairline.entity.Flights;
import net.suncaper.sparkairline.service.FlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class FlightsServiceImpl implements FlightsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> getFlights(String departureCityName, String arrivalCityName) {
        String sql="select flightNumber,departure_time,stop_cityname,departure_terminal,price from d20200616 where" +
                " departure_cityname="+departureCityName+" and "+
                "arrival_cityname="+arrivalCityName+
                " order by price";
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
    public List<Map<String, Object>> flyWhere(String departureCityName, String departure_time) {
        //todo:修改sql
        //将departure_time格式转换eg:2020-06-16 ->d20200616
        String formatDeTime="";
        String sql= "";
        System.out.println("sql="+sql);
        System.out.println("开始查询");
        List<Map<String, Object>> where = jdbcTemplate.queryForList(sql);
        System.out.println(where);
        for( int i = 0; i < where.size(); i++ ) {
            System.out.println( where.get(i) );
        }
        return where;
    }

    @Override
    public List<Map<String, Object>> ComparePrice(String departureCityName, String arrivalCityName) {
        //todo:修改sql
        String sql="";
        System.out.println("sql="+sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        //todo：此处可添加拼接后的信息list,两个list可以合并
        return flights;
    }

}
