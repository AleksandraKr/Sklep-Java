package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class AdresPanel extends AnchorPane {
    private Adres a;
    private List<Produkt> produkty;
    private List<Koszyk> koszyki;
    private List<StatusZamowienia> sz;
    @FXML
    Label pelenadres;
    @FXML
    Button wzamow;

    public AdresPanel(Adres a) throws Exception{
        this.a = a;
        FXMLLoader ap = new FXMLLoader(getClass().getResource("/fxml/adres.fxml"));
        ap.setRoot(this);
        ap.setController(this);
        ap.load();
        String s = a.getUlica() + "/" + a.getNrMieszkania().toString() + ", " + a.getMiasto() + " " +a.getKodPocztowy().toString();
        pelenadres.setText(s);
        wzamow.setOnAction(e->{
            wzamowButton();
            Parent par = wzamow.getParent();
            while(!(par instanceof ScrollPane))
                par = par.getParent();
            ScrollPane sp = (ScrollPane)par;
            VBox testowy = new VBox();
            testowy.setAlignment(Pos.CENTER);
            Label lab = new Label("Twoje zamówienie zostało złożone");
            testowy.getChildren().add(lab);
            sp.setContent(testowy);
        });
    }

    public void wzamowButton(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        TypedQuery query = session.createQuery("from Produkt");
        produkty = query.getResultList();
        query = session.createQuery("from Koszyk where uzytkownik.nazwaUzytkownika = '" + SesjaUzytkownika.getInstance().getUzytkownik().getNazwaUzytkownika()+"'");
        koszyki = query.getResultList();
        query = session.createQuery("from StatusZamowienia");
        sz = query.getResultList();
        int j= koszyki.size();
        Zamowienie z = new Zamowienie();
        z.setUzytkownik(SesjaUzytkownika.getInstance().getUzytkownik());
        Date d = new Date();
        z.setDataZ(d);
        z.setStatusZamowienia(sz.get(0));
        z.setAdres(a);
        Double sumakoszykow = 0.0;
        for(int i=0;i<j;i++){
            sumakoszykow = sumakoszykow + koszyki.get(i).getProdukt().getCena()*koszyki.get(i).getIlosc();
        }
        sumakoszykow = sumakoszykow + 10;
        z.setKoszt(sumakoszykow);
        session.save(z);
        for(int i = 0; i<j; i++){
            ProduktZamowienie przam = new ProduktZamowienie();
            przam.setProdukt(koszyki.get(i).getProdukt());
            przam.setZamowienie(z);
            przam.setIloscP(koszyki.get(i).getIlosc());
            koszyki.get(i).getProdukt().setIloscSztuk(koszyki.get(i).getProdukt().getIloscSztuk()-koszyki.get(i).getIlosc());
            session.update(koszyki.get(i).getProdukt());
            session.save(przam);
            session.delete(koszyki.get(i));
        }
        session.save(z);
        session.getTransaction().commit();
        session.close();
    }

}
