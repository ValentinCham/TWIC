package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.dao.VilleDAO;
import com.dao.VilleDAOImpl;

@Configuration
public class JDBCConfiguration {

	private  String url;
    private  String username;
    private  String password;

    public JDBCConfiguration() {
		// TODO Auto-generated constructor stub
	}
    JDBCConfiguration(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public static JDBCConfiguration getInstance() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {

        }

        JDBCConfiguration instance = new JDBCConfiguration("jdbc:postgresql://127.0.0.1:15432/Twic", "postgres", "postgres");
        return instance;
    }
    
    public Connection getConnection() throws SQLException {
    	
    	return DriverManager.getConnection(url, username, password);
    	} 

    // Récupération du Dao
    public VilleDAO getVilleDao() {
        return new VilleDAOImpl(this);
    }
}
