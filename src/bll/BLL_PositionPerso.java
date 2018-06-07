/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import bean.PositionPerso;
import dao_json.JDAO_PositionPerso;
import java.awt.Frame;
import java.io.IOException;
import ui.GestionErreur;

/**
 *
 * @author b78877
 */
public class BLL_PositionPerso {
    
    private static JDAO_PositionPerso dao = new JDAO_PositionPerso();
    
    public PositionPerso getPositionPerso(int persoId){
        try {
            return dao.getPositionPerso(persoId);
        } catch (IOException ex) {
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, ex, "getPositionPerso(int persoId) de JDAO_PositionPerso");
            ge.setVisible(true);
            return null;
        }
    }
    
    public void savePosPers(int x, int y, int indexMap, int persoId){
        try {
            dao.savePosPerso(x, y, indexMap, persoId);
        } catch (IOException ex) {
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, ex, "savePosPers(int x, int y, int indexMap, int persoId) de JDAO_PositionPerso");
            ge.setVisible(true);
        }
    }
    
    public void deletePosPers(int persoId){
        try{
            dao.deletePosPers(persoId);
        }
        catch(IOException ex){
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, ex, "deletePosPers(int persoId) de JDAO_PositionPerso");
            ge.setVisible(true);
        }
    }
    
}
