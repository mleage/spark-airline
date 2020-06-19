package net.suncaper.sparkairline.controller;

import java.util.List;
import java.util.Map;

public class FlightsFilter {
    //在已获得的航班信息中，根据XXXX筛选
    public List<Map<String,Object>> flightsFilter(int x,List<Map<String,Object>> input){
        //todo:待修改
        List<Map<String, Object>> result;
        //根据输入
        result= (List<Map<String, Object>>) input.stream();

        return result;
    }
}
