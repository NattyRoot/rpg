/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import bean.Map;
import dao_json.JDAO_Map;
import java.awt.Frame;
import java.io.IOException;
import ui.GestionErreur;

/**
 *
 * @author b78877
 */
public class BLL_Map {
    
    private static JDAO_Map dao = new JDAO_Map();
    
    public Map getMapByNum(int num){
        try {
            return dao.getMapByNum(num); 
        } catch (IOException ex) {
            GestionErreur ge = new GestionErreur(Frame.getFrames()[0], true, ex, "getMapByNum(int num) de JDAO_Map");
            ge.setVisible(true);
            return new Map();
        }
    }
    
}
