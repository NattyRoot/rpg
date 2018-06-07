/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import bean.Personnage;
import dao_json.JDAO_Personnage;
import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ui.GestionErreur;

/**
 *
 * @author b78877
 */
public class BLL_Personnage {
    
    private JDAO_Personnage jdao = new JDAO_Personnage();
    
    public List<Personnage> getAllPersonnages(){        
        try {
            return jdao.getAllPersonnages();
        } catch (IOException e) {
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, e, "getAllPersonnage() de JDAO_Personnage");
            ge.setVisible(true);
            return new ArrayList<Personnage>();
        }
    }
    
    public Personnage getPersonnageById(int id){
        try {
            return jdao.getPersonnageById(id);
        } catch (IOException ex) {
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, ex, "getPersonnageById(int id) de JDAO_Personnage");
            ge.setVisible(true);
            return new Personnage();
        }
    }
    
    public void newPersonnage(Personnage p){
        try {
            jdao.newPersonnage(p);
        } catch (IOException e) {
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, e, "newPersonnage(p) de JDAO_Personnage");
            ge.setVisible(true);
        }
    }
    
    public void updatePersonnage(Personnage p){
        try {
            jdao.updatePersonnage(p);
        } catch (IOException ex) {
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, ex, "updatePersonnage(p) de JDAO_Personnage");
            ge.setVisible(true);
        }
    }
    
    public void deletePersonnage(int id){
        try {
            jdao.deletePersonnage(id);
            (new BLL_PositionPerso()).deletePosPers(id);
        } catch (IOException ex) {
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, ex, "deletePersonnage(p) de JDAO_Personnage");
            ge.setVisible(true);
        }
    }
}
