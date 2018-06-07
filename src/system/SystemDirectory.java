/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import bean.Classe;
import bean.Competence;
import bean.Effet;
import bean.Map;
import bean.Personnage;
import bean.PositionPerso;
import bean.Race;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

/**
 *
 * @author b78877
 */
public class SystemDirectory {
    
    private static GsonBuilder builder;
    private static Gson gson;
    
    static{
        builder = new GsonBuilder();
        gson = builder.create();
    }
    
    public void createDir(){
        String path = System.getProperty("user.home") + "/RPG/data";
        File directory = new File(path);
        
        if (!directory.exists()){
            directory.mkdirs();
        }
        
        try{
            if (!new File(path + "/Classe.json").exists()){
                createJSon("Classe.json");
            }
            if (!new File(path + "/Competence.json").exists()){
                createJSon("Competence.json");
            }
            if (!new File(path + "/Effet.json").exists()){
                createJSon("Effet.json");
            }
            if (!new File(path + "/Map.json").exists()){
                createJSon("Map.json");
            }
            if (!new File(path + "/Personnage.json").exists()){
                createJSon("Personnage.json");
            }
            if (!new File(path + "/PositionPerso.json").exists()){
                createJSon("PositionPerso.json");
            }
            if (!new File(path + "/Race.json").exists()){
                createJSon("Race.json");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void createJSon(String fileName) throws IOException{
        String destination = System.getProperty("user.home") + "/RPG/data";
        URL source = getClass().getResource("/json/"+fileName);
        
        try(JsonReader reader = new JsonReader(new InputStreamReader(source.openStream(), StandardCharsets.UTF_8))){
            switch(fileName.split(Pattern.quote("."))[0]){
                case "Classe":
                    Classe[] lesClasses = gson.fromJson(reader, Classe[].class);

                    try(Writer writer = new FileWriter(destination + "/" + fileName)){
                        gson.toJson(lesClasses, writer);
                    }

                    break;
                case "Competence":
                    Competence[] lesComp = gson.fromJson(reader, Competence[].class);

                    try(Writer writer = new FileWriter(destination + "/" + fileName)){
                        gson.toJson(lesComp, writer);
                    }

                    break;
                case "Effet":
                    Effet[] lesEffets = gson.fromJson(reader, Effet[].class);

                    try(Writer writer = new FileWriter(destination + "/" + fileName)){
                        gson.toJson(lesEffets, writer);
                    }

                    break;
                case "Map":
                    Map[] lesMaps = gson.fromJson(reader, Map[].class);

                    try(Writer writer = new FileWriter(destination + "/" + fileName)){
                        gson.toJson(lesMaps, writer);
                    }

                    break;
                case "Personnage":
                    Personnage[] lesPersos = gson.fromJson(reader, Personnage[].class);

                    try(Writer writer = new FileWriter(destination + "/" + fileName)){
                        gson.toJson(lesPersos, writer);
                    }

                    break;
                case "PositionPerso":
                    PositionPerso[] lesPos = gson.fromJson(reader, PositionPerso[].class);

                    try(Writer writer = new FileWriter(destination + "/" + fileName)){
                        gson.toJson(lesPos, writer);
                    }

                    break;
                case "Race":
                    Race[] lesRaces = gson.fromJson(reader, Race[].class);

                    try(Writer writer = new FileWriter(destination + "/" + fileName)){
                        gson.toJson(lesRaces, writer);
                    }

                    break;
                default:
                    break;
            }
        }
    }
    
}
