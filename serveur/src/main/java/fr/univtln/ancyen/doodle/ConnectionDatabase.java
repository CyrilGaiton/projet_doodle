package fr.univtln.ancyen.doodle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {

    private static String url = "jdbc:h2:~/test";
    private static String user = "sa";
    private static String password = "";

    private static Connection connect;


    public static Connection getInstance() throws Exception {
        if (connect == null) {
            try {
                Class.forName("org.h2.Driver");
                connect = DriverManager.getConnection(url, user, password);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connect;
    }


}
