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
import java.io.IOException;
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
        List<Map<String, Object>> Flights=FlightsService.getFlightsOneWayByPrice(departureCityName, arrivalCityName,departureTime);

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
        List<Map<String, Object>> Flights=FlightsService.getFlightsOneWayByDuringTime(departureCityName, arrivalCityName,departureTime);

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
        List<Map<String, Object>> Flights = FlightsService.getFlightsOneWayByDepartureTime(departureCityName, arrivalCityName, departureTime);

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
        List<Map<String, Object>> Flights=FlightsService.getFlightsOneWayByArrivalTime(departureCityName, arrivalCityName,departureTime);

        String FlightsStr= JSON.toJSONString(Flights);
        return FlightsStr;
    }

    /**
     *
     * @param request
     * @return
     * 添加了拼接功能返回的map里增加了一个segment字段，形式为list<map>
     */
    @RequestMapping("/getFlightsOneWayJointByPrice")
    @ResponseBody
    public String getFlightsOneWayJointByPrice(HttpServletRequest request){
        String departureCityName=request.getParameter("departureCityName");
        String arrivalCityName=request.getParameter("arrivalCityName");
        String departureTime=request.getParameter("departureTime");
        List<Map<String, Object>> Flights=FlightsService.getFlightsOneWayJointByPrice(departureCityName, arrivalCityName,departureTime);

        String FlightsStr= JSON.toJSONString(Flights);
        return FlightsStr;
    }
    /**
     *
     * @param request
     * @return
     * ajax访问/flights/whenToFlightViewYear传递出发城市，到达城市，年份三个参数
     * 返回当年最低价格排布，没有预测功能！
     * 具体实现见serviceImpl
     */
    @RequestMapping("/whenToFlightViewYear")
    @ResponseBody
    public String whenToFlightViewYear(HttpServletRequest request){
        String departureCityName=request.getParameter("departureCityName");
        String arrivalCityName=request.getParameter("arrivalCityName");
        String year=request.getParameter("year");
        List<Map<String, Object>> Flights=FlightsService.whenToFlightViewYear(departureCityName, arrivalCityName,year);

        String FlightsStr= JSON.toJSONString(Flights);
        return FlightsStr;
    }
    /**
     *
     * @param request
     * @return
     * ajax访问/flights/whenToFlightViewYear传递出发城市，到达城市，年份，月份四个参数
     * 返回当月最低价格排布，没有预测功能！
     * 具体实现见serviceImpl
     */
    @RequestMapping("/whenToFlightViewMonth")
    @ResponseBody
    public String whenToFlightViewMonth(HttpServletRequest request){
        String departureCityName=request.getParameter("departureCityName");
        String arrivalCityName=request.getParameter("arrivalCityName");
        String year=request.getParameter("year");
        String month=request.getParameter("month");
        List<Map<String, Object>> Flights=FlightsService.whenToFlightViewMonth(departureCityName, arrivalCityName,year,month);

        String FlightsStr= JSON.toJSONString(Flights);
        return FlightsStr;
    }

    /**
     *
     * @param request
     * @return
     * ajax访问/flights/flyToWhere传递出发城市，出发时间两个个参数
     * 返回飞往各地城市最低价的航程信息，
     * 具体实现见serviceImpl
     */
    @RequestMapping("flyToWhere")
    @ResponseBody
    public  String flyToWhere(HttpServletRequest request){
        String departureCityName=request.getParameter("departureCityName");
        String departureTime=request.getParameter("departureTime");
        List<Map<String,Object>> Places=FlightsService.flyToWhere(departureTime,departureCityName);
        String PlacesStr=JSON.toJSONString(Places);
        return PlacesStr;
    }

    /**
     *
     * @param request
     * @throws IOException
     * 传递出发城市，目的城市，以及年份，回传对一年各月份的机票价格预测,属性为月份和价格。
     */
    @RequestMapping("/predictModelTraningYearDataPredicting")
    @ResponseBody
    public String predictModelTraningYearDataPredicting(HttpServletRequest request) throws IOException {
        String departureCityName=request.getParameter("departureCityName");
        String arrivalCityName=request.getParameter("arrivalCityName");
        String year=request.getParameter("year");
        List<Map<String,Object>> yearPredicting=FlightsService.predictModelTraningYearDataPredicting(departureCityName,arrivalCityName,year);
        String yearPredictingStr=JSON.toJSONString(yearPredicting);
        return yearPredictingStr;
    }



    /**
     * 训练数据写入接口，请勿使用！
     */
    @RequestMapping("/predictModelTraningYearDataWrite")
    @ResponseBody
    public String predictModelTraningYearDataWrite(HttpServletRequest request) throws IOException {
        List<Map<String, Object>> Flights=FlightsService.predictModelTraningYearDataWrite();
        String FlightsStr= JSON.toJSONString(Flights);
        return FlightsStr;
    }

    /**
     * 数据训练接口，请勿使用！
     */
    @RequestMapping("/predictModelTraningYearDataTraining")
    @ResponseBody
    public void predictModelTraningYearDataTraining(HttpServletRequest request) throws IOException {
        String departureCityName=request.getParameter("departureCityName");
        String arrivalCityName=request.getParameter("arrivalCityName");
        String year=request.getParameter("year");
        String month=request.getParameter("month");
        FlightsService.predictModelTraningYearDataTraining();
    }
}
