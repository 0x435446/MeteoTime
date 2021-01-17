package ro.mta.se.lab.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ro.mta.se.lab.model.Countries;
import ro.mta.se.lab.model.Weather;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFileC {

    Weather WM;
    @FXML
    private TextField textbox;
    public String FileName;
    public ReadFileC(String FileName) {
        this.WM = new Weather();
        this.FileName=FileName;
    }
    /**
     *
     * reprezinta functia de citire a fisierului
     * si populare aobiectylui de tip Weather
     *
     */
    public Weather readFile() {
        try {
            File myObj = new File(this.FileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
                var x = data.split(" ");
                System.out.println(x.length);
                boolean ok = false;
                int index = -1;
                if (this.WM.getF().getCountry() != null) {
                    for (int i = 0; i < this.WM.getF().
                            getCountry().size(); i++) {
                        if (this.WM.getF().getCountry().
                                get(i).getNume().equals(x[4])) {
                            ok = true;
                            ro.mta.se.lab.model.
                                    Orase oras = new ro.mta.se.lab.model.Orase();
                            oras.setNume(x[1]);
                            oras.setLat(x[2]);
                            oras.setLon(x[3]);
                            this.WM.getF().getCountry().get(i).addOras(oras);
                            break;
                        }
                    }
                }
                if (!ok) {
                    Countries tara = new Countries();
                    tara.setNume(x[4]);
                    ro.mta.se.lab.model.
                            Orase oras = new ro.mta.se.lab.model.Orase();
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
        return this.WM;
    }

}
