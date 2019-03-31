package Game;

import java.sql.*;

public class DBConnect {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DBConnect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brainfight?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC", "root", "");
            statement = connection.createStatement();

        }catch(Exception ex){
            System.out.println("Error: " + ex);
        }
    }
    public void delete_players(){
        try {
            statement.executeUpdate("DELETE FROM `player` ");

        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public void update_players(int id, int score){
        int point=0;

        try {
            String query = "SELECT * FROM `player` WHERE id="+id+"";
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                point = resultSet.getInt("score");
            }

            statement.executeUpdate("UPDATE `player` SET `score`="+(point+score)+" WHERE id="+id+"");

        }catch(Exception ex){
            System.out.println(ex);
        }
    }
    public String total_score(){
        String total="\tID\t|\tName\t|\tScore\t\n";

        try {
            String query = "SELECT * FROM `player`";
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int score = resultSet.getInt("score");

                total += "\t"+id+"\t|\t"+name+"\t|\t"+score+"\n";
            }

        }catch(Exception ex){
            System.out.println(ex);
        }
        return total;
    }

}
