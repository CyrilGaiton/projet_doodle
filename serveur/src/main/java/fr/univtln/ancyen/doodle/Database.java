package fr.univtln.ancyen.doodle;

import java.sql.*;

public class Database{

    public Database() {

        try {

            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");*

        } catch (SQLException e) 
        conn.close();
        

    }
}
