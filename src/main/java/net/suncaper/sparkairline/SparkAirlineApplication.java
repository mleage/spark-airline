package net.suncaper.sparkairline;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

@SpringBootApplication
public class SparkAirlineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SparkAirlineApplication.class, args);
    }

}
