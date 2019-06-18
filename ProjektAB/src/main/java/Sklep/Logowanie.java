package Sklep;

import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import javax.persistence.TypedQuery;
import java.util.List;

class Logowanie extends Application {
    @Override
    public void start(Stage stage){
        GridPane okno = new GridPane();
        okno.setAlignment(Pos.CENTER);
        okno.setId("okno");
        okno.setVgap(20);
        okno.setHgap(10);
        Label login = new Label("Login");
        Label haslo = new Label("Hasło");
        Label info = new Label();
        TextField loginf = new TextField();
        PasswordField haslof = new PasswordField();
        Button zaloguj = new Button("Zaloguj");
        info.setId("err");

        okno.add(login, 0,0,1,1);
        okno.add(haslo,0,2,1,1);
        okno.add(loginf, 1,0,1,1);
        okno.add(haslof, 1,2,1,1);
        okno.add(zaloguj, 1,6,1,1);
        okno.add(info, 1,4,1,1);

        zaloguj.setOnAction(e-> {
            String log = loginf.getText();
            String has = haslof.getText();
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            TypedQuery query = session.createQuery("from Uzytkownik u where u.nazwaUzytkownika = '"+ log +"'");
            List<Uzytkownik> u = query.getResultList();
            query = session.createQuery("from TypUzytkownika");
            List<TypUzytkownika> tu = query.getResultList();
            if(u.size() == 0){
                info.setText("Zły login lub hasło");
            }
            else{
                System.out.println(has);
                System.out.println(u.get(0).getHaslo());
                if(u.get(0).getHaslo().equals(has)){
                    SesjaUzytkownika.setInstance(u.get(0));
                    if(u.get(0).getTypUzytkownika().getIdTypU() == 3) {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/fxml/sklep.fxml"));
                            Stage newStage = new Stage();
                            newStage.setResizable(false);
                            newStage.setTitle("Sklep");
                            newStage.setScene(new Scene(root));
                            stage.close();
                            newStage.show();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    if(u.get(0).getTypUzytkownika().getIdTypU() == 2) {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/fxml/sklepPracownik.fxml"));
                            Stage newStage = new Stage();
                            newStage.setResizable(false);
                            newStage.setTitle("Sklep");
                            newStage.setScene(new Scene(root));
                            stage.close();
                            newStage.show();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    if(u.get(0).getTypUzytkownika().getIdTypU() == 1) {
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/fxml/sklepAdmin.fxml"));
                            Stage newStage = new Stage();
                            newStage.setResizable(false);
                            newStage.setTitle("Sklep");
                            newStage.setScene(new Scene(root));
                            stage.close();
                            newStage.show();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                else{
                    info.setText("Zły login lub hasło");
                }
            }
            session.getTransaction().commit();
        });

        Scene scena = new Scene(okno, 800, 800);
        scena.getStylesheets().add("style.css");
        stage.setScene(scena);
        stage.show();
    }

}
