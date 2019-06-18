package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;

public class PanelProduktu extends AnchorPane {
    private Produkt p;
    @FXML
    Label nazwaprod;
    @FXML
    Label opisprod;
    @FXML
    Label sztprod;
    @FXML
    Label cenaprod;
    @FXML
    TextField iloscprod;
    @FXML
    Button dodajdokoszyka;

    public PanelProduktu(Produkt p) throws Exception{
        this.p = p;
        FXMLLoader pp = new FXMLLoader(getClass().getResource("/fxml/produkt.fxml"));
        pp.setRoot(this);
        pp.setController(this);
        pp.load();

        nazwaprod.setText(p.getNazwaProduktu());
        opisprod.setText(p.getOpisProduktu());
        if(p.getIloscSztuk() == 0)
            sztprod.setText("Produkt chwilowo niedostępny");
        else
            sztprod.setText("Ilość szt: " + p.getIloscSztuk().toString());
        cenaprod.setText(String.format("%.2f",p.getCena()) + " zł");
        dodajdokoszyka.setOnAction(e->{dodajDoKoszyka();});
    }

    public void dodajDoKoszyka(){
        int ilosc = Integer.parseInt(iloscprod.getText());
        if(ilosc<=p.getIloscSztuk()) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Koszyk k = new Koszyk();
            k.setProdukt(p);
            k.setUzytkownik(SesjaUzytkownika.getInstance().getUzytkownik());
            k.setIlosc(ilosc);
            session.save(k);
            session.getTransaction().commit();
            session.close();
        }
    }

}
