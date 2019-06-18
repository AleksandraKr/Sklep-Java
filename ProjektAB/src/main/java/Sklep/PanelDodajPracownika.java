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

import javax.persistence.TypedQuery;
import java.util.List;

public class PanelDodajPracownika extends AnchorPane {
    @FXML
    TextField nazwa;
    @FXML
    TextField imie;
    @FXML
    TextField nazwisko;
    @FXML
    TextField haslo;
    @FXML
    Button dodaj;

    public PanelDodajPracownika() throws Exception{
        FXMLLoader pdp = new FXMLLoader(getClass().getResource("/fxml/dodajPracownika.fxml"));
        pdp.setRoot(this);
        pdp.setController(this);
        pdp.load();
        dodaj.setOnAction(e->{dodajButton();});
    }

    public void dodajButton(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        TypedQuery query = session.createQuery("from TypUzytkownika ");
        List<TypUzytkownika> tu = query.getResultList();
        Uzytkownik u = new Uzytkownik();
        u.setNazwaUzytkownika(nazwa.getText());
        u.setImie(imie.getText());
        u.setNazwisko(nazwisko.getText());
        u.setHaslo(haslo.getText());
        u.setTypUzytkownika(tu.get(1));
        session.save(u);
        session.getTransaction().commit();
        session.close();
        Parent par = dodaj.getParent();
        while(!(par instanceof ScrollPane))
            par = par.getParent();
        ScrollPane sp = (ScrollPane)par;
        VBox testowy = new VBox();
        testowy.setAlignment(Pos.CENTER);
        Label lab = new Label("Pracownik został dodany");
        testowy.getChildren().add(lab);
        sp.setContent(testowy);
    }
}
