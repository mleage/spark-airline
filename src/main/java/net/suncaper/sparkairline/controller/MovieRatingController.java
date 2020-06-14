package net.suncaper.sparkairline.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.mllib.recommendation.MatrixFactorizationModel;
import org.apache.spark.mllib.recommendation.Rating;
import org.codehaus.janino.Java;
import scala.Tuple2;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/movie")
public class MovieRatingController {
    @GetMapping("rating")
    @ResponseBody
    public List<String> listPrimaryData() {
        JavaSparkContext ctx=new JavaSparkContext("local","Movie-Job");
        JavaRDD<String> rawData = ctx.textFile("ml-100k/ml-100k/u.data");
        JavaRDD<String[]> rawRatings = rawData.map(x -> Arrays.asList(x.split("\t")).subList(0,3).toArray(new String[]{}));
        JavaRDD<Rating>  ratings = rawRatings.map(x ->new Rating(Integer.valueOf(x[0]),Integer.valueOf(x[1]),Integer.valueOf(x[2])));
        return rawData.collect();
    }
}
