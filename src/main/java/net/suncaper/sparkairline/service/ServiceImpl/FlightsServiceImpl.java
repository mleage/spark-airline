package net.suncaper.sparkairline.service.ServiceImpl;

import net.suncaper.sparkairline.entity.Flights;
import net.suncaper.sparkairline.service.FlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
@Service
public class FlightsServiceImpl implements FlightsService {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private HelpfunImpl helpfun;

    @Override
    public List<Map<String, Object>> getFlights(String departureCityName, String arrivalCityName) {
        String sql="select flightNumber,departure_time,stop_cityname,departure_terminal,price from d20200616 where" +
                " departure_cityname="+departureCityName+" and "+
                "arrival_cityname="+arrivalCityName+
                " order by price";
        System.out.println("sql="+sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        //test
        System.out.println(flights);
        for( int i = 0; i < flights.size(); i++ ) {
            System.out.println( flights.get(i) );
        }
        return flights;
    }

    @Override
    public List<Map<String, Object>> flyWhere(String departureCityName, String departure_time) {
        //todo:修改sql
        //将departure_time格式转换eg:2020-06-16 ->d20200616，可以lamda
        String formatDeTime="d"+helpfun.FormatingTime(departure_time);
        //直飞
        String sql=//父查询
                "select * from "+formatDeTime+"where flightNumber in"+
                //子查询
                "(select min(flightNumber) from "+formatDeTime+"where departure_cityname="+departureCityName+
                "group by  arrival_cityname"+"having min(price)"+
                ")";
        System.out.println("sql="+sql);
        System.out.println("开始查询");
        List<Map<String, Object>> where = jdbcTemplate.queryForList(sql);
        //todo: 拼接的航程信息
        //todo:将拼接的航程信息与直飞对比，价格更小的替换

//        System.out.println(where);
        Iterator<Map<String, Object>> it = where.iterator();
        while(it.hasNext()){
            Map<String, Object> next = it.next();
            System.out.println(next.toString());
//            System.out.println(next.get("flightNumber"));
        }
        return where;
    }

    @Override
    public List<Map<String, Object>> ComparePrice(String departureCityName, String arrivalCityName,String deprture_time) {
        //唯一标识航程由出发地，目的地，出发时间
        String formatTime="d"+helpfun.FormatingTime(deprture_time);
        String sql="select * from "+formatTime+"where departureCityName="+departureCityName+"and arrivalCityName="+arrivalCityName;
        System.out.println("sql="+sql);
        System.out.println("开始查询");
        //直飞航程信息
        List<Map<String, Object>> flights_outright = jdbcTemplate.queryForList(sql);
        //todo：此处可添加拼接后的信息list,两个list可以合并
        return flights_outright;
    }

    @Override
    public List<Map<String, Object>> specificInfo(String flightNumber,String departure_time) {
        //查询具体信息
        String  format_time="d"+helpfun.FormatingTime(departure_time);
        String sql="select * from "+format_time+ "where flightNumber="+flightNumber;
        System.out.println("sql="+sql);
        System.out.println("开始查询");
        List<Map<String,Object>> info=jdbcTemplate.queryForList(sql);
        //test
        for( int i = 0; i < info.size(); i++ ) {
            System.out.println( info.get(i) );
        }
        return info;
    }

}
