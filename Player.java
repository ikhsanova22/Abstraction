package Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Player {

    String name;
    double score;
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public Player(String name){
        this.name = name;
        this.score = 0;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brainfight?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC", "root", "");
            statement = connection.createStatement();

        }catch(Exception ex){
            System.out.println("Error: " + ex);
        }

    }
    public void insert(int id, String nnn){
        try {
            statement.executeUpdate("INSERT INTO `player` VALUES ("+id+", '"+nnn+"', 0)");

        }catch(Exception ex){
            System.out.println(ex);
        }
    }

    public String getName(){
        return name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(int point){
        this.score = point;
    }

}
