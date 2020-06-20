package net.suncaper.sparkairline.service;

import net.suncaper.sparkairline.entity.Flights;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Jia,Dian
 * @version 1.0
 *
 */

public interface FlightsService {
    List<Map<String, Object>> getFlightsOneWayByPrice(String departureCityName, String arrivalCityName, String departureTime);
    List<Map<String, Object>> getFlightsOneWayByDuringTime(String departureCityName, String arrivalCityName, String departureTime);
    List<Map<String, Object>> getFlightsOneWayByDepartureTime(String departureCityName, String arrivalCityName, String departureTime);
    List<Map<String, Object>> getFlightsOneWayByArrivalTime(String departureCityName, String arrivalCityName, String departureTime);
    List<Map<String, Object>> whenToFlightViewYear(String departureCityName, String arrivalCityName, String year);
    List<Map<String, Object>> whenToFlightViewMonth(String departureCityName, String arrivalCityName, String year, String month);
    //飞去哪,根据出发城市，出发时间，确定各城市最低价的航程
    List<Map<String, Object>> flyToWhere(String departureTime,String departureCityName);
    //机器学习预测未来一年各月最低价格模型训练功能
    List<Map<String, Object>> predictModelTraningYear(String departureCityName, String arrivalCityName, String year) throws IOException;
    //机器学习预测未来一月各天最低价格模型训练功能
    List<Map<String, Object>> predictModelTraningMonth(String departureCityName, String arrivalCityName, String year, String month);
}
