/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Personnage;
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
public class DAO_Personnage {
    
    public List<Personnage> getAllPersonnages() throws SQLException{
        Connection conn = PoolConnection.getConnection();
        
        String sql = "SELECT * FROM personnage;";
        
        PreparedStatement pst = conn.prepareStatement(sql);
        
        ResultSet rs = pst.executeQuery();
        
        List<Personnage> lesPersos = new ArrayList<Personnage>();
        
        while(rs.next()){
            lesPersos.add(new Personnage(rs.getInt("Personnage_ID"), rs.getString("Nom"), rs.getInt("Race_ID"), rs.getInt("Classe_ID"), rs.getInt("Niveau_ID"), rs.getInt("Experience")));
        }
        
        return lesPersos;
    }
    
    public void newPersonnage(Personnage p) throws SQLException{
        Connection conn = PoolConnection.getConnection();
        
        String sql = "INSERT INTO personnage (Nom, Race_ID, Classe_ID) VALUES (?, ?, ?);";
        
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, p.getNom());
        pst.setInt(2, p.getRace_ID());
        pst.setInt(3, p.getClasse_ID());
        
        pst.executeUpdate();
        pst.close();
    }
    
}
