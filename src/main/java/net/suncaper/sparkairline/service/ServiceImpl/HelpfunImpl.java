package net.suncaper.sparkairline.service.ServiceImpl;

import net.suncaper.sparkairline.service.Helpfun;

public class HelpfunImpl implements Helpfun {
    @Override
    public String FormatingTime(String time) {
        String result="";
        //将departure_time格式转换eg:2020-06-16 ->d20200616
        StringBuilder str=new StringBuilder(time);
        String day=str.substring(8);
        String month=str.substring(5,7);
        String year=str.substring(0,4);
        result=result+year+month+day;
        return result;
    }
}
