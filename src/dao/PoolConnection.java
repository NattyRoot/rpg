package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PoolConnection {
	
	static Connection cnx = null;
	
	public static Connection getConnection() {
            try{
                if(cnx ==null || cnx.isClosed()){
                    Class.forName("com.mysql.jdbc.Driver");

                    String url= "jdbc:mysql://localhost:3306/rpg";
                    
                    cnx = DriverManager.getConnection(url, "PEPSIDACC", "PedfSedfD37");
                    
                    return cnx;
                }
                else{
                    return cnx;
                }

            }
            catch (Exception e){
                    e.printStackTrace();
            }
            return null;
	}
	
	public static void closeConnection(){
            if (cnx !=null){
                try {
                    cnx.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
	}

}

