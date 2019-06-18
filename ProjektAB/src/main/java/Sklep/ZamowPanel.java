package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;

public class ZamowPanel extends AnchorPane {
    private List<Produkt> produkty;
    private List<Koszyk> koszyki;
    @FXML
    Label nrzmowienia;
    @FXML
    Label przedmioty;
    @FXML
    Label kosztz;

    public ZamowPanel() throws Exception{
        FXMLLoader zp = new FXMLLoader(getClass().getResource("/fxml/zamowienie.fxml"));
        zp.setRoot(this);
        zp.setController(this);
        zp.load();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        TypedQuery query = session.createQuery("from Produkt");
        produkty = query.getResultList();
        query = session.createQuery("from Koszyk where uzytkownik.nazwaUzytkownika = '" + SesjaUzytkownika.getInstance().getUzytkownik().getNazwaUzytkownika()+"'");
        koszyki = query.getResultList();
        session.getTransaction().commit();
        session.close();
        nrzmowienia.setText("Szczegóły zamówienia (koszt dostawy 10zł, płatność przy odbiorze)");
        String nazwyprod = "";
        int j = koszyki.size();
        Double sumakoszykow = 0.0;
        for(int i=0;i<j;i++){
            nazwyprod = nazwyprod + koszyki.get(i).getProdukt().getNazwaProduktu()+"x"+koszyki.get(i).getIlosc()+", ";
            sumakoszykow = sumakoszykow + koszyki.get(i).getProdukt().getCena()*koszyki.get(i).getIlosc();
        }
        sumakoszykow = sumakoszykow + 10;
        przedmioty.setText(nazwyprod);
        kosztz.setText(String.format("%.2f",sumakoszykow) + " zł");
    }
}
