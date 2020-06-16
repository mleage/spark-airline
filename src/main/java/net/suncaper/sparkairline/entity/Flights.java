package net.suncaper.sparkairline.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.joda.time.DateTime;

import java.sql.Time;
import java.sql.Timestamp;

public class Flights {
    public String getDepartureCityName() {
        return departureCityName;
    }

    public String getDepartureCityId() {
        return departureCityId;
    }

    public String getArrivalCityName() {
        return arrivalCityName;
    }

    public String getArrivalCityId() {
        return arrivalCityId;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public String getDepartureAirportId() {
        return departureAirportId;
    }

    public String getDepartureTerminal() {
        return departureTerminal;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public String getArrivalAirportId() {
        return arrivalAirportId;
    }

    public String getArrivalTerminal() {
        return arrivalTerminal;
    }

    public DateTime getDepartureTime() {
        return departureTime;
    }

    public DateTime getArrivalTime() {
        return arrivalTime;
    }

    public Time getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public String getPunctualityRate() {
        return punctualityRate;
    }

    public String getCraftTypeKindDisplayName() {
        return craftTypeKindDisplayName;
    }

    public String getCraftTypeName() {
        return craftTypeName;
    }

    public String getStopCityname() {
        return stopCityname;
    }

    private String departureCityName;
    private String departureCityId;
    private String arrivalCityName;
    private String arrivalCityId;
    private String airlineName;
    private String flightNumber;
    private String departureAirportName;
    private String departureAirportId;
    private String departureTerminal;
    private String arrivalAirportName;
    private String arrivalAirportId;
    private String arrivalTerminal;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private DateTime departureTime;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private DateTime arrivalTime;
    @JSONField(format="HH:mm:ss")
    private Time duration;
    private int price;
    private String punctualityRate;
    private String craftTypeKindDisplayName;
    private String craftTypeName;
    private String stopCityname;



}
