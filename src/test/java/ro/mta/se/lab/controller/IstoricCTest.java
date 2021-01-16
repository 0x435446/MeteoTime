package ro.mta.se.lab.controller;

import org.hamcrest.core.Is;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Before;
import org.junit.Test;
import ro.mta.se.lab.model.Countries;
import ro.mta.se.lab.model.Weather;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
public class IstoricCTest {

    IstoricC x;
    WeatherC moktest;
    Weather mokW;
    ReadFileC mokfile;
    Weather WM;
    @Before
    public void setUp() throws Exception {
        x=new IstoricC();
        x.add_Oras("Pitesti");
        x.add_Oras("Bucuresti");
        x.add_Oras("Berlin");
    }

    @Test
    public void get_Orase() {
        assertEquals(x.get_Orase(),"Pitesti\nBucuresti\nBerlin\n");
        //assertEquals(x.get_Orase(),"Oras\nPitesti\nBucuresti\nBerlin\n");
    }


    @Before
    public void setUp_Mok() throws Exception{
        this.WM=new Weather();
        Countries tara = new Countries();
        tara.setNume("RO");
        ro.mta.se.lab.model.
                Orase oras = new ro.mta.se.lab.model.Orase();
        oras.setNume("Pitesti");
        oras.setLat("1234");
        oras.setLon("5678");
        tara.addOras(oras);
        this.WM.getF().addCountry(tara);

        moktest=mock(WeatherC.class);
        mokfile=mock(ReadFileC.class);
        mokfile.FileName="filename.txt";
        mokfile.WM=this.WM;
        when(mokfile.readFile()).thenReturn(this.WM);
    }

    @Test
    public void test_ReadFile_Mock() {
        assertEquals(mokfile.readFile(),this.WM);
    }
}