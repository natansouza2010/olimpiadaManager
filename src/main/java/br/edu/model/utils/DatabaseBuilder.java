package br.edu.model.utils;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {
    public static void main(String[] args) {
        buildDatabase();
    }

    private static void buildDatabase(){
        if(!Files.exists(Paths.get("db_atletas.db"))){
            build();
        }else{
            System.out.println("Banco já existe");
        }
    }

    private static void build(){
        try(Statement stmt = ConnectionFactory.createStatement()) {
            stmt.addBatch(createAtletaTable());
            stmt.executeBatch();
            stmt.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    private static String createAtletaTable(){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE atleta (\n");
        sb.append("cod TEXT NOT NULL UNIQUE, \n");
        sb.append("name TEXT ,\n");
        sb.append("modality TEXT ,\n");
        sb.append("country TEXT ,\n");
        sb.append("birthday TEXT ,\n");
        sb.append("deficiency TEXT ,\n");
        sb.append("PRIMARY KEY(cod)\n");
        sb.append("); \n");

        return sb.toString();
    }
}
