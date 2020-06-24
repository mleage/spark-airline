package net.suncaper.sparkairline.service.ServiceImpl;

import com.rabbitmq.tools.json.JSONUtil;
import net.suncaper.sparkairline.entity.Flights;
import net.suncaper.sparkairline.service.FlightsService;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

/**
 * @author Jia,Dian
 * @version 1.0
 */

@Service
public class FlightsServiceImpl implements FlightsService,Serializable {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     *
     * @param departureCityName
     * @param arrivalCityName
     * @param departureTime
     * @return
     * 输入出发地点，目的地点，以及出发时间，返回按价格排序的单程航班的maplist
     * 往返和多程只需将地点调换多次调用即可
     */
    @Override
    public List<Map<String, Object>> getFlightsOneWayByPrice(String departureCityName, String arrivalCityName,String departureTime) {
        String sql="select airlineName,departure_cityname,arrival_cityname,departure_airportname,flightNumber,departure_time,departure_terminal,arrival_airportname,arrival_terminal,arrival_time,stop_cityname,price from d20200616 where" +
                " departure_cityname='"+departureCityName+"' and "+
                "arrival_cityname='"+arrivalCityName+
                "' and departure_time like '"+departureTime+
                "%' order by price";

        System.out.println("sql="+sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        System.out.println(flights);
        for( int i = 0; i < flights.size(); i++ ) {
            System.out.println( flights.get(i) );
        }
        return flights;
    }
    /**
     *
     * @param departureCityName
     * @param arrivalCityName
     * @param departureTime
     * @return
     * 输入出发地点，目的地点，以及出发时间，返回按持续时间排序的单程航班的maplist
     * 往返和多程只需将地点调换多次调用即可
     */
    @Override
    public List<Map<String, Object>> getFlightsOneWayByDuringTime(String departureCityName, String arrivalCityName,String departureTime) {
        String sql="select airlineName,departure_cityname,arrival_cityname,departure_airportname,flightNumber,departure_time,departure_terminal,arrival_airportname,arrival_terminal,arrival_time,stop_cityname,price from d20200616 where" +
                " departure_cityname='"+departureCityName+"' and "+
                "arrival_cityname='"+arrivalCityName+
                "' and departure_time like '"+departureTime+
                "%' order by duration";
        System.out.println("sql="+sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        System.out.println(flights);
        for( int i = 0; i < flights.size(); i++ ) {
            System.out.println( flights.get(i) );
        }
        return flights;
    }
    /**
     *
     * @param departureCityName
     * @param arrivalCityName
     * @param departureTime
     * @return
     * 输入出发地点，目的地点，以及出发时间，返回按出发时间排序的单程航班的maplist
     * 往返和多程只需将地点调换多次调用即可
     */
    @Override
    public List<Map<String, Object>> getFlightsOneWayByDepartureTime(String departureCityName, String arrivalCityName,String departureTime) {
        String sql = "select airlineName,departure_cityname,arrival_cityname,departure_airportname,flightNumber,departure_time,departure_terminal,arrival_airportname,arrival_terminal,arrival_time,stop_cityname,price from d20200616 where" +
                " departure_cityname='" + departureCityName + "' and " +
                "arrival_cityname='" + arrivalCityName +
                "' and departure_time like '" + departureTime +
                "%' order by departure_time";
        System.out.println("sql=" + sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        System.out.println(flights);
        for (int i = 0; i < flights.size(); i++) {
            System.out.println(flights.get(i));
        }
        return flights;
    }
    /**
     *
     * @param departureCityName
     * @param arrivalCityName
     * @param departureTime
     * @return
     * 输入出发地点，目的地点，以及出发时间，返回按到达时间排序的单程航班的maplist
     * 往返和多程只需将地点调换多次调用即可
     */
    @Override
    public List<Map<String, Object>> getFlightsOneWayByArrivalTime(String departureCityName, String arrivalCityName,String departureTime) {
        String sql = "select airlineName,departure_cityname,arrival_cityname,departure_airportname,flightNumber,departure_time,departure_terminal,arrival_airportname,arrival_terminal,arrival_time,stop_cityname,price from d20200616 where" +
                " departure_cityname='" + departureCityName + "' and " +
                "arrival_cityname='" + arrivalCityName +
                "' and departure_time like '" + departureTime +
                "%' order by arrival_time";
        System.out.println("sql=" + sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        System.out.println(flights);
        for (int i = 0; i < flights.size(); i++) {
            System.out.println(flights.get(i));
        }
        return flights;
    }

    @Override
    public List<Map<String, Object>> getFlightsOneWayJointByPrice(String departureCityName, String arrivalCityName,String departureTime) {
        String sql="select flid,departure_cityname,arrival_cityname,airlineName,departure_airportname,flightNumber,departure_time,departure_terminal,arrival_airportname,arrival_terminal,arrival_time,stop_cityname,price from flights_line where" +
                " departure_cityname='"+departureCityName+"' and "+
                "arrival_cityname='"+arrivalCityName+
                "' and departure_time like '"+departureTime+
                "%' order by price";
        String segment1="select departure_cityname,arrival_cityname,airlineName,departure_airportname,flightNumber,departure_time,departure_terminal,arrival_airportname,arrival_terminal,arrival_time,stop_cityname,price from d20200616 where" +
                " departure_cityname='"+departureCityName+
                "' and departure_time like '"+departureTime+
                "%' order by departure_time";
        String segment2="select departure_cityname,arrival_cityname,airlineName,departure_airportname,flightNumber,departure_time,departure_terminal,arrival_airportname,arrival_terminal,arrival_time,stop_cityname,price from d20200616 where" +
                " arrival_cityname='"+arrivalCityName+
                "' order by departure_time";
        List<Map<String, Object>> flights_segment1 = jdbcTemplate.queryForList(segment1);
        List<Map<String, Object>> flights_segment2 = jdbcTemplate.queryForList(segment2);
        System.out.println("sql="+sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flightsline = jdbcTemplate.queryForList(sql);
        System.out.println(flightsline);
        for(Map<String, Object> f:flightsline) {
            String sql_flights_segment = "select fsid,flid,airlineName,flightNumber,departure_time,arrival_time,duration,craft_type_kind_display_name,craft_type_name from flights_segment where" +
                    " flid=" + f.get("flid") +
                    " order by departure_time";
            System.out.println("sql=" + sql_flights_segment);
            System.out.println("开始查询");
            List<Map<String, Object>> flightsegment = jdbcTemplate.queryForList(sql_flights_segment);
            if (!flightsegment.isEmpty()) {
                f.put("segment", flightsegment);
                System.out.println("航段已加入");
            } else {
                System.out.println("无航段可用，进行智能拼接");
                List<Map<String, Object>> resultList = new ArrayList<>();
                for (Map<String, Object> seg1 : flightsline) {
                    for (Map<String, Object> seg2 : flightsline) {
                        if (seg1.get("arrival_cityname").toString().equals(seg2.get("departure_cityname").toString())
                                && seg1.get("arrival_time").toString().compareTo(seg2.get("departure_time").toString()) < 0) {
                            resultList.add(seg1);
                            resultList.add(seg2);
                            flights_segment1.remove(seg1);
                            flights_segment2.remove(seg2);
                            if (!resultList.isEmpty()) {
                                f.put("segment", resultList);
                                System.out.println("航段已加入");
                            } else {
                                f.put("segment","");
                                System.out.println("未能查询到结果");
                            }
                        }else{
                            f.put("segment", "");
                        }
                    }
                }
                if(!f.containsKey("segment")){
                    System.out.println("添加segment字段");
                    f.put("segment", "");
                }

            }
        }


        return flightsline;
    }
    /**
     *
     * @param departureCityName
     * @param arrivalCityName
     * @param year
     * @return
     * 输入出发地点和目的地点和年份，输出所属年份一年按月最低价格排布，可用作何时飞条形图数据源
     * 注意此函数并无预测功能，仅显示爬取好的数据
     */
    @Override
    public List<Map<String, Object>> whenToFlightViewYear(String departureCityName, String arrivalCityName, String year) {
        String sql = "select month(departure_time) as month, min(price) as price from d20200616 where" +
                " departure_cityname='" + departureCityName + "' and " +
                "arrival_cityname='" + arrivalCityName +
                "' and year(departure_time)= '"+year+
                "' group by month(departure_time)"+
                " order by month(departure_time)";
        System.out.println("sql=" + sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        System.out.println(flights);
        for (int i = 0; i < flights.size(); i++) {
            System.out.println(flights.get(i));
        }
        return flights;
    }

    /**
     *
     * @param departureCityName
     * @param arrivalCityName
     * @param year
     * @param month
     * @return
     * 输入出发地和目的地，以及年份和月份，输出当月的按天最低价格排布，可作为何时飞条形图数据源
     * 注意此函数并无预测功能，仅显示爬取好的数据
     */
    @Override
    public List<Map<String, Object>> whenToFlightViewMonth(String departureCityName, String arrivalCityName, String year, String month) {
        String sql = "select day(departure_time) as day, min(price) as price from d20200616 where" +
                " departure_cityname='" + departureCityName + "' and " +
                "arrival_cityname='" + arrivalCityName +
                "' and year(departure_time)= '"+year+
                "' and month(departure_time)= '"+month+
                "' group by day(departure_time)"+
                " order by day(departure_time)";
        System.out.println("sql=" + sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        System.out.println(flights);
        for (int i = 0; i < flights.size(); i++) {
            System.out.println(flights.get(i));
        }
        return flights;
    }
    /**
     *
     * @param departureCityName
     * @param departureTime
     * @return
     * 输入出发地和出发时间，获取各城市最低价的航程信息
     *
     */
    @Override
    public List<Map<String, Object>> flyToWhere(String departureTime, String departureCityName) {
        String sql="select * from d20200616 where flightnumber in("+
                "select max(flightnumber) from d20200616 where departure_cityname="+departureCityName+
                "and departure_time="+departureTime+
                "group by arrival_cityname"+
                "having min(price)"+
                ")";
        System.out.println("sql=" + sql);
        System.out.println("开始查询");
        List<Map<String, Object>> places = jdbcTemplate.queryForList(sql);
        System.out.println(places);
        for (int i = 0; i < places.size(); i++) {
            System.out.println(places.get(i));
        }
        return places;
    }

    /**
     *
     * @param departureCityName
     * @param arrivalCityName
     * @param year
     * @return
     * @throws IOException
     *输入出发地，目的地以及年份，返回一个带有月份和价格属性的maplist,对全年各月份价格进行预测;
     */
    @Override
    public List<Map<String, Object>> predictModelTraningYearDataPredicting(String departureCityName, String arrivalCityName, String year) throws IOException {
        String sql = "select departure_cityid,arrival_cityid from d20200616 where" +
                " departure_cityname='" + departureCityName + "' and " +
                "arrival_cityname='" + arrivalCityName+
                "' group by departure_citynam,arrival_cityname";
        System.out.println("sql=" + sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        int key=Integer.valueOf(charToNum(flights.get(0).get("departure_cityid").toString()))+Integer.valueOf(charToNum(flights.get(0).get("arrival_cityid").toString()));
        List<Map<String, Object>> inputdata=null;
        for (int i=0;i<12;i++){
            Map map=new HashMap();
            map.put("flightsId",key);
            map.put("month",i+1);
            inputdata.add(map);
        }
        List<Map<String, Object>> result=null;
        JavaSparkContext ctx = new JavaSparkContext("local", "Flights-Predict-Year");
        MatrixFactorizationModel model =  MatrixFactorizationModel.load(ctx.sc(), "predictYear.model");
        for(Map<String, Object> input:inputdata) {
            double predict = model.predict((int)input.get("flightsId"), (int)input.get("month"));
            Map map=new HashMap();
            map.put("month",input.get("month"));
            map.put("price",predict);
            result.add(map);
        }
        return result;
    }

    /**
     *
     * @return
     * @throws IOException
     * 数据写入接口，请勿使用！
     */
    @Override
    public List<Map<String, Object>> predictModelTraningYearDataWrite() throws IOException {
        String sql = "select departure_cityid,arrival_cityid,month(departure_time) as month, price from d20200616";
        System.out.println("sql=" + sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        File file = new File("predictYear.data");
        //如果没有文件就创建
        if (!file.isFile()) {
            file.createNewFile();
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter("predictYear.data"));
        for(Map<String,Object> map:flights){
            String strItems="";
            int sum=0;
            for (String key : map.keySet()) {
                if(Character.isLetter(map.get(key).toString().toCharArray()[0])){
                    sum+=Integer.valueOf(charToNum(map.get(key).toString()));
                }
                else{
                    if(sum!=0){
                        strItems=String.valueOf(sum);
                        sum=0;
                    }
                    strItems =  strItems+"\t"+map.get(key);
                }

            }
            writer.write(strItems.trim());
            writer.write("\n");
        }
        System.out.println("写入成功");
        writer.close();

        System.out.println(flights);
        for (int i = 0; i < flights.size(); i++) {
            System.out.println(flights.get(i));
        }
        return flights;
    }

    /**
     *
     * @param departureCityName
     * @param arrivalCityName
     * @param year
     * @param month
     * @return
     */
    @Override
    public List<Map<String, Object>> predictModelTraningMonth(String departureCityName, String arrivalCityName, String year, String month) {
        String sql = "select day(departure_time) as day, min(price) as price from d20200616 where" +
                " departure_cityname='" + departureCityName + "' and " +
                "arrival_cityname='" + arrivalCityName +
                "' and year(departure_time)= '"+year+
                "' and month(departure_time)= '"+month+
                "' group by day(departure_time)"+
                " order by day(departure_time)";
        System.out.println("sql=" + sql);
        System.out.println("开始查询");
        List<Map<String, Object>> flights = jdbcTemplate.queryForList(sql);
        System.out.println(flights);
        for (int i = 0; i < flights.size(); i++) {
            System.out.println(flights.get(i));
        }
        return flights;
    }

    /**
     *
     * @throws IOException
     * 数据训练接口，请勿使用！
     */
    @Override
    public void predictModelTraningYearDataTraining() throws IOException {
        SparkConf conf = new SparkConf().setAppName("Flights-Predict-Year").setMaster("local[2]").set("spark.driver.allowMultipleContexts","true");
        JavaSparkContext ctx = new JavaSparkContext("local", "Flights-Predict-Year");
        JavaRDD<String> rawData = ctx.textFile("predictYear.data");
        JavaRDD<String[]> rawRatings = rawData.map(x -> Arrays.asList(x.split("\t")).subList(0, 3).toArray(new String[]{}));
        JavaRDD<Rating> ratings = rawRatings.map(x -> new Rating(Integer.valueOf(x[0]), Integer.valueOf(x[1]), Integer.valueOf(x[2])));
        MatrixFactorizationModel model = ALS.train(ratings.rdd(), 50, 10, 0.01);
        model.save(ctx.sc(), "predictYear.model");
    }

    /**
     *
     * @param input
     * @return
     * 辅助函数，请勿使用！
     */
    @Override
    public String charToNum(String input){
        String reg = "[a-zA-Z]";
        StringBuffer strBuf = new StringBuffer();
        input = input.toLowerCase();
        if (null != input && !"".equals(input))
        {
            for (char c : input.toCharArray())
            {
                if (String.valueOf(c).matches(reg))
                {
                    strBuf.append(c - 96);
                } else
                {
                    strBuf.append(c);
                }
            }
            return strBuf.toString();
        } else
        {
            return input;
        }
    }


}
