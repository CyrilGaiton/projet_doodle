package fr.univtln.ancyen.doodle;

import java.sql.*;

public class Database{

    public Database() {

        Connection conn;

        try {

            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

        } catch (SQLException e) {
            conn.close();
        }

    }
}
