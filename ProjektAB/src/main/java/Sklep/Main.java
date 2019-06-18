package Sklep;

import javax.swing.text.html.CSS;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import javafx.scene.control.Button;


public class Main extends Application {
    @Override
    public void start(Stage stage){
        GridPane okno = new GridPane();

        Button rejestracja = new Button("Zarejestruj");
        Button zaloguj = new Button("Zaloguj");
        rejestracja.setPrefSize(406,815);
        zaloguj.setPrefSize(406,815);
        VBox vbox1 = new VBox(rejestracja);
        VBox vbox2 = new VBox(zaloguj);
        rejestracja.setId("rejestracja");
        zaloguj.setId("zaloguj");
        vbox1.setId("vbox1");
        vbox2.setId("vbox2");
        zaloguj.setOnAction(e-> {
            Logowanie l = new Logowanie();
            l.start(stage);
        });
        rejestracja.setOnAction(e->{
            Rejestracja r = new Rejestracja();
            r.start(stage);
        });
        okno.add(vbox1,0,0);
        okno.add(vbox2,1,0);
        Scene scena = new Scene(okno, 800, 800);
        scena.getStylesheets().add("style.css");
        stage.setScene(scena);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String args[]){
        launch(args);
    }
}
