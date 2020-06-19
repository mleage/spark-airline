package net.suncaper.sparkairline.service;

import net.suncaper.sparkairline.entity.Flights;
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

}
