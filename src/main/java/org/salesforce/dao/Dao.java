package org.salesforce.dao;

import org.salesforce.connection.OracleDBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {

    public static boolean insertUpdateDeleteCommand(String query){
        boolean result = false;
        Connection conn = OracleDBConnection.getConnection();

        try {
            Statement statement =  conn.createStatement();
            statement.executeUpdate(query);
            result = true;
        } catch (SQLException e) {
            System.out.println("Falha ao executar comando no banco");
            System.out.println(e);
        }
        finally {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Falha ao fechar a conexão com o banco");
            }
        }

        return result;
    }

    public static ResultSet selectCommand(String query){
        Connection conn = OracleDBConnection.getConnection();
        ResultSet resultSet = null;
        try {
            Statement statement = conn.createStatement();
            resultSet =  statement.executeQuery(query);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;
    }

}
