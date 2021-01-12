package ro.mta.se.lab.model;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Weather {
    String Country;
    String City;
    String Temp;
    String Key;
    public Weather() {
        setKey("ba15ea04b3f184dfca3b983297c4c234");
        Cities x=new Cities();
    }

    public void setCity(String city) {
        City = city;
    }

    public void setKey(String key) {
        Key = key;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public String getCountry() {
        return Country;
    }

    public String Get_from_Json(String word1, String word2, JSONObject jo) throws ParseException {
        Object Weather= new JSONParser().parse(jo.get(word1).toString());
        Object WeatherJ=new Object();
        if(Weather.toString().charAt(0)=='['){
            WeatherJ=new JSONParser().parse(Weather.toString().substring(1).replaceFirst(".$","").toString());
        }
        else{
            WeatherJ=new JSONParser().parse(Weather.toString());
        }
        JSONObject WeatherO = (JSONObject) WeatherJ;
        return WeatherO.get(word2).toString();
    }



    public void Call_API() throws IOException, ParseException {
        URL yahoo = new URL("https://api.openweathermap.org/data/2.5/weather?lat=55.591667&lon=37.740833&appid=ba15ea04b3f184dfca3b983297c4c234&units=metric");
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;

        StringBuilder everything = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            everything.append(inputLine);
        Object obj = new JSONParser().parse(everything.toString());
        JSONObject jo = (JSONObject) obj;
        //System.out.println(Weather.toString().substring(1).replaceFirst(".$","").toString());
        System.out.println("Vreme: "+Get_from_Json("weather","main",jo));
        System.out.println("Temperatura: "+Get_from_Json("main","temp",jo));
        System.out.println("Umiditate: "+Get_from_Json("main","humidity",jo));
        System.out.println("Temperatura simtita: "+Get_from_Json("main","feels_like",jo));
        System.out.println("Presiune: "+Get_from_Json("main","pressure",jo));
        System.out.println("Viteza vantului: "+Get_from_Json("wind","speed",jo));
        System.out.println("Nori: "+Get_from_Json("clouds","all",jo));
        System.out.println(jo.toJSONString());
        in.close();



    }
}
