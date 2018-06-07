/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_json;

import bean.Race;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author b78877
 */
public class JDAO_Race {
    
    private JsonReader reader;
    private static GsonBuilder builder;
    private static Gson gson;
    private Race[] lesRaces;
    private String url = System.getProperty("user.home") + "/RPG/data/Race.json";
    
    static{
        builder = new GsonBuilder();
        gson = builder.create();
    }
    
    public List<Race> getAllRaces() throws IOException{
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesRaces = gson.fromJson(reader, Race[].class);

            List<Race> listRaces = new ArrayList<Race>();

            listRaces.addAll(Arrays.asList(lesRaces));

            return listRaces;
        }
    }
    
    public Race getRaceById(int id) throws IOException{
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesRaces = gson.fromJson(reader, Race[].class);

            for(Race ra : lesRaces){
                if (ra.getRace_ID() == id){
                    return ra;
                }
            }

            return new Race();
        }
    }
}
