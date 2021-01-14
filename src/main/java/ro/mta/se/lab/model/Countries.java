package ro.mta.se.lab.model;

import java.util.ArrayList;

public class Countries {
    public Countries() {
        this.Orase=new ArrayList<Orase>();
    }
    ArrayList<Orase> Orase;
    String nume;

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public ArrayList<ro.mta.se.lab.model.Orase> getOrase() {
        return Orase;
    }

    public void setOrase(ArrayList<ro.mta.se.lab.model.Orase> orase) {
        Orase = orase;
    }

    public void addOras(ro.mta.se.lab.model.Orase Oras){
        this.Orase.add(Oras);
    }
}
