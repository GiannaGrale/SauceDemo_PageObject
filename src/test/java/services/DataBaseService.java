package services;

import org.testng.log4testng.Logger;

import java.sql.*;
import java.util.List;

public class DataBaseService {
    public static Logger logger = Logger.getLogger(DataBaseService.class);

    static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    static final String USER = "postgres";
    static final String PSW = "1234postgres";

    Connection connection = null;
    Statement statement = null;

    public DataBaseService() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, USER, PSW);
        } catch (ClassNotFoundException e) {
            System.out.println((e.toString()));
        } catch (SQLException throwables) {
            System.out.println((throwables.toString()));
        }

        if (connection != null) {
            System.out.println("Connection is successful...");
        } else {
            System.out.println("Error");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        try {
            if (statement == null) {
                statement = getConnection().createStatement();
            }
        } catch (SQLException throwables) {
            System.out.println((throwables.toString()));
        }
        return statement;
    }

    public boolean executeSQL(String sql) {
        try {
            return getStatement().execute(sql);
        } catch (SQLException throwables) {
            System.out.println((throwables.toString()));
        }
        return false;
    }


    public ResultSet executeQuery(String sql) {
        try {
            return getStatement().executeQuery(sql);
        } catch (SQLException throwables) {
            System.out.println((throwables.toString()));
        }
        return null;
    }


    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            logger.info(throwables.toString());
        }
    }
}
