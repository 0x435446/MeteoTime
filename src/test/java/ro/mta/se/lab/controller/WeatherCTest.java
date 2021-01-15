package ro.mta.se.lab.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ro.mta.se.lab.model.Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class WeatherCTest {
    JSONObject jo=new JSONObject();
    String lat= "55.591667";
    String lon= "37.740833";

    String Json="{\"coord\":{\"lon\":37.7408,\"lat\":55.5917},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"light snow\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":-13.97,\"feels_like\":-18.84,\"temp_min\":-15,\"temp_max\":-13,\"pressure\":1008,\"humidity\":78},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":80},\"snow\":{\"1h\":0.21},\"clouds\":{\"all\":75},\"dt\":1610744547,\"sys\":{\"type\":1,\"id\":9029,\"country\":\"RU\",\"sunrise\":1610776005,\"sunset\":1610803825},\"timezone\":10800,\"id\":819827,\"name\":\"Razvilka\",\"cod\":200}\n";
    String lat2= "55.591667";
    String lon2= "37.740833";
    WeatherC moktest;
    JSONObject jo2;


    @Test
    public void get_from_Json() throws ParseException, IOException {
        URL yahoo = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon+"&units=metric&appid=ba15ea04b3f184dfca3b983297c4c234");
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;
        StringBuilder everything = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            everything.append(inputLine);
        System.out.println(everything);
        Object obj = new JSONParser().parse(everything.toString());
        JSONObject jo = (JSONObject) obj;
        WeatherC test=new WeatherC();
        assertEquals(test.Get_from_Json("weather","main",jo),"Snow");
        //assertEquals(test.Get_from_Json("weather","main",jo),"Sun");
    }

    @Before
    public void setup() throws Exception{
        moktest=mock(WeatherC.class);
        JSONObject jo2=new JSONObject();
        jo2= (JSONObject) new JSONParser().parse(Json);
        when(moktest.Get_from_Json("weather","main",jo2)).thenReturn("Snow");

    }

    @Test
    public void get_from_Json_Mok() throws ParseException {
        JSONObject jo2=new JSONObject();
        jo2= (JSONObject) new JSONParser().parse(Json);
        assertEquals(moktest.Get_from_Json("weather","main",jo2),"Snow");
        //assertEquals(moktest.Get_from_Json("weather","main",jo2),"Sun");
    }
}