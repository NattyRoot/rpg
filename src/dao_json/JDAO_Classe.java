/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_json;

import bean.Classe;
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
public class JDAO_Classe {
    
    private static GsonBuilder builder;
    private static Gson gson;
    private Classe[] lesClasses;
    private String url = System.getProperty("user.home") + "/RPG/data/Classe.json";
    
    static{
        builder = new GsonBuilder();
        gson = builder.create();
    }
    
    public Classe getClasseById(int id) throws IOException{
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesClasses = gson.fromJson(reader, Classe[].class);

            for (Classe c : lesClasses){
                if (c.getClasse_ID() == id){
                    return c;
                }
            }
            return new Classe();
        }
    }
    
    public List<Classe> getAllClasses() throws IOException{
        try(JsonReader reader = new JsonReader(new FileReader(url))){
            lesClasses = gson.fromJson(reader, Classe[].class);

            List<Classe> listClasse = new ArrayList<Classe>();

            for (Classe cl : lesClasses){
                listClasse.add(cl);
            }

            return listClasse;
        }
    }
    
    
    
    
    //----SAUVEGARDE POUR SE RAPPELER COMMENT FAIRE LES CREATE/UPDATE/DELETE-----
    
    
    
    
    
//    public void add(Classe c) throws IOException{
//        lesClasses = gson.fromJson(reader, Classe[].class);
//        
//        List<Classe> listClasse = this.getAllClasses();
//        
//        listClasse.add(c);
//        
//        System.out.println(listClasse);
//        
//        try (Writer writer = new FileWriter(".\\src\\json\\Classe.json")){
//            gson.toJson(listClasse.toArray(), writer);
//        }
//    }
    
//    public void update(Classe c) throws IOException{
//        lesClasses = gson.fromJson(reader, Classe[].class);
//        
//        List<Classe> listClasse = new ArrayList<Classe>();
//        
//        for(Classe cl : lesClasses){
//            if (cl.getClasse_ID() == c.getClasse_ID()){
//                listClasse.add(c);
//            }
//            else{
//                listClasse.add(cl);
//            }
//        }
//        
//        try (Writer writer = new FileWriter(".\\src\\json\\Classe.json")){
//            gson.toJson(listClasse.toArray(), writer);
//        }
//    }
    
//    public void delete(int id) throws IOException{
//        lesClasses = gson.fromJson(reader, Classe[].class);
//        
//        List<Classe> listClasse = new ArrayList<Classe>();
//        
//        for(Classe cl : lesClasses){
//            if (cl.getClasse_ID() != id){
//                listClasse.add(cl);
//            }
//        }
//        
//        try (Writer writer = new FileWriter(".\\src\\json\\Classe.json")){
//            gson.toJson(listClasse.toArray(), writer);
//        }
//    }
}
