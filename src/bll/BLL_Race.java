/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import bean.Race;
import dao_json.JDAO_Race;
import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ui.GestionErreur;

/**
 *
 * @author b78877
 */
public class BLL_Race {
    
    private JDAO_Race jdao = new JDAO_Race();
    
    public List<Race> getAllRaces(){
        try {        
            return jdao.getAllRaces();
        } catch (IOException ex) {
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, ex, "getAllRaces() de JDAO_Race");
            ge.setVisible(true);
            return new ArrayList<Race>();
        }
    }
    
    public Race getRaceById(int id){
        try {
            return jdao.getRaceById(id);
        } catch (IOException ex) {
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, ex, "getRaceById(id) de JDAO_Race");
            ge.setVisible(true);
            return new Race();
        }
    }
}
