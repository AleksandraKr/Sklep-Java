package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;

public class PanelZamowieniePracownik extends AnchorPane {
    private Zamowienie z;
    private List<ProduktZamowienie> pz;
    private List<Produkt> produkty;
    private List<StatusZamowienia> sz;
    @FXML
    Label nrzamowienia;
    @FXML
    Label daneos;
    @FXML
    Label adreszam;
    @FXML
    Label szczegoly;
    @FXML
    Label status;
    @FXML
    Label kosztzam;
    @FXML
    Button zmienna;

    public PanelZamowieniePracownik(Zamowienie z) throws Exception {
        this.z = z;
        FXMLLoader pzp = new FXMLLoader(getClass().getResource("/fxml/zamowieniePracownik.fxml"));
        pzp.setRoot(this);
        pzp.setController(this);
        pzp.load();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        TypedQuery query = session.createQuery("from ProduktZamowienie where zamowienie.idZamowienie = " + z.getIdZamowienie());
        pz = query.getResultList();
        query = session.createQuery("from Produkt");
        produkty = query.getResultList();
        query = session.createQuery("from StatusZamowienia ");
        sz = query.getResultList();
        System.out.println(z.getIdZamowienie());
        session.getTransaction().commit();
        session.close();
        nrzamowienia.setText("Zamówienie nr: " + z.getIdZamowienie()+ " z dnia " + z.getDataZ().toString().substring(0,10));
        daneos.setText(z.getUzytkownik().getImie() + " " + z.getUzytkownik().getNazwisko());
        adreszam.setText(z.getAdres().getUlica() + "/" + z.getAdres().getNrMieszkania() + ", " + z.getAdres().getMiasto() + " " + z.getAdres().getKodPocztowy());
        String s = "";
        int j = pz.size();
        for(int i=0; i<j; i++){
            s = s + pz.get(i).getProdukt().getNazwaProduktu() + " x" + pz.get(i).getIloscP() +", ";
        }
        szczegoly.setText(s);
        status.setText(z.getStatusZamowienia().getNazwaStatusZ());
        kosztzam.setText(String.format("%.2f",z.getKoszt()) + " zł");
        if(z.getStatusZamowienia().getIdStatusZ() == 1)
            zmienna.setText("Zmień na wysłane");
        else if(z.getStatusZamowienia().getIdStatusZ() == 2)
            zmienna.setText("Zmień na dostarczone");
        else{
            zmienna.setText("zrobione");
            zmienna.setDisable(true);
        }
        zmienna.setOnAction(e->{zmiennaButton();});
    }

    public void zmiennaButton(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        if(z.getStatusZamowienia().getIdStatusZ() == 1){
            z.setStatusZamowienia(sz.get(1));
            zmienna.setText("Zmień na dostarczone");
            status.setText(z.getStatusZamowienia().getNazwaStatusZ());
        }
        else if(z.getStatusZamowienia().getIdStatusZ() == 2){
            z.setStatusZamowienia(sz.get(2));
            zmienna.setText("zrobione");
            status.setText(z.getStatusZamowienia().getNazwaStatusZ());
            zmienna.setDisable(true);
        }

        session.update(z);
        session.getTransaction().commit();
        session.close();
    }
}
