package ro.mta.se.lab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import javafx.scene.control.Label;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Scanner;

import ro.mta.se.lab.model.Countries;
import ro.mta.se.lab.model.Orase;
import ro.mta.se.lab.model.Weather;



public class WeatherC {

    @FXML
    private Label grade;
    @FXML
    private TextField textbox;
    @FXML
    private Label grade_a;
    @FXML
    private Label umiditate;
    @FXML
    private Label presiune;
    @FXML
    private Label vitezav;
    @FXML
    private Label nori;
    @FXML
    private Label Vreme;
    @FXML
    private ComboBox<String> Countries;
    @FXML
    private ComboBox<String> Cities;
    @FXML
    private Label City;

    Weather WM;
    @FXML
    public void initialize() throws IOException, ParseException {
        WM=new Weather();
    }

    void populateComboBox(){
        ObservableList<String> tari= FXCollections.observableArrayList();
        for(int i=0;i<WM.getF().getCountry().size();i++){
            tari.add(WM.getF().getCountry().get(i).getNume());
        }
        Countries.setItems(tari);
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

    public void readFile(){
        try {
            File myObj = new File(textbox.getText());
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                var x=data.split(" ");
                System.out.println(x.length);
                boolean ok=false;
                int index=-1;
                if(this.WM.getF().getCountry()!=null){
                    for(int i=0;i<this.WM.getF().getCountry().size();i++) {
                        if (this.WM.getF().getCountry().get(i).getNume().equals(x[4])) {
                            ok = true;
                            ro.mta.se.lab.model.Orase oras=new ro.mta.se.lab.model.Orase();
                            oras.setNume(x[1]);
                            oras.setLat(x[2]);
                            oras.setLon(x[3]);
                            this.WM.getF().getCountry().get(i).addOras(oras);
                            break;
                        }
                    }
                }
                if (!ok){
                    Countries tara=new Countries();
                    tara.setNume(x[4]);
                    ro.mta.se.lab.model.Orase oras=new ro.mta.se.lab.model.Orase();
                    oras.setNume(x[1]);
                    oras.setLat(x[2]);
                    oras.setLon(x[3]);
                    tara.addOras(oras);
                    this.WM.getF().addCountry(tara);
                }
                System.out.println("-------------------");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        System.out.println("-------------------");
        System.out.println(this.WM.getF().getCountry().get(1).getOrase().size());
    }

    public void set_data(){
        presiune.setText(this.WM.getPresiune());
        grade.setText(this.WM.getGrade());
        grade_a.setText(this.WM.getGrade_a());
        umiditate.setText(this.WM.getUmiditate());
        vitezav.setText(this.WM.getVitezav());
        nori.setText(this.WM.getNori());
        Vreme.setText(this.WM.getVreme());
    }
    public void Call_API() throws IOException, ParseException {
        Weather x= new Weather();
        System.out.println(x.getKey());
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?lat="+
                this.WM.getLat()+"&lon="+this.WM.getLon()+"&units=metric&appid="+x.getKey());
        URLConnection yc = url.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;
        StringBuilder everything = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            everything.append(inputLine);
        Object obj = new JSONParser().parse(everything.toString());
        JSONObject jo = (JSONObject) obj;
        City.setText(this.WM.getName());
        this.WM.setVreme(Get_from_Json("weather","main",jo));
        this.WM.setGrade(""+Get_from_Json("main","temp",jo)+"°");
        this.WM.setUmiditate("Umiditate: "+Get_from_Json("main","humidity",jo));
        this.WM.setGrade_a("Temperatura simtita: "+Get_from_Json("main","feels_like",jo));
        this.WM.setPresiune("Presiune: "+Get_from_Json("main","pressure",jo));
        this.WM.setVitezav("Viteza vantului: "+Get_from_Json("wind","speed",jo)+" km/h");
        this.WM.setNori("Nori: "+Get_from_Json("clouds","all",jo));
        System.out.println(jo.toJSONString());
        in.close();
    }

    public void hBtn(ActionEvent actionEvent) throws IOException, ParseException {
        if(!Cities.getSelectionModel().isEmpty()) {
            this.Call_API();
            this.set_data();
        }
    }

    public void lBtn(ActionEvent actionEvent) throws IOException, ParseException {
        if(!textbox.getText().isEmpty()) {
            this.readFile();
            this.populateComboBox();
        }
    }

    public void setCity(ActionEvent actionEvent) {
        if(!Countries.getSelectionModel().isEmpty()){
            ObservableList<String> orase= FXCollections.observableArrayList();
            String tara=Countries.getValue().toString();
            for(int i=0;i<this.WM.getF().getCountry().size();i++){
                if(this.WM.getF().getCountry().get(i).getNume().equals(tara)){
                    for(int j=0;j<this.WM.getF().getCountry().get(i).getOrase().size();j++){
                        orase.add(this.WM.getF().getCountry().get(i).getOrase().get(j).getNume());
                    }
                }
            }
            Cities.setItems(orase);
        }
    }

    public void setDatas(ActionEvent actionEvent) {
        if(!Cities.getSelectionModel().isEmpty()){
            String oras=Cities.getValue().toString();
            for(int i=0;i<this.WM.getF().getCountry().size();i++){
                for(int j=0;j<this.WM.getF().getCountry().get(i).getOrase().size();j++){
                    if(this.WM.getF().getCountry().get(i).getOrase().get(j).getNume().equals(oras)){
                        this.WM.setName(oras);
                        this.WM.setLat(this.WM.getF().getCountry().get(i).getOrase().get(j).getLat());
                        this.WM.setLon(this.WM.getF().getCountry().get(i).getOrase().get(j).getLon());
                        System.out.println(this.WM.getLat());
                        System.out.println(this.WM.getLon());
                    }
                }
            }
        }
    }
}