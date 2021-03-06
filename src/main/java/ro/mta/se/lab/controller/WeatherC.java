package ro.mta.se.lab.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import javafx.scene.control.Label;


import java.io.*;
import java.net.URL;
import java.net.URLConnection;

import ro.mta.se.lab.model.Weather;

/**
 *
 * Aceasta clasa reprezinta clasa ce rezolva interactiunea
 * principala dintre utilizator si UI
 * @author Cujba Mihai-Catalin
 */

public class WeatherC {
    /**
     *
     * definirea variabilelor de legatura dintre cod si UI
     *
     */
    @FXML
    private TextField textbox;
    @FXML
    private Label grade;
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



    IstoricC istoric;
    Weather WM;
    /**
     *
     * functia apelata la rularea programului
     *
     */
    @FXML
    public void initialize() throws IOException, ParseException {
        WM=new Weather();
        istoric=new IstoricC();
    }

    /**
     *
     * functia ce are rolul de a popula lista de tari
     *
     */
    void populateComboBox(){
        ObservableList<String> tari= FXCollections.observableArrayList();
        for(int i=0;i<WM.getF().getCountry().size();i++){
            tari.add(WM.getF().getCountry().get(i).getNume());
        }
        Countries.setItems(tari);
    }
    /**
     *
     * functia ce are rolul de parsare a Json-ului
     * @param word1 reprezinta radacina json-ului
     * @param word2 reprezinta campul din json cautat
     * @param jo reprezinta json-ul
     */
    public String Get_from_Json(String word1,
                                String word2, JSONObject jo) throws ParseException {
        Object Weather= new JSONParser().parse(jo.get(word1).toString());
        Object WeatherJ=new Object();
        if(Weather.toString().charAt(0)=='['){
            WeatherJ=new JSONParser().parse(Weather.toString().
                    substring(1).replaceFirst(".$","").toString());
        }
        else{
            WeatherJ=new JSONParser().parse(Weather.toString());
        }
        JSONObject WeatherO = (JSONObject) WeatherJ;
        return WeatherO.get(word2).toString();
    }
    /**
     *
     * functia are rolul de a apela functia de citire a fisierului setat
     * in obiectul de tip ReadFileC
     * @param x - parametrul functiei ce se ocupa cu citirea fisierului
     *          de intrare
     *
     */
    public Weather readFile(ReadFileC x){
        return x.readFile();
    }


    /**
     *
     * reprezinta functia de setare a datelor in interfata grafica
     *
     */


    public void set_data() throws IOException {
        presiune.setText(this.WM.getPresiune());
        grade.setText(this.WM.getGrade());
        grade_a.setText(this.WM.getGrade_a());
        umiditate.setText(this.WM.getUmiditate());
        vitezav.setText(this.WM.getVitezav());
        nori.setText(this.WM.getNori());
        Vreme.setText(this.WM.getVreme());
        istoric.Write_to_file();
    }

    /**
     *
     * reprezinta functia de trimitere a request-ului catre API-ul
     * ce are rol de trimitere a datelor meteorologice si popularea
     * obiectului de tip Weather cu valorile extrase din json
     *
     */

    public void Call_API() throws IOException, ParseException {
        Weather x= new Weather();
        System.out.println(x.getKey());
        URL url =
                new URL("https://api.openweathermap.org/data/2.5/weather?lat="+
                this.WM.getLat()+"&lon="+
                        this.WM.getLon()+"&units=metric&appid="+x.getKey());
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
        this.WM.setVreme(
                Get_from_Json("weather","main",jo));
        this.WM.setGrade(
                ""+Get_from_Json("main","temp",jo)+"°");
        this.WM.setUmiditate(
                "Umiditate: "+Get_from_Json("main","humidity",jo));
        this.WM.setGrade_a(
                "Temperatura simtita: "+Get_from_Json("main","feels_like",jo));
        this.WM.setPresiune(
                "Presiune: "+Get_from_Json("main","pressure",jo));
        this.WM.setVitezav(
                "Viteza vantului: "+Get_from_Json("wind","speed",jo)+" km/h");
        this.WM.setNori(
                "Nori: "+Get_from_Json("clouds","all",jo));
        this.istoric.add_Oras(this.WM.getName()+": "+
                this.WM.getGrade()+" °;"+" Umiditate: "+
                this.WM.getUmiditate()+"; Presiune: "+
                this.WM.getPresiune()+";");
        System.out.println(jo.toJSONString());
        in.close();
    }
    /**
     *
     * reprezinta handler-ul pentru actiunea butonului de search
     *
     */
    public void hBtn(ActionEvent actionEvent)
            throws IOException, ParseException {
        if(!Cities.getSelectionModel().isEmpty()) {
            this.Call_API();
            this.set_data();
        }
    }
    /**
     *
     * reprezinta handler-ul pentru actiunea butonului de load
     *
     */
    public void lBtn(ActionEvent actionEvent)
            throws IOException, ParseException {
        if(!textbox.getText().isEmpty()) {
            ReadFileC x= new ReadFileC(textbox.getText().toString());
            this.WM=this.readFile(x);
            this.populateComboBox();
        }
    }
    /**
     *
     * reprezinta handler-ul pentru actiunea de alegere a tarii
     * are rolul de a popula lista de orase
     *
     */
    public void setCity(ActionEvent actionEvent) {
        if(!Countries.getSelectionModel().isEmpty()){
            ObservableList<String> orase= FXCollections.observableArrayList();
            String tara=Countries.getValue().toString();
            for(int i=0;i<this.WM.getF().getCountry().size();i++){
                if(this.WM.getF().getCountry().get(i).getNume().equals(tara)){
                    for(int j=0;j<this.WM.getF().
                            getCountry().get(i).getOrase().size();j++){
                        orase.add(
                                this.WM.getF().
                                        getCountry().
                                        get(i).getOrase().
                                        get(j).getNume()
                        );
                    }
                }
            }
            Cities.setItems(orase);
        }
    }
    /**
     *
     * reprezinta handler-ul pentru actiunea alegerii orasului
     *
     */
    public void setDatas(ActionEvent actionEvent) {
        if(!Cities.getSelectionModel().isEmpty()){
            String oras=Cities.getValue().toString();
            for(int i=0;i<this.WM.getF().getCountry().size();i++){
                for(int j=0;j<this.WM.getF().getCountry().
                        get(i).getOrase().size();j++){
                    if(this.WM.getF().getCountry().get(i).
                            getOrase().get(j).getNume().equals(oras)){
                        this.WM.setName(oras);
                        this.WM.setLat(this.WM.getF().getCountry().
                                get(i).getOrase().get(j).getLat());
                        this.WM.setLon(this.WM.getF().getCountry().
                                get(i).getOrase().get(j).getLon());
                    }
                }
            }
        }
    }
}
