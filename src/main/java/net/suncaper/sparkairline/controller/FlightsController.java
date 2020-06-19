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

/**
 * @author Jia,Dian
 * @version 1.0
 */
@Controller
@RequestMapping("/flights")
public class FlightsController {
    @Autowired
    private FlightsServiceImpl FlightsService;

    /**
     *
     * @param request
     * @return
     * ajax访问/flights/getFlightsOneWayByPrice传递出发城市，到达城市，出发时间三个参数
     * 返回单程按价格排序航班列表，
     * 具体实现见serviceImpl
     */
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
    /**
     *
     * @param request
     * @return
     * ajax访问/flights/getFlightsOneWayByDuringTime传递出发城市，到达城市，出发时间三个参数
     * 返回单程按持续时间排序航班列表，
     * 具体实现见serviceImpl
     */
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
    /**
     *
     * @param request
     * @return
     * ajax访问/flights/getFlightsOneWayByDepartureTime传递出发城市，到达城市，出发时间三个参数
     * 返回单程按出发时间排序航班列表，
     * 具体实现见serviceImpl
     */
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
    /**
     *
     * @param request
     * @return
     * ajax访问/flights/getFlightsOneWayByArrivalTime传递出发城市，到达城市，出发时间三个参数
     * 返回单程按到达时间排序航班列表，
     * 具体实现见serviceImpl
     */
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
    /**
     *
     * @param request
     * @return
     * ajax访问/flights/whenToFlightViewYear传递出发城市，到达城市，年份三个参数
     * 返回当年最低价格排布，
     * 具体实现见serviceImpl
     */
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
    /**
     *
     * @param request
     * @return
     * ajax访问/flights/whenToFlightViewYear传递出发城市，到达城市，年份，月份四个参数
     * 返回当月最低价格排布，
     * 具体实现见serviceImpl
     */
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

    @RequestMapping("flyToWhere")
    @ResponseBody
    public  String flyToWhere(HttpServletRequest request){
        String departureCityName=request.getParameter("departureCityName");
        String departureTime=request.getParameter("departureTime");
        List<Map<String,Object>> Places=FlightsService.flyToWhere(departureTime,departureCityName);
        String PlacesStr=JSON.toJSONString(Places);
        return PlacesStr;
    }
}
