/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import bean.Competence;
import dao_json.JDAO_Competence;
import java.awt.Frame;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import ui.GestionErreur;

/**
 *
 * @author b78877
 */
public class BLL_Competence {
    
    private JDAO_Competence jdao = new JDAO_Competence();
    
    public List<Competence> getCompetenceByClasse(int classeId){
        try {
            return jdao.getCompByClasse(classeId);
        } catch (IOException ex) {
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, ex, "getCompByClasse(id) de JDAO_Competence");
            ge.setVisible(true);
            return new ArrayList<Competence>();
        }
    }

    public Competence getCompetenceById(int id) {
        try {
            return jdao.getCompById(id);
        } catch (IOException ex) {
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, ex, "getCompById(id) de JDAO_Competence");
            ge.setVisible(true);
            return new Competence();
        }
    }
    
}
