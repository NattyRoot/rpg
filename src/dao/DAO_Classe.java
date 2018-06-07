/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import bean.Classe;
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
public class DAO_Classe {
    
    public List<Classe> getAllClasses() throws SQLException{
        Connection conn = PoolConnection.getConnection();
        
        String sql = "SELECT * FROM classe;";
        
        PreparedStatement pst = conn.prepareStatement(sql);
        
        ResultSet rs = pst.executeQuery();
        
        List<Classe> lesClasses = new ArrayList<Classe>();
        
        while(rs.next()){
            lesClasses.add(new Classe(rs.getInt("Classe_ID"), rs.getString("Libelle"), rs.getInt("Attaque"), rs.getInt("Defense"), rs.getInt("Vitesse")));
        }
        
        pst.close();
        rs.close();
        
        return lesClasses;
    }
    
    public Classe getClasseById(int id) throws SQLException{
        Connection conn = PoolConnection.getConnection();
        
        String sql = "SELECT * FROM classe WHERE Classe_ID = ? ;";
        
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);
        
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()){
            return new Classe(rs.getInt("Classe_ID"), rs.getString("Libelle"), rs.getInt("Attaque"), rs.getInt("Defense"), rs.getInt("Vitesse"));
        }
        else{
            return new Classe();
        }
        
    }
}
