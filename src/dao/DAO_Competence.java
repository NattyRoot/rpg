/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Competence;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author b78877
 */
public class DAO_Competence {
    
    public List<Competence> getCompetencesByClasse(int classeId) throws SQLException{
        Connection conn = PoolConnection.getConnection();
        
        String sql = "SELECT * FROM competence WHERE Classe_ID = ?";
        
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, classeId);
        
        ResultSet rs = pst.executeQuery();
        
        List<Competence> lesComp = new ArrayList<Competence>();
        
        while(rs.next()){
            lesComp.add(new Competence(rs.getInt("Competence_ID"), rs.getString("Libelle"), rs.getInt("Classe_ID"), rs.getInt("Degat"), rs.getInt("Effet_ID")));
        }
        
        rs.close();
        pst.close();
        
        return lesComp;
    }
    
}
