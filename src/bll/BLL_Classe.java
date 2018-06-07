/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import bean.Classe;
import dao_json.JDAO_Classe;
import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ui.GestionErreur;

/**
 *
 * @author b78877
 */
public class BLL_Classe {
    
    private JDAO_Classe jdao = new JDAO_Classe();
    
    public List<Classe> getAllClasses(){
        try {
            return jdao.getAllClasses();
        } catch (IOException ex) {
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, ex, "getAllClasses() de JDAO_Classe");
            ge.setVisible(true);
            return new ArrayList<Classe>();
        }
    }
    
    public Classe getClasseById(int id){
        try {
            return jdao.getClasseById(id);
        } catch (IOException ex) {
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, ex, "getClasseById(id) de JDAO_Classe");
            ge.setVisible(true);
            return new Classe();
        }
    }
}
