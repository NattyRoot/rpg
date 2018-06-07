/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_json;

import bean.Personnage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author b78877
 */
public class JDAO_Personnage {
    
    private static GsonBuilder builder;
    private static Gson gson;
    private Personnage[] lesPersos;
    private String url = System.getProperty("user.home") + "/RPG/data/Personnage.json";
    
    static{
        builder = new GsonBuilder();
        gson = builder.create();
    }
    
    public List<Personnage> getAllPersonnages() throws IOException{
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesPersos = gson.fromJson(reader, Personnage[].class);

            List<Personnage> listPersos = new ArrayList<Personnage>();

            listPersos.addAll(Arrays.asList(lesPersos));

            return listPersos;
        }
    }
    
    public Personnage getPersonnageById(int id) throws IOException{
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesPersos = gson.fromJson(reader, Personnage[].class);

            for (Personnage p : lesPersos){
                if (p.getPersonnage_ID() == id){
                    return p;
                }
            }

            return new Personnage();
        }
    }
    
    public void newPersonnage(Personnage p) throws IOException{
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesPersos = gson.fromJson(reader, Personnage[].class);

            p.setPersonnage_ID(this.getNextID(lesPersos));

            List<Personnage> lesPersonnages = this.getAllPersonnages();
            lesPersonnages.add(p);
                        
            try (Writer writer = new FileWriter(url)){
                gson.toJson(lesPersonnages.toArray(), writer);
            }
        }
    }
    
    public void updatePersonnage(Personnage p) throws IOException{
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesPersos = gson.fromJson(reader, Personnage[].class);

            List<Personnage> listPersos = new ArrayList<Personnage>();

            for (Personnage perso : lesPersos){
                if (perso.getPersonnage_ID() == p.getPersonnage_ID()){
                    listPersos.add(p);
                }
                else{
                    listPersos.add(perso);
                }
            }
            try (Writer writer = new FileWriter(url)){
                gson.toJson(listPersos.toArray(), writer);
            }
        }
    }
    
    public void deletePersonnage(int id) throws IOException{
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesPersos = gson.fromJson(reader, Personnage[].class);

            List<Personnage> listPersos = new ArrayList<Personnage>();

            for (Personnage p : lesPersos){
                if (p.getPersonnage_ID() != id){
                    listPersos.add(p);
                }
            }
            try (Writer writer = new FileWriter(url)){
                gson.toJson(listPersos.toArray(), writer);
            }
        }
    }
    
    /**
     * Retourne l'ID du dernier personnage + 1
     * 
     * @param lesPersonnages
     * @return int id
     */
    public int getNextID(Personnage[] lesPersonnages){
        if (lesPersonnages != null && lesPersonnages.length != 0){
            return lesPersonnages[lesPersonnages.length - 1].getPersonnage_ID() + 1;
        }
        else{
            return 1;
        }
    }
}
