package net.suncaper.sparkairline.controller;

import com.alibaba.fastjson.JSON;
import net.suncaper.sparkairline.service.ServiceImpl.FlightsServiceImpl;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.apache.spark.sql.execution.columnar.MAP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comparePrice")
public class FlightsComparePriceController {
    @Autowired
    private FlightsServiceImpl FlightsService;
    //获取同航程比价的信息
    @RequestMapping("/compare_price")
    @ResponseBody
    public String comparePrice(HttpServletRequest request){
        String departure_cityname=request.getParameter("departure_cityName");
        String arrival_cityname=request.getParameter("arrival_cityName");
        String departure_time=request.getParameter("departure_time");
        List<Map<String,Object>> Prices= FlightsService.ComparePrice(departure_cityname,arrival_cityname,departure_time);
        String PriceStr= JSON.toJSONString(Prices);
        return PriceStr;
    }
}
