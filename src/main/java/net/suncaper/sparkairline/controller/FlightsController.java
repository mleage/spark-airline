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

    @RequestMapping("/getFlights")
    @ResponseBody
    public String getFlight(HttpServletRequest request){
        String departureCityName=request.getParameter("departureCityName");
        String arrivalCityName=request.getParameter("arrivalCityName");
        List<Map<String, Object>> Flights=FlightsService.getFlights(departureCityName, arrivalCityName);

        String FlightsStr= JSON.toJSONString(Flights);
        return FlightsStr;
    }
}
