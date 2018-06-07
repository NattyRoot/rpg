/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Race;
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
public class DAO_Race {
    
    public List<Race> getAllRaces() throws SQLException{
        Connection conn = PoolConnection.getConnection();
        
        String sql = "SELECT * FROM race;";
        
        PreparedStatement pst = conn.prepareStatement(sql);
        
        ResultSet rs = pst.executeQuery();
        
        List<Race> lesRaces = new ArrayList<Race>();
        
        while (rs.next()){
            lesRaces.add( new Race(rs.getInt("Race_ID"), rs.getString("Libelle"), rs.getInt("Pv"), rs.getInt("Esquive"), rs.getInt("Precision")));
        }
        
        rs.close();
        pst.close();
        
        return lesRaces;
    }
    
    public Race getRaceById(int id) throws SQLException{
        Connection conn = PoolConnection.getConnection();
        
        String sql = "SELECT * FROM race WHERE Race_ID = ? ;";
        
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);
        
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()){
            return new Race(rs.getInt("Race_ID"), rs.getString("Libelle"), rs.getInt("Pv"), rs.getInt("Esquive"), rs.getInt("Precision"));
        }
        else{
            return new Race();
        }
    }
}
