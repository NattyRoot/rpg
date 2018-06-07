/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_json;

import bean.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author b78877
 */
public class JDAO_Map {
    private static GsonBuilder builder;
    private static Gson gson;
    private Map[] lesMaps;
    private String url = System.getProperty("user.home") + "/RPG/data/Map.json";

    static{
        builder = new GsonBuilder();
        gson = builder.create();
    }
    
    public Map getMapByNum(int num) throws IOException{        
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesMaps = gson.fromJson(reader, Map[].class);

            return lesMaps[num];
        }
    }
    
}
