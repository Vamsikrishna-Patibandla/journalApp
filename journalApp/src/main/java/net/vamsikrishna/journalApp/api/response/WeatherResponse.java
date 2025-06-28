package net.vamsikrishna.journalApp.api.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Location{
    private String name;
    public String region;
    public String country;
    public double lat;
    public double lon;
    public String tz_id;
    public int localtime_epoch;
    public String localtime;
}

@Getter
@Setter
public class WeatherResponse{
    public Location location;
    public Current current;



}

