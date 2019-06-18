package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Admin implements Initializable {
    private List<Produkt> produkty;
    private List<TypProduktu> typP;
    private List<StatusZamowienia> sz;
    private List<Adres> adresy;
    private List<Zamowienie> zamowienia;
    private List<Reklamacja> reklamacje;
    private List<StatusReklamacji> sr;
    private List<Uzytkownik> uzytkownicy;
    private List<ProduktZamowienie> pz;
    private List<Uzytkownik> pracownicy;
    @FXML
    Button bsls;
    @FXML
    Button wszystko;
    @FXML
    ScrollPane scroolsklep;
    @FXML
    Button zsls;
    @FXML
    Button wysokopor;
    @FXML
    Button niskopor;
    @FXML
    Button sredniopor;
    @FXML
    Button humektantowe;
    @FXML
    Button proteinowe;
    @FXML
    Button emolientowe;
    @FXML
    Button ziolaf;
    @FXML
    Button ziolab;
    @FXML
    Button zele;
    @FXML
    Button kremy;
    @FXML
    Button pianki;
    @FXML
    ScrollPane scroolzir;
    @FXML
    Button spiszamowien;
    @FXML
    Button spisreklamacji;
    @FXML
    Button dodajprod;
    @FXML
    Button dodajpracownika;
    @FXML
    ScrollPane scroolkont;
    @FXML
    Button kontapracownikow;
    @FXML
    Button modprod;

    @Override
    public void initialize(URL xd, ResourceBundle rs){
        wezProdukty();
        wszystkoButton();
        spiszamowienButton();
        wszystko.setOnAction(e->{wszystkoButton();});
        bsls.setOnAction(e->{typyButton(5);});
        zsls.setOnAction(e->{typyButton(4);});
        wysokopor.setOnAction(e->{typyButton(6);});
        niskopor.setOnAction(e->{typyButton(8);});
        sredniopor.setOnAction(e->{typyButton(7);});
        humektantowe.setOnAction(e->{typyButton(1);});
        proteinowe.setOnAction(e->{typyButton(3);});
        emolientowe.setOnAction(e->{typyButton(2);});
        ziolab.setOnAction(e->{typyButton(10);});
        ziolaf.setOnAction(e->{typyButton(9);});
        kremy.setOnAction(e->{typyButton(11);});
        zele.setOnAction(e->{typyButton(12);});
        pianki.setOnAction(e->{typyButton(13);});
        spiszamowien.setOnAction(e->{spiszamowienButton();});
        spisreklamacji.setOnAction(e->{spisreklamacjiButton();});
        dodajprod.setOnAction(e->{dodajprodButton();});
        dodajpracownika.setOnAction(e->{dodajpracownikaButton();});
        kontapracownikow.setOnAction(e->{spisrpracownikowiButton();});
        modprod.setOnAction(e->{modprodButton();});
    }

    public void wezProdukty(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        TypedQuery query = session.createQuery("from Produkt");
        produkty = query.getResultList();
        query = session.createQuery("from TypProduktu");
        typP = query.getResultList();
        query = session.createQuery("from StatusZamowienia");
        sz = query.getResultList();
        query = session.createQuery("from Adres");
        adresy = query.getResultList();
        query = session.createQuery("from Zamowienie");
        zamowienia = query.getResultList();
        query = session.createQuery("from Reklamacja");
        reklamacje = query.getResultList();
        query = session.createQuery("from StatusReklamacji ");
        sr = query.getResultList();
        query = session.createQuery("from Uzytkownik where typUzytkownika.idTypU = 3");
        uzytkownicy = query.getResultList();
        query = session.createQuery("from Uzytkownik where typUzytkownika.idTypU = 2");
        pracownicy = query.getResultList();
        query = session.createQuery("from ProduktZamowienie ");
        pz = query.getResultList();
        session.getTransaction().commit();
        session.close();
    }

    public void wszystkoButton(){
        wezProdukty();
        VBox lista = new VBox();
        int j = produkty.size();
        for(int i=0; i<j; i++){
            try{
                PanelProduktPracownik ppp = new PanelProduktPracownik(produkty.get(i));
                lista.getChildren().add(ppp);
            }catch(Exception ex){ex.printStackTrace();}
        }
        scroolsklep.setContent(lista);
    }

    public void typyButton(int id){
        wezProdukty();
        VBox lista = new VBox();
        int j = produkty.size();
        for(int i=0; i<j; i++){
            if (produkty.get(i).getTypProduktu().getIdTypP() == id) {
                try{
                    PanelProduktPracownik ppp = new PanelProduktPracownik(produkty.get(i));
                    lista.getChildren().add(ppp);
                }catch(Exception ex){ex.printStackTrace();}
            }
        }
        scroolsklep.setContent(lista);
    }

    public void spiszamowienButton(){
        wezProdukty();
        VBox lista = new VBox();
        int j = zamowienia.size();
        for(int i=0; i<j; i++){
            try{
                PanelZamowieniePracownik pzp = new PanelZamowieniePracownik(zamowienia.get(i));
                lista.getChildren().add(pzp);
            }catch(Exception ex){ex.printStackTrace();}
        }
        scroolzir.setContent(lista);
    }

    public void spisreklamacjiButton(){
        wezProdukty();
        VBox lista = new VBox();
        int j = reklamacje.size();
        for(int i=0; i<j; i++){
            try{
                PanelReklamacjaPracownik prp = new PanelReklamacjaPracownik(reklamacje.get(i), sr);
                lista.getChildren().add(prp);
            }catch(Exception ex){ex.printStackTrace();}
        }
        scroolzir.setContent(lista);
    }

    public void dodajprodButton(){
        VBox lista = new VBox();
        try{
            PanelDodajProdukt pdp = new PanelDodajProdukt();
            lista.getChildren().add(pdp);
        }catch(Exception ex){ex.printStackTrace();}
        scroolsklep.setContent(lista);
    }

    public void dodajpracownikaButton(){
        VBox lista = new VBox();
        try{
            PanelDodajPracownika pdp = new PanelDodajPracownika();
            lista.getChildren().add(pdp);
        }catch(Exception ex){ex.printStackTrace();}
        scroolkont.setContent(lista);
    }

    public void spisrpracownikowiButton(){
        wezProdukty();
        VBox lista = new VBox();
        int j = pracownicy.size();
        for(int i=0; i<j; i++){
            try{
                PanelSpisPracownikow prp = new PanelSpisPracownikow(pracownicy.get(i));
                lista.getChildren().add(prp);
            }catch(Exception ex){ex.printStackTrace();}
        }
        scroolkont.setContent(lista);
    }

    public void modprodButton(){
        wezProdukty();
        VBox lista = new VBox();
        int j = produkty.size();
        for(int i=0; i<j; i++){
            try{
                PanelZmienProdukt pp = new  PanelZmienProdukt(produkty.get(i));
                lista.getChildren().add(pp);
            }catch(Exception ex){ex.printStackTrace();}
        }
        scroolsklep.setContent(lista);
    }

}
