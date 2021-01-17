package ro.mta.se.lab.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WeatherTest {
    Weather test;
    @Before
    public void setUp(){
        test=new Weather();
        test.setName("Ro");
        test.setLat("1234");
        test.setLon("5678");
        test.setKey("Key");
        test.setGrade("1");
        test.setGrade_a("2");
        test.setUmiditate("80");
        test.setPresiune("1000");
        test.setVitezav("80");
        test.setNori("50");
        test.setVreme("Sun");
    }
    @Test
    public void getLat() {
        assertEquals(test.getLat(),"1234");
    }

    @Test
    public void getLon() {
        assertEquals(test.getLon(),"5678");
    }

    @Test
    public void getName() {
        assertEquals(test.getName(),"Ro");
    }

    @Test
    public void getKey() {
        assertEquals(test.getKey(),"Key");
    }

    @Test
    public void getGrade() {
        assertEquals(test.getGrade(),"1");
    }

    @Test
    public void getGrade_a() {
        assertEquals(test.getGrade_a(),"2");
    }

    @Test
    public void getUmiditate() {
        assertEquals(test.getUmiditate(),"80");
    }

    @Test
    public void getPresiune() {
        assertEquals(test.getPresiune(),"1000");
    }

    @Test
    public void getVitezav() {
        assertEquals(test.getVitezav(),"80");
    }

    @Test
    public void getNori() {
        assertEquals(test.getNori(),"50");
    }

    @Test
    public void getVreme() {
        assertEquals(test.getVreme(),"Sun");
    }
}