package net.suncaper.sparkairline.service;

import net.suncaper.sparkairline.entity.Flights;
import java.util.List;
import java.util.Map;

public interface FlightsService {
    List<Map<String, Object>> getFlights(String departureCityName, String arrivalCityName);
}
