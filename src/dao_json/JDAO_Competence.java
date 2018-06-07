/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_json;

import bean.Competence;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b78877
 */
public class JDAO_Competence {
    
    private static GsonBuilder builder;
    private static Gson gson;
    private Competence[] lesComp;
    private String url = System.getProperty("user.home") + "/RPG/data/Competence.json";
    
    static{
        builder = new GsonBuilder();
        gson = builder.create();
    }
    
    public List<Competence> getCompByClasse(int classeId) throws IOException{
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesComp = gson.fromJson(reader, Competence[].class);

            List<Competence> listComp = new ArrayList<Competence>();

            for (Competence co : lesComp){
                if (co.getClasse_ID() == classeId){
                    listComp.add(co);
                }
            }

            return listComp;
        }
    }
    
    public Competence getCompById(int id) throws IOException{
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesComp = gson.fromJson(reader, Competence[].class);

            for (Competence co : lesComp){
                if (co.getCompetence_ID() == id){
                    return co;
                }
            }

            return new Competence();
        }
    }
}
