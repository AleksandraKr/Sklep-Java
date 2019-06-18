package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;

public class PanelSpisPracownikow extends AnchorPane {
    private Uzytkownik u;
    @FXML
    Label nazwa;
    @FXML
    Label imieinazwisko;
    @FXML
    Button usun;

    public PanelSpisPracownikow(Uzytkownik u) throws Exception{
        this.u = u;
        FXMLLoader psp = new FXMLLoader(getClass().getResource("/fxml/spisPracownikow.fxml"));
        psp.setRoot(this);
        psp.setController(this);
        psp.load();
        nazwa.setText(u.getNazwaUzytkownika());
        imieinazwisko.setText(u.getImie() + " " + u.getNazwisko());
        usun.setOnAction(e->{usunButton();});
    }

    public void usunButton(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(u);
        session.getTransaction().commit();
        session.close();
    }
}
