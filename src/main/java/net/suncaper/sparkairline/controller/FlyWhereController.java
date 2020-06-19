package net.suncaper.sparkairline.controller;

import com.alibaba.fastjson.JSON;
import net.suncaper.sparkairline.service.ServiceImpl.FlightsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping("/flyWhere")
public class FlyWhereController {
    @Autowired
    private FlightsServiceImpl FlightsService;
    //获取飞去哪各地方的信息
    @RequestMapping("/fly_where")
    @ResponseBody
    public String flyTowhere(HttpServletRequest request){
        String departureCityName=request.getParameter("departureCityName");
        String departure_time=request.getParameter("departure_time");
        List<Map<String, Object>> Places=FlightsService.flyWhere(departureCityName, departure_time);
        String PlacesStr= JSON.toJSONString(Places);
        return PlacesStr;
    }
}
