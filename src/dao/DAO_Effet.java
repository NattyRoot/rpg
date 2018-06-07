/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import bean.Effet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author b78877
 */
public class DAO_Effet {
    
    public Effet getEffetById(int id) throws SQLException{
        Connection conn = PoolConnection.getConnection();
        
        String sql = "SELECT * FROM effet WHERE effet_id = ?";
        
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setInt(1, id);
        
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()){
            return new Effet(rs.getInt("effet_id"), rs.getString("libelle"), rs.getInt("degat"), rs.getInt("soin"), rs.getInt("duree"), rs.getString("HtmlDesc"));
        }
        else{
            return new Effet();
        }
    }
    
}
