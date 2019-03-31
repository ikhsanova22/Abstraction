package Game;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.util.ArrayList;


public class Game extends Application {

    private DBConnect db= new DBConnect();
    private int numofplayers;
    private ObservableList<Player> players1 = FXCollections.observableArrayList();
    GridPane question_table = new GridPane();
    int count = -1;

    public void start(Stage primaryStage) throws Exception {

        BorderPane welcome = new BorderPane();
        welcome.setPadding(new Insets(50));
        welcome.setStyle("-fx-background-color: #FFE5CC;");

        InnerShadow is = new InnerShadow();
        is.setOffsetX(4.0f);
        is.setOffsetY(4.0f);

        Text brainFight = new Text(200,100,"Welcome ");
        Text brainFight1 = new Text(200,100," to ");
        Text brainFight2 = new Text(200,100,"BrainFight!");

        brainFight.setEffect(is);
        brainFight.setX(10.0f);
        brainFight.setY(50.0f);
        brainFight.setCache(true);

        brainFight1.setEffect(is);
        brainFight1.setEffect(is);
        brainFight1.setX(10.0f);
        brainFight1.setY(50.0f);
        brainFight1.setCache(true);

        brainFight2.setEffect(is);
        brainFight2.setEffect(is);
        brainFight2.setX(10.0f);
        brainFight2.setY(50.0f);
        brainFight2.setCache(true);

        VBox vBoxText = new VBox();
        vBoxText.getChildren().addAll(brainFight,brainFight1,brainFight2);
        brainFight.setTextAlignment(TextAlignment.CENTER);
        vBoxText.setAlignment(Pos.CENTER);

        brainFight.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 70));
        brainFight.setFill(Color.BLUEVIOLET);
        brainFight1.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 70));
        brainFight1.setFill(Color.BLUEVIOLET);
        brainFight2.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 70));
        brainFight2.setFill(Color.BLUEVIOLET);

        welcome.setTop(vBoxText);

        DropShadow shadow = new DropShadow();
        Button start = new Button("START");

        start.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent e) {
                    start.setEffect(shadow);
                }
        });

        start.setStyle("-fx-font: 25 arial; -fx-base:#99FFCC");
        welcome.setCenter(start);

        DropShadow ds = new DropShadow();
        ds.setOffsetY(4.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        BorderPane number = new BorderPane();
        Text text = new Text("Please,enter number");
        text.setEffect(ds);
        text.setCache(true);
        text.setX(10.0f);
        text.setY(270.0f);

        Text text2 = new Text("of players");
        text2.setEffect(ds);
        text2.setCache(true);
        text2.setX(10.0f);
        text2.setY(270.0f);

        text.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 40));
        text.setTextAlignment(TextAlignment.CENTER);
        text2.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 40));
        text2.setTextAlignment(TextAlignment.CENTER);

        TextField input = new TextField();
        input.setPrefWidth(100);
        input.setAlignment(Pos.CENTER);

        Button continue1= new Button("CONTINUE");
        continue1.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                continue1.setEffect(shadow);
            }
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(text,text2, input,continue1);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);

        number.setCenter(vBox);

        continue1.setStyle("-fx-font: 25 arial; -fx-base:#99FFCC");

        number.setPadding(new Insets(100));
        number.setStyle("-fx-background-color: #FFE5CC;");

        Button continue2= new Button("CONTINUE");
        continue2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent e) {
                    continue2.setEffect(shadow);
                }
        });
        continue2.setStyle("-fx-font: 25 arial; -fx-base:#99FFCC");
        continue2.setAlignment(Pos.CENTER);


        continue1.setOnAction( (ActionEvent e) -> {
            if (input.getText().isEmpty() || !Character.isDigit(input.getText().charAt(0))
                    || Integer.parseInt(input.getText())<1 || Integer.parseInt(input.getText())>4 ){
                input.setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                Label l=new Label("Enter digit between 1 and 4");
                l.setFont(new Font("Arial", 20));
                vBox.getChildren().add(l);

            }else {
                VBox names = new VBox();
                Text nick = new Text("Enter your nicknames");
                names.getChildren().addAll(nick);
                nick.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 40));

                String[] players = new String[0];
                numofplayers = Integer.parseInt(input.getText());
                ObservableList<TextField> users = FXCollections.observableArrayList();
                for (int i = 1; i < numofplayers + 1; i++) {
                    HBox hBox = new HBox();
                    hBox.setPadding(new Insets(10, 10, 10, 10));
                    Label label = new Label("Player " + i + "     ");
                    label.setFont(new Font("Arial",30));

                    TextField textField = new TextField();
                    hBox.getChildren().addAll(label, textField);
                    hBox.setAlignment(Pos.CENTER);

                    names.getChildren().addAll(hBox);
                    names.setAlignment(Pos.CENTER);

                    names.setPadding(new Insets(100));
                    names.setStyle("-fx-background-color: #FFE5CC;");

                    users.add(textField);
                }

                db.delete_players();
                ObservableList<Player> players1 = FXCollections.observableArrayList();
                continue2.setOnAction(ev -> {
                    for (int i = 0; i < numofplayers; i++) {
                        Player play = new Player(users.get(i).getText());
                        players1.add(play);
                        play.insert(i+1, play.getName());
                        if (users.get(i).getText().isEmpty()){
                            users.get(i).setStyle("-fx-border-color: red ; -fx-border-width: 2px ;");
                        }
                    }
                });
                names.getChildren().addAll(continue2);
                Scene sc3 = new Scene(names, 800, 500);
                primaryStage.setScene(sc3);
            }
        });

        Stage table = new Stage();
        question_table = new GridPane();
        question_table.setPrefWidth(800);
        question_table.setPrefHeight(500);
        question_table.setStyle("-fx-background-color: #FFE5CC;");
        question_table.setVgap(5);
        question_table.setHgap(5);

        Text capital = new Text("Capitals");
        Text sdu = new Text("SDU");
        Text cars = new Text("Cars");
        Text food = new Text("Food");

        capital.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 30));
        sdu.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 30));
        cars.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 30));
        food.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 30));

        question_table.add(capital,0,0);
        question_table.add(sdu,1,0);
        question_table.add(cars,2,0);
        question_table.add(food,3,0);

        ArrayList<Button> capitals_button = new ArrayList<>();
        ArrayList<Button> sdu_button = new ArrayList<>();
        ArrayList<Button> cars_button = new ArrayList<>();
        ArrayList<Button> food_button = new ArrayList<>();

        for (int i = 1; i< 5; i++){
            capitals_button.add(new Button(""+(i+1)+"0"));
        }
        for (int i = 1; i< 5; i++){
            sdu_button.add(new Button(""+(i+1)+"0"));
        }
        for (int i = 1; i< 5; i++){
            cars_button.add(new Button(""+(i+1)+"0"));
        }
        for (int i = 1; i< 5; i++){
            food_button.add(new Button(""+(i+1)+"0"));
        }

        question_table.add(capitals_button.get(0), 0, 1);
        question_table.add(capitals_button.get(1), 0, 2);
        question_table.add(capitals_button.get(2), 0, 3);
        question_table.add(capitals_button.get(3), 0, 4);

        question_table.add(sdu_button.get(0), 1, 1);
        question_table.add(sdu_button.get(1), 1, 2);
        question_table.add(sdu_button.get(2), 1, 3);
        question_table.add(sdu_button.get(3), 1, 4);

        question_table.add(cars_button.get(0), 2, 1);
        question_table.add(cars_button.get(1), 2, 2);
        question_table.add(cars_button.get(2), 2, 3);
        question_table.add(cars_button.get(3), 2, 4);

        question_table.add(food_button.get(0), 3, 1);
        question_table.add(food_button.get(1), 3, 2);
        question_table.add(food_button.get(2), 3, 3);
        question_table.add(food_button.get(3), 3, 4);

        for (Button f: food_button) {
            f.setPrefHeight(question_table.getPrefHeight()/4-5);
            f.setPrefWidth(question_table.getPrefWidth()/4-5);
            f.setStyle("-fx-font-size: 40px arial; -fx-base:#d0d4ff");
        }
        for (Button c: cars_button){
            c.setPrefHeight(question_table.getPrefHeight()/4-5);
            c.setPrefWidth(question_table.getPrefWidth()/4-5);
            c.setStyle("-fx-font-size: 40px arial; -fx-base:#d0d4ff");
        }
        for (Button s: sdu_button) {
            s.setPrefHeight(question_table.getPrefHeight()/4-5);
            s.setPrefWidth(question_table.getPrefWidth()/4-5);
            s.setStyle("-fx-font-size: 40px arial; -fx-base:#d0d4ff");
        }
        for (Button cap: capitals_button){
            cap.setPrefHeight(question_table.getPrefHeight()/4-5);
            cap.setPrefWidth(question_table.getPrefWidth()/4-5);
            cap.setStyle("-fx-font-size: 40px arial; -fx-base:#d0d4ff");
        }

        Capitals capitalFactory = new Capitals();
        for (Button cap: capitals_button){
            cap.setOnMouseClicked(e1 ->{
                VBox vBox11 = new VBox();
                int point = Integer.parseInt(cap.getText());

                vBox11.setStyle("-fx-background-color: #FFE5CC;");
                Text text11 = new Text(capitalFactory.getQuestion(point));
                text11.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 30));
                GridPane grid = new GridPane();
                grid.setPrefHeight(450);
                grid.setPrefWidth(800);

                Button b1 = new Button(capitalFactory.getVariants()[0]);
                Button b2 = new Button(capitalFactory.getVariants()[1]);
                Button b3 = new Button(capitalFactory.getVariants()[2]);
                Button b4 = new Button(capitalFactory.getVariants()[3]);

                Button[] answer_for_question = {b1,b2,b3,b4};

                grid.add(b1,0,0);
                grid.add(b2,1,0);
                grid.add(b3,0,1);
                grid.add(b4,1,1);

                b1.setPrefWidth(grid.getPrefWidth()/2);
                b1.setPrefHeight(grid.getPrefHeight()/2);
                b2.setPrefWidth(grid.getPrefWidth()/2);
                b2.setPrefHeight(grid.getPrefHeight()/2);
                b3.setPrefWidth(grid.getPrefWidth()/2);
                b3.setPrefHeight(grid.getPrefHeight()/2);
                b4.setPrefWidth(grid.getPrefWidth()/2);
                b4.setPrefHeight(grid.getPrefHeight()/2);

                vBox11.getChildren().addAll(text11, grid);

                cap.setDisable(true);

                for (Button answer: answer_for_question) {
                    answer.setStyle("-fx-font-size: 40px arial; -fx-base:#d0d4ff");
                    answer.setOnMouseClicked(verify_answer -> {
                        boolean correctness = capitalFactory.verifyAnswer(answer.getText());
                        if (correctness) {
                            System.out.println("True");
                            db.update_players(count%numofplayers+1,point);
                        }else {
                            System.out.println("False");
                        }
                        table.show();
                        primaryStage.close();
                    });
                }

                Scene sc = new Scene(vBox11, 800,500);
                primaryStage.setScene(sc);
                primaryStage.show();
                table.close();

                count++;
                if (count==numofplayers*4){
                    Stage stage = new Stage();
                    BorderPane pane = new BorderPane();
                    Text ttt = new Text("Game is over");
                    ttt.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 30));
                    Button end = new Button("The End");
                    pane.setCenter(ttt);
                    pane.setBottom(end);
                    pane.setStyle("-fx-background-color: #FFE5CC;");
                    Scene scene = new Scene(pane, 200,100);
                    stage.setScene(scene);
                    stage.show();
                    end.setAlignment(Pos.CENTER);                    end.setStyle("-fx-font: 25 arial; -fx-base:#99FFCC");
                    end.setOnMouseClicked(theend -> {
                        BorderPane total = new BorderPane();
                        total.setStyle("-fx-background-color: #FFE5CC;");
                        Text total_score = new Text(db.total_score());
                        total_score.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 20));
                        total.setCenter(total_score);
                        Scene t = new Scene(total,300,300);
                        stage.setScene(t);
                        stage.show();
                    });
                    primaryStage.close();
                    table.close();
                }
            });
        }

        Food foodFactory = new Food();
        for (Button f: food_button){
            f.setOnMouseClicked(e1 ->{
                VBox vBox11 = new VBox();
                int point = Integer.parseInt(f.getText());

                vBox11.setStyle("-fx-background-color: #FFE5CC;");
                Text text11 = new Text(foodFactory.getQuestion(point));
                text11.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 30));
                GridPane grid = new GridPane();
                grid.setPrefHeight(450);
                grid.setPrefWidth(800);

                Button b1 = new Button(foodFactory.getVariants()[0]);
                Button b2 = new Button(foodFactory.getVariants()[1]);
                Button b3 = new Button(foodFactory.getVariants()[2]);
                Button b4 = new Button(foodFactory.getVariants()[3]);

                Button[] answer_for_question = {b1,b2,b3,b4};

                grid.add(b1,0,0);
                grid.add(b2,1,0);
                grid.add(b3,0,1);
                grid.add(b4,1,1);

                b1.setPrefWidth(grid.getPrefWidth()/2);
                b1.setPrefHeight(grid.getPrefHeight()/2);
                b2.setPrefWidth(grid.getPrefWidth()/2);
                b2.setPrefHeight(grid.getPrefHeight()/2);
                b3.setPrefWidth(grid.getPrefWidth()/2);
                b3.setPrefHeight(grid.getPrefHeight()/2);
                b4.setPrefWidth(grid.getPrefWidth()/2);
                b4.setPrefHeight(grid.getPrefHeight()/2);

                vBox11.getChildren().addAll(text11, grid);

                f.setDisable(true);
                for (Button answer: answer_for_question) {
                    answer.setStyle("-fx-font-size: 40px arial; -fx-base:#d0d4ff");
                    answer.setOnMouseClicked(verify_answer -> {
                        boolean correctness = foodFactory.verifyAnswer(answer.getText());
                        if (correctness) {
                            System.out.println("True");
                            db.update_players(count%numofplayers+1,point);
                        }else {
                            System.out.println("False");
                        }
                        table.show();
                        primaryStage.close();
                    });
                }

                Scene sc = new Scene(vBox11, 800,500);
                primaryStage.setScene(sc);
                primaryStage.show();
                table.close();

                count++;
                if (count==numofplayers*4){
                    Stage stage = new Stage();
                    BorderPane pane = new BorderPane();
                    Text ttt = new Text("Game is over");
                    ttt.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 30));
                    Button end = new Button("The End");
                    pane.setCenter(ttt);
                    pane.setBottom(end);
                    pane.setStyle("-fx-background-color: #FFE5CC;");
                    Scene scene = new Scene(pane, 200,100);
                    stage.setScene(scene);
                    stage.show();
                    end.setAlignment(Pos.CENTER);
                    end.setStyle("-fx-font: 25 arial; -fx-base:#99FFCC");
                    end.setOnMouseClicked(theend -> {
                        BorderPane total = new BorderPane();
                        total.setStyle("-fx-background-color: #FFE5CC;");
                        Text total_score = new Text(db.total_score());
                        total_score.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 20));
                        total.setCenter(total_score);
                        Scene t = new Scene(total,300,300);
                        stage.setScene(t);
                        stage.show();
                    });
                    primaryStage.close();
                    table.close();
                }
            });
        }

        Cars carFactory = new Cars();
        for (Button c: cars_button){
            c.setOnMouseClicked(e1 ->{
                VBox vBox11 = new VBox();
                int point = Integer.parseInt(c.getText());

                vBox11.setStyle("-fx-background-color: #FFE5CC;");
                Text text11 = new Text(carFactory.getQuestion(point));
                text11.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 30));
                GridPane grid = new GridPane();
                grid.setPrefHeight(450);
                grid.setPrefWidth(800);

                Button b1 = new Button(carFactory.getVariants()[0]);
                Button b2 = new Button(carFactory.getVariants()[1]);
                Button b3 = new Button(carFactory.getVariants()[2]);
                Button b4 = new Button(carFactory.getVariants()[3]);

                Button[] answer_for_question = {b1,b2,b3,b4};

                grid.add(b1,0,0);
                grid.add(b2,1,0);
                grid.add(b3,0,1);
                grid.add(b4,1,1);

                b1.setPrefWidth(grid.getPrefWidth()/2);
                b1.setPrefHeight(grid.getPrefHeight()/2);
                b2.setPrefWidth(grid.getPrefWidth()/2);
                b2.setPrefHeight(grid.getPrefHeight()/2);
                b3.setPrefWidth(grid.getPrefWidth()/2);
                b3.setPrefHeight(grid.getPrefHeight()/2);
                b4.setPrefWidth(grid.getPrefWidth()/2);
                b4.setPrefHeight(grid.getPrefHeight()/2);

                vBox11.getChildren().addAll(text11, grid);

                c.setDisable(true);
                for (Button answer: answer_for_question) {
                    answer.setStyle("-fx-font-size: 40px arial; -fx-base:#d0d4ff");
                    answer.setOnMouseClicked(verify_answer -> {
                        boolean correctness = carFactory.verifyAnswer(answer.getText());
                        if (correctness) {
                            System.out.println("True");
                            db.update_players(count%numofplayers+1,point);
                        }else {
                            System.out.println("False");
                        }
                        table.show();
                        primaryStage.close();
                    });
                }

                Scene sc = new Scene(vBox11, 800,500);
                primaryStage.setScene(sc);
                primaryStage.show();
                table.close();

                count++;
                if (count==numofplayers*4){
                    Stage stage = new Stage();
                    BorderPane pane = new BorderPane();
                    Text ttt = new Text("Game is over");
                    ttt.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 30));
                    Button end = new Button("The End");
                    pane.setCenter(ttt);
                    pane.setBottom(end);
                    pane.setStyle("-fx-background-color: #FFE5CC;");
                    Scene scene = new Scene(pane, 200,100);
                    stage.setScene(scene);
                    stage.show();
                    end.setAlignment(Pos.CENTER);
                    end.setStyle("-fx-font: 25 arial; -fx-base:#99FFCC");
                    end.setOnMouseClicked(theend -> {
                        BorderPane total = new BorderPane();
                        total.setStyle("-fx-background-color: #FFE5CC;");
                        Text total_score = new Text(db.total_score());
                        total_score.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 20));
                        total.setCenter(total_score);
                        Scene t = new Scene(total,300,300);
                        stage.setScene(t);
                        stage.show();
                    });
                    primaryStage.close();
                    table.close();
                }

            });
        }

        SDU sduFactory = new SDU();
        for (Button s: sdu_button){
            s.setOnMouseClicked(e1 ->{
                VBox vBox11 = new VBox();
                int point = Integer.parseInt(s.getText());

                vBox11.setStyle("-fx-background-color: #FFE5CC;");
                Text text11 = new Text(sduFactory.getQuestion(point));
                text11.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 30));
                GridPane grid = new GridPane();
                grid.setPrefHeight(450);
                grid.setPrefWidth(800);

                Button b1 = new Button(sduFactory.getVariants()[0]);
                Button b2 = new Button(sduFactory.getVariants()[1]);
                Button b3 = new Button(sduFactory.getVariants()[2]);
                Button b4 = new Button(sduFactory.getVariants()[3]);

                Button[] answer_for_question = {b1,b2,b3,b4};

                grid.add(b1,0,0);
                grid.add(b2,1,0);
                grid.add(b3,0,1);
                grid.add(b4,1,1);

                b1.setPrefWidth(grid.getPrefWidth()/2);
                b1.setPrefHeight(grid.getPrefHeight()/2);
                b2.setPrefWidth(grid.getPrefWidth()/2);
                b2.setPrefHeight(grid.getPrefHeight()/2);
                b3.setPrefWidth(grid.getPrefWidth()/2);
                b3.setPrefHeight(grid.getPrefHeight()/2);
                b4.setPrefWidth(grid.getPrefWidth()/2);
                b4.setPrefHeight(grid.getPrefHeight()/2);

                vBox11.getChildren().addAll(text11, grid);

                s.setDisable(true);
                for (Button answer: answer_for_question) {
                    answer.setStyle("-fx-font-size: 40px arial; -fx-base:#d0d4ff");
                    answer.setOnMouseClicked(verify_answer -> {
                        boolean correctness = sduFactory.verifyAnswer(answer.getText());
                        if (correctness) {
                            System.out.println("True");
                            db.update_players(count%numofplayers+1,point);

                        }else {
                            System.out.println("False");
                        }
                        table.show();
                        primaryStage.close();
                    });
                }

                Scene sc = new Scene(vBox11, 800,500);
                primaryStage.setScene(sc);
                primaryStage.show();
                table.close();

                count++;
                if (count==numofplayers*4){
                    Stage stage = new Stage();
                    BorderPane pane = new BorderPane();
                    Text ttt = new Text("Game is over");
                    ttt.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 30));
                    Button end = new Button("The End");
                    pane.setCenter(ttt);
                    pane.setBottom(end);
                    pane.setStyle("-fx-background-color: #FFE5CC;");
                    Scene scene = new Scene(pane, 200,100);
                    stage.setScene(scene);
                    stage.show();
                    end.setAlignment(Pos.CENTER);
                    end.setStyle("-fx-font: 25 arial; -fx-base:#99FFCC");
                    end.setOnMouseClicked(theend -> {
                        BorderPane total = new BorderPane();
                        total.setStyle("-fx-background-color: #FFE5CC;");
                        Text total_score = new Text(db.total_score());
                        total_score.setFont(Font.font("Times New Roman", FontPosture.REGULAR, 20));
                        total.setCenter(total_score);
                        Scene t = new Scene(total,300,300);
                        stage.setScene(t);
                        stage.show();
                    });
                    primaryStage.close();
                    table.close();
                }
            });
        }


        Scene sc1 = new Scene(welcome, 800, 500);
        Scene sc2 = new Scene(number, 800,500);
        Scene table_scene = new Scene(question_table,800,550);
        table.setScene(table_scene);

        continue2.setOnMouseClicked(event -> {
            table.show();
            primaryStage.close();
        });

        primaryStage.setScene(sc1);
        start.setOnMouseClicked(e -> primaryStage.setScene(sc2));
        primaryStage.setTitle("BrainFight");

        primaryStage.show();


    }

}
