/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_json;

import bean.PositionPerso;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b78877
 */
public class JDAO_PositionPerso {
    
    private static GsonBuilder builder;
    private static Gson gson;
    private PositionPerso[] lesPositions;
    private String url = System.getProperty("user.home") + "/RPG/data/PositionPerso.json";
    
    static{
        builder = new GsonBuilder();
        gson = builder.create();
    }
    
    public void savePosPerso(int x, int y, int indexMap, int persoId) throws IOException{
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesPositions = gson.fromJson(reader, PositionPerso[].class);
            
            List<PositionPerso> listPos = new ArrayList<PositionPerso>();
            boolean rowFound = false;
            
            for (PositionPerso pp : lesPositions){
                if (pp.getPersonnage_ID() == persoId){
                    pp.setIndexMap(indexMap);
                    pp.setPersonnage_ID(persoId);
                    pp.setX(x);
                    pp.setY(y);
                    
                    rowFound = true;
                }
                listPos.add(pp);
            }
            
            if (!rowFound){
                listPos.add(new PositionPerso(persoId, indexMap, x, y));
            }
            
            try(Writer writer = new FileWriter(url)){
                gson.toJson(listPos.toArray(), writer);
            }
        }
    }
    
    public PositionPerso getPositionPerso(int persoId) throws IOException{
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesPositions = gson.fromJson(reader, PositionPerso[].class);
            
            PositionPerso pp = null;
            
            for (PositionPerso p : lesPositions){
                if (p.getPersonnage_ID() == persoId){
                    pp = p;
                }
            }
            return pp;
        }
    }

    public void deletePosPers(int persoId) throws IOException{
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesPositions = gson.fromJson(reader, PositionPerso[].class);
            
            List<PositionPerso> listPos = new ArrayList<PositionPerso>();
            
            for (PositionPerso pp : lesPositions){
                if (pp.getPersonnage_ID() != persoId){
                    listPos.add(pp);
                }
            }
            
            try(Writer writer = new FileWriter(url)){
                gson.toJson(listPos.toArray(), writer);
            }
        }
    }
}
