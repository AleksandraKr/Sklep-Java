package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;

import java.util.List;

public class PanelReklamacjaPracownik extends AnchorPane {
    private Reklamacja r;
    private List<StatusReklamacji> sr;
    @FXML
    Label nrreklamacji;
    @FXML
    Label daneos;
    @FXML
    Label reklamowanyprod;
    @FXML
    Label opis;
    @FXML
    Label status;
    @FXML
    Button zmienna2;
    @FXML
    Button zmienna;

    public PanelReklamacjaPracownik(Reklamacja r, List<StatusReklamacji> sr) throws Exception {
        this.r = r;
        this.sr = sr;
        FXMLLoader prp = new FXMLLoader(getClass().getResource("/fxml/reklamacjaPracownik.fxml"));
        prp.setRoot(this);
        prp.setController(this);
        prp.load();
        nrreklamacji.setText("Reklamacja nr: " + r.getIdReklamacja() +" z dnia " + r.getDataR().toString().substring(0,10));
        daneos.setText(r.getUzytkownik().getImie() + " " + r.getUzytkownik().getNazwisko());
        reklamowanyprod.setText(r.getProdukt().getNazwaProduktu());
        opis.setText(r.getOpisProblemu());
        status.setText(r.getStatusReklamacji().getNazwaStatusR());
        zmienna.setOnAction(e->{zmiennaButton();});
        zmienna2.setOnAction(e->{zmienna2Button();});
        if(!r.getStatusReklamacji().equals(sr.get(0))){
            zmienna.setDisable(true);
            zmienna2.setDisable(true);
        }
    }

    public void zmiennaButton(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        r.setStatusReklamacji(sr.get(1));
        session.update(r);
        session.getTransaction().commit();
        session.close();
        status.setText(r.getStatusReklamacji().getNazwaStatusR());
        zmienna.setDisable(true);
        zmienna2.setDisable(true);
    }

    public void zmienna2Button(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        r.setStatusReklamacji(sr.get(2));
        session.update(r);
        session.getTransaction().commit();
        session.close();
        status.setText(r.getStatusReklamacji().getNazwaStatusR());
        zmienna.setDisable(true);
        zmienna2.setDisable(true);
    }
}
