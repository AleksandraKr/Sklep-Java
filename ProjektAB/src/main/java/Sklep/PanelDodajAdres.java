package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.hibernate.Session;

public class PanelDodajAdres extends AnchorPane {
    @FXML
    TextField miasto;
    @FXML
    TextField kodp;
    @FXML
    TextField ulica;
    @FXML
    TextField nrmieszkania;
    @FXML
    Button dodaj;

    public PanelDodajAdres() throws Exception{
        FXMLLoader pda = new FXMLLoader(getClass().getResource("/fxml/dodajAdres.fxml"));
        pda.setRoot(this);
        pda.setController(this);
        pda.load();
        dodaj.setOnAction(e->{dodajButton();});
    }

    public void dodajButton(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Adres a = new Adres();
        a.setMiasto(miasto.getText());
        a.setKodPocztowy(Integer.parseInt(kodp.getText()));
        a.setUlica(ulica.getText());
        a.setNrMieszkania(Integer.parseInt(nrmieszkania.getText()));
        a.setUzytkownik(SesjaUzytkownika.getInstance().getUzytkownik());
        session.save(a);
        session.getTransaction().commit();
        session.close();
        Parent par = dodaj.getParent();
        while(!(par instanceof ScrollPane))
            par = par.getParent();
        ScrollPane sp = (ScrollPane)par;
        VBox testowy = new VBox();
        testowy.setAlignment(Pos.CENTER);
        Label lab = new Label("Dodano Adres");
        testowy.getChildren().add(lab);
        sp.setContent(testowy);
    }
}
