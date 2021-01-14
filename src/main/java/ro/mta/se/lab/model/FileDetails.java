package ro.mta.se.lab.model;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
public class FileDetails {
    ArrayList<Countries> Country;

    public FileDetails() {
        this.Country=new ArrayList<Countries>();
    }

    public ArrayList<Countries> getCountry() {
        return Country;
    }

    public void setCountry(ArrayList<Countries> country) {
        Country = country;
    }

    public void addCountry(Countries C){
        this.Country.add(C);
    }
}
