package ro.mta.se.lab.model;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
public class Cities {
    ArrayList<ArrayList<String>> Cities;
    ArrayList<String> Row;

    public Cities() {

    }

    public void setCities(ArrayList<ArrayList<String>> cities) {
        Cities = cities;
    }


    public ArrayList<ArrayList<String>> getCities() {
        return Cities;
    }

    public void setRow(ArrayList<String> row) {
        Row = row;
    }

    public ArrayList<String> getRow() {
        return Row;
    }
}
