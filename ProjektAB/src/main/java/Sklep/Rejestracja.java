package Sklep;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

class Rejestracja extends Application {
    @Override
    public void start(Stage stage){
        GridPane okno = new GridPane();
        ColumnConstraints[] con;
        okno.setAlignment(Pos.CENTER);
        okno.setVgap(2);
        okno.setHgap(10);
        okno.setId("okno");
        Label email = new Label("E-mail:");
        Label haslo = new Label("Hasło:");
        Label phaslo = new Label("Potwierdż hasło:");
        Label imie = new Label("Imię:");
        Label nazwisko = new Label("Nazwisko:");
        TextField emailf = new TextField();
        PasswordField haslof = new PasswordField();
        PasswordField phaslof = new PasswordField();
        TextField imief = new TextField();
        TextField nazwiskof = new TextField();
        Button rejestracja = new Button("Utwórz konto");
        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().add(rejestracja);
        ArrayList<Label> err = new ArrayList<>();
        Pattern patin = Pattern.compile("[A-Z][a-z]*");
        Pattern patemail = Pattern.compile("[A-Za-z]*[@][a-z]{2,5}[.][a-z]{2,4}");
        Pattern pathaslo = Pattern.compile("[A-Za-z0-9]{5}[A-Za-z0-9]*");

        int j = 1;
        for(int i=0;i<5;i++){
            err.add(new Label());
            err.get(i).setId("err");
            okno.add(err.get(i), 1,j,2,1);
            j=j+2;
        }
        okno.add(email,0,0,1,1);
        okno.add(haslo, 0,2,1,1);
        okno.add(phaslo,0,4,1,1);
        okno.add(imie,0,6,1,1);
        okno.add(nazwisko,0,8,1,1);
        okno.add(hbox,0,10,2,1);
        okno.add(emailf, 1, 0,1,1);
        okno.add(haslof,1,2,1,1);
        okno.add(phaslof,1,4,1,1);
        okno.add(imief,1,6,1,1);
        okno.add(nazwiskof,1,8,1,1);

        rejestracja.setOnAction(e->{
            for(int i=0;i<5;i++)
                err.get(i).setText("");
            Boolean spr = true;
            Matcher test1 = patemail.matcher(emailf.getText());
            Matcher test2 = pathaslo.matcher(haslof.getText());
            Matcher test3 = patin.matcher(imief.getText());
            Matcher test4 = patin.matcher(nazwiskof.getText());
            if(!emailf.getText().isEmpty()) {
                if (test1.matches() == false) {
                    err.get(0).setText("Błędny adres e-mail!");
                    spr = false;
                }
            }
            else {
                err.get(0).setText("Nie podano adresu e-mail!");
                spr = false;
            }
            if(!haslof.getText().isEmpty()) {
                if (test2.matches() == false) {
                    err.get(1).setText("Hasło musi zawierać co najmniej 5 znaków!");
                    spr = false;
                    haslof.clear();
                    phaslof.clear();
                }
                else if(!haslof.getText().equals(phaslof.getText())){
                    err.get(2).setText("Hasła nie są takie same!");
                    spr = false;
                    haslof.clear();
                    phaslof.clear();
                }
            }
            else {
                err.get(1).setText("Nie podano hasła!");
                spr = false;
            }
            if(!imief.getText().isEmpty()){
                if(test3.matches() == false){
                    err.get(3).setText("Błędne imie!");
                    spr = false;
                }
            }
            else{
                err.get(3).setText("Nie podano imienia!");
                spr = false;
            }
            if(!nazwiskof.getText().isEmpty()){
                if(test4.matches() == false){
                    err.get(4).setText("Błędne nazwisko!");
                    spr = false;
                }
            }
            else{
                err.get(4).setText("Nie podano imienia!");
                spr = false;
            }

            if(spr == true){
                Session session = HibernateUtil.getSessionFactory().openSession();
                session.beginTransaction();
                TypedQuery query = session.createQuery("from TypUzytkownika where idTypU = 3");
                List<TypUzytkownika> tu = query.getResultList();

                Uzytkownik u = new Uzytkownik();
                u.setNazwaUzytkownika(emailf.getText());
                u.setHaslo(haslof.getText());
                u.setImie(imief.getText());
                u.setNazwisko(nazwiskof.getText());
                u.setTypUzytkownika(tu.get(0));


                session.save(u);
                session.getTransaction().commit();
                session.close();

                Main m = new Main();
                m.start(stage);
            }
            else{
                haslof.clear();
                phaslof.clear();
            }
        });

        Scene scena = new Scene(okno, 800, 800);
        scena.getStylesheets().add("style.css");
        stage.setScene(scena);
        stage.show();
    }
}
