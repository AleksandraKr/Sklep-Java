package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;

public class Zamow2Panel extends AnchorPane {
    private Zamowienie z;
    private List<ProduktZamowienie> pz;
    private List<Produkt> produkty;
    private List<StatusZamowienia> sz;
    private List<Adres> a;
    @FXML
    Label nrzmowienia;
    @FXML
    Label przedmioty;
    @FXML
    Label koszt;
    @FXML
    Label adreszam;
    @FXML
    Label status;

    public Zamow2Panel(Zamowienie z) throws Exception{
        this.z = z;
        FXMLLoader zp = new FXMLLoader(getClass().getResource("/fxml/zamowienie2.fxml"));
        zp.setRoot(this);
        zp.setController(this);
        zp.load();
        nrzmowienia.setText("Zamówienie nr: " + z.getIdZamowienie()+ " z dnia " + z.getDataZ().toString().substring(0,10));
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        TypedQuery query = session.createQuery("from ProduktZamowienie where zamowienie.idZamowienie = " + z.getIdZamowienie());
        pz = query.getResultList();
        query = session.createQuery("from Produkt");
        produkty = query.getResultList();
        query = session.createQuery("from StatusZamowienia ");
        sz = query.getResultList();
        session.getTransaction().commit();
        session.close();
        int j = pz.size();
        String s = "";
        for(int i=0; i<j; i++){
            s = s + pz.get(i).getProdukt().getNazwaProduktu() + " x" + pz.get(i).getIloscP() +", ";
        }
        przedmioty.setText(s);
        koszt.setText(String.format("%.2f",z.getKoszt()) + " zł");
        adreszam.setText(z.getAdres().getUlica() + "/" + z.getAdres().getNrMieszkania()+ ", " + z.getAdres().getMiasto() + " " + z.getAdres().getKodPocztowy());
        status.setText(z.getStatusZamowienia().getNazwaStatusZ());
    }
}
