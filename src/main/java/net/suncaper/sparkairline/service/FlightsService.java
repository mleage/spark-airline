package net.suncaper.sparkairline.service;

import net.suncaper.sparkairline.entity.Flights;
import java.util.List;
import java.util.Map;

public interface FlightsService {
    //查询航程信息
    List<Map<String, Object>> getFlights(String departureCityName, String arrivalCityName);
    //飞去哪
    List<Map<String, Object>> flyWhere(String departureCityName, String departure_time);
    //航程比价,提供直飞的航程信息
    List<Map<String, Object>> ComparePrice(String departureCityName, String arrivalCityName,String deprture_time);
    //查看具体信息(根据航班号,出发时间---确定从哪张表开始）
    List<Map<String,Object>> specificInfo(String flightNumber ,String departure_time );
}
