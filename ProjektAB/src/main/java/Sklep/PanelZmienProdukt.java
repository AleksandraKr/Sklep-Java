package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;

public class PanelZmienProdukt extends AnchorPane {
    private Produkt p;
    @FXML
    TextField ilosc;
    @FXML
    TextField opis;
    @FXML
    TextField nazwa;
    @FXML
    TextField cena;
    @FXML
    Button zapisz;
    @FXML
    Button usun;

    public PanelZmienProdukt(Produkt p) throws Exception{
        this.p = p;
        FXMLLoader pdp = new FXMLLoader(getClass().getResource("/fxml/zmienProdukt.fxml"));
        pdp.setRoot(this);
        pdp.setController(this);
        pdp.load();
        ilosc.setText(p.getIloscSztuk().toString());
        opis.setText(p.getOpisProduktu());
        nazwa.setText(p.getNazwaProduktu());
        cena.setText(p.getCena().toString());
        zapisz.setOnAction(e->{zapiszButton();});
    }

    public void zapiszButton(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        p.setCena(Double.parseDouble(cena.getText()));
        p.setIloscSztuk(Integer.parseInt(ilosc.getText()));
        p.setNazwaProduktu(nazwa.getText());
        p.setOpisProduktu(opis.getText());
        session.update(p);
        session.getTransaction().commit();
        session.close();
    }
}
