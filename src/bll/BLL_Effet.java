/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import bean.Effet;
import dao_json.JDAO_Effet;
import java.awt.Frame;
import java.io.IOException;
import ui.GestionErreur;

/**
 *
 * @author b78877
 */
public class BLL_Effet {
    
    private JDAO_Effet jdao = new JDAO_Effet();
    
    public Effet getEffetById(int id){
        try {
            return jdao.getEffetById(id);
        } catch (IOException ex) {
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, ex, "getEffetById(id) de JDAO_Effet");
            ge.setVisible(true);
            return new Effet();
        }
    }
    
}
