package br.edu.model.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.SQLException;
import org.sqlite.SQLiteDataSource;

public class ConnectionFactory  implements AutoCloseable{

    private static Connection connection;
    private static PreparedStatement preparedStatement;
    private static Statement statement;


    public static Connection createConnection() {
        try {
//            connection = DriverManager.getConnection(("jdbc:sqlite:db_atletas.db"));
            SQLiteDataSource db = new SQLiteDataSource();
            db.setUrl("jdbc:sqlite:db_atletas.db");
            if(connection == null){
                connection = db.getConnection();
            }
        }catch (SQLException e) { e.printStackTrace();}

        return connection;
    }


    public static PreparedStatement createPreparedStatement(String sql) {
        try {preparedStatement = createConnection().prepareStatement(sql);}
        catch (SQLException e) { e.printStackTrace();}
        return preparedStatement;
    }


    public static Statement createStatement(){
        try{
            statement = createConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return statement;

    }


    public void close() throws Exception {

        if(preparedStatement !=null){
            preparedStatement.close();
            if(statement !=null){
                statement.close();
            }
            if(connection !=null){
                connection.close();
            }
        }
    }

}
