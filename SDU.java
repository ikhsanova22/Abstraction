package Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SDU implements Questions{

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private int id;

    SDU(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/brainfight?verifyServerCertificate=false&useSSL=false&requireSSL=false&useLegacyDatetimeCode=false&amp&serverTimezone=UTC", "root", "");
            statement = connection.createStatement();

        }catch(Exception ex){
            System.out.println("Error: " + ex);
        }
    }


    @Override
    public String getQuestion(int point) {
        String question="";
        try {
            String query = "SELECT * FROM sdu WHERE point="+point+" ORDER BY RAND() limit 1";
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                question = resultSet.getString("question");
                id = resultSet.getInt("id");

            }

        }catch(Exception ex){
            System.out.println(ex);
        }
        return question;
    }

    @Override
    public boolean verifyAnswer(String answer) {
        String correct="";
        try {
            String query = "SELECT * FROM sdu WHERE id="+id+"";
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                correct = resultSet.getString("answer");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        if (answer.equals(correct))
            return true;
        return false;
    }

    @Override
    public String[] getVariants() {
        String[] variants = new String[4];
        try {
            String query = "SELECT * FROM sduvariants WHERE id="+id+"";
            resultSet = statement.executeQuery(query);

            while(resultSet.next()){
                variants[0] = resultSet.getString("var1");
                variants[1] = resultSet.getString("var2");
                variants[2] = resultSet.getString("var3");
                variants[3] = resultSet.getString("var4");
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return variants;
    }

}
