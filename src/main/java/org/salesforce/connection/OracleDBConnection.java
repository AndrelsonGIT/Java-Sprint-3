package org.salesforce.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDBConnection {

    public static Connection getConnection(){


        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL", "RM552639", "241104");
            return connection;
        } catch ( ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
