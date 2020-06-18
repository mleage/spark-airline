package net.suncaper.sparkairline.controller;

import com.alibaba.fastjson.JSON;
import net.suncaper.sparkairline.entity.Flights;
import net.suncaper.sparkairline.service.ServiceImpl.FlightsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import net.suncaper.sparkairline.service.FlightsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/flights")
public class FlightsController {
    @Autowired
    private FlightsServiceImpl FlightsService;

    @RequestMapping("/getFlightsOneWayByPrice")
    @ResponseBody
    public String getFlightsOneWayByPrice(HttpServletRequest request){
        String departureCityName=request.getParameter("departureCityName");
        String arrivalCityName=request.getParameter("arrivalCityName");
        String departureTime=request.getParameter("departureTime");
        List<Map<String, Object>> Flights=FlightsService.getFlightsOneWayByPrice("departureCityName", "arrivalCityName","departureTime");

        String FlightsStr= JSON.toJSONString(Flights);
        return FlightsStr;
    }

    @RequestMapping("/getFlightsOneWayByDuringTime")
    @ResponseBody
    public String getFlightsOneWayByDuringTime(HttpServletRequest request){
        String departureCityName=request.getParameter("departureCityName");
        String arrivalCityName=request.getParameter("arrivalCityName");
        String departureTime=request.getParameter("departureTime");
        List<Map<String, Object>> Flights=FlightsService.getFlightsOneWayByDuringTime("departureCityName", "arrivalCityName","departureTime");

        String FlightsStr= JSON.toJSONString(Flights);
        return FlightsStr;
    }

    @RequestMapping("/getFlightsOneWayByDepartureTime")
    @ResponseBody
    public String getFlightsOneWayByDepartureTime(HttpServletRequest request) {
        String departureCityName = request.getParameter("departureCityName");
        String arrivalCityName = request.getParameter("arrivalCityName");
        String departureTime = request.getParameter("departureTime");
        List<Map<String, Object>> Flights = FlightsService.getFlightsOneWayByDepartureTime("成都", "宁波", "2020-06-16");

        String FlightsStr = JSON.toJSONString(Flights);
        return FlightsStr;
    }

    @RequestMapping("/getFlightsOneWayByArrivalTime")
    @ResponseBody
    public String getFlightsOneWayByArrivalTime(HttpServletRequest request){
        String departureCityName=request.getParameter("departureCityName");
        String arrivalCityName=request.getParameter("arrivalCityName");
        String departureTime=request.getParameter("departureTime");
        List<Map<String, Object>> Flights=FlightsService.getFlightsOneWayByArrivalTime("宁波", "厦门","2020-06-16");

        String FlightsStr= JSON.toJSONString(Flights);
        return FlightsStr;
    }

    @RequestMapping("/whenToFlightViewYear")
    @ResponseBody
    public String whenToFlightViewYear(HttpServletRequest request){
        String departureCityName=request.getParameter("departureCityName");
        String arrivalCityName=request.getParameter("arrivalCityName");
        String year=request.getParameter("year");
        List<Map<String, Object>> Flights=FlightsService.whenToFlightViewYear("宁波", "厦门","2020");

        String FlightsStr= JSON.toJSONString(Flights);
        return FlightsStr;
    }

    @RequestMapping("/whenToFlightViewMonth")
    @ResponseBody
    public String whenToFlightViewMonth(HttpServletRequest request){
        String departureCityName=request.getParameter("departureCityName");
        String arrivalCityName=request.getParameter("arrivalCityName");
        String year=request.getParameter("year");
        String month=request.getParameter("month");
        List<Map<String, Object>> Flights=FlightsService.whenToFlightViewMonth("宁波", "厦门","2020","6");

        String FlightsStr= JSON.toJSONString(Flights);
        return FlightsStr;
    }
}
