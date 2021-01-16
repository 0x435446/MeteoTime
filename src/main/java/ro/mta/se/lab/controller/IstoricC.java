package ro.mta.se.lab.controller;
import ro.mta.se.lab.model.Istoric;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class IstoricC {

    Istoric lista;

    public IstoricC() {
        lista=new Istoric();
    }

    public void add_Oras(String oras){
        lista.Orase_vizitate.add(oras);
    }

    public String get_Orase(){
        StringBuilder history= new StringBuilder();
        for(int i=0;i<lista.Orase_vizitate.size();i++){
            history.append(lista.Orase_vizitate.get(i)).append("\n");
        }
        return history.toString();
    }

    public void Write_to_file(String Oras) throws IOException {
        String contentToAppend = get_Orase();
        Files.write(
                Paths.get("Istoric.txt"),
                contentToAppend.getBytes(),
                StandardOpenOption.WRITE);
    }

}
