package ro.mta.se.lab.model;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

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


public class Weather {
    String Temp;
    String Key;
    String Lat;
    String Lon;
    String Name;


    String grade;
    String grade_a;
    String umiditate;
    String presiune;
    String vitezav;
    String nori;
    String vreme;

    FileDetails f;

    public Weather() {
        setKey("ba15ea04b3f184dfca3b983297c4c234");
        f=new FileDetails();
    }


    public String getLat() {
        return Lat;
    }

    public void setLat(String lat) {
        Lat = lat;
    }

    public String getLon() {
        return Lon;
    }

    public void setLon(String lon) {
        Lon = lon;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public FileDetails getF() {
        return f;
    }

    public void setF(FileDetails f) {
        this.f = f;
    }

    public void setKey(String key) {
        Key = key;
    }
    public String getKey() {
        return Key;
    }

    public void setTemp(String temp) {
        Temp = temp;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setGrade_a(String grade_a) {
        this.grade_a = grade_a;
    }

    public void setUmiditate(String umiditate) {
        this.umiditate = umiditate;
    }

    public void setPresiune(String presiune) {
        this.presiune = presiune;
    }

    public void setVitezav(String vitezav) {
        this.vitezav = vitezav;
    }

    public void setNori(String nori) {
        this.nori = nori;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public String getTemp() {
        return Temp;
    }

    public String getGrade() {
        return grade;
    }

    public String getGrade_a() {
        return grade_a;
    }

    public String getUmiditate() {
        return umiditate;
    }

    public String getPresiune() {
        return presiune;
    }

    public String getVitezav() {
        return vitezav;
    }

    public String getNori() {
        return nori;
    }

    public String getVreme() {
        return vreme;
    }
}
