/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_json;

import bean.Effet;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author b78877
 */
public class JDAO_Effet {
    
    private static GsonBuilder builder;
    private static Gson gson;
    private Effet[] lesEffets;
    private String url = System.getProperty("user.home") + "/RPG/data/Effet.json";
    
    static{
        builder = new GsonBuilder();
        gson = builder.create();
    }
    
    public Effet getEffetById(int id) throws IOException{
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesEffets = gson.fromJson(reader, Effet[].class);

            for (Effet ef : lesEffets){
                if (ef.getEffet_ID() == id){
                    return ef;
                }
            }

            return new Effet();
        }
    }
    
}
