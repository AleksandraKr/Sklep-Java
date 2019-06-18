package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.hibernate.Session;
import javax.persistence.TypedQuery;
import java.net.URL;
import java.util.*;

public class Klient implements Initializable {

    private List<Produkt> produkty;
    private List<Koszyk> koszyki;
    private List<TypProduktu> typP;
    private Button zamow = new Button("Zamów");
    private Button dodajadres = new Button("Dodaj Adres");
    private List<StatusZamowienia> sz;
    private List<Adres> adresy;
    private List<Adres> adresynull;
    private List<Zamowienie> zamowienia;
    private List<Reklamacja> reklamacje;
    private List<StatusReklamacji> sr;
    private Button zamow2 = new Button("Zamów");
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
    ScrollPane scroolkoszyk;
    @FXML
    Button koszyk;
    @FXML
    Button mojezakupy;
    @FXML
    Button freklamacji;
    @FXML
    Button mojereklamacje;
    @FXML
    ScrollPane scroolkonto;
    @FXML
    Button listaadresow;
    @FXML
    Button danekonta;

    @Override
    public void initialize(URL xd, ResourceBundle rs){
        wezProdukty();
        wszystkoButton();
        //wezKoszyk();
        koszykButton();
        danekontaButton();
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
        koszyk.setOnAction(e->{koszykButton();});
        zamow.setOnAction(e->{zamowButton();});
        mojezakupy.setOnAction(e->{mojezakupyButton();});
        freklamacji.setOnAction(e->{freklamacjiButton();});
        mojereklamacje.setOnAction(e->{mojereklamacjeButton();});
        danekonta.setOnAction(e->{danekontaButton();});
        listaadresow.setOnAction(e->{listaadresowButton();});
        dodajadres.setOnAction(e->{dodajadresButton();});
    }

    public void wezProdukty(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        TypedQuery query = session.createQuery("from Produkt");
        produkty = query.getResultList();
        query = session.createQuery("from TypProduktu");
        typP = query.getResultList();
        query = session.createQuery("from Koszyk where uzytkownik.nazwaUzytkownika = '" + SesjaUzytkownika.getInstance().getUzytkownik().getNazwaUzytkownika()+"'");
        koszyki = query.getResultList();
        query = session.createQuery("from StatusZamowienia");
        sz = query.getResultList();
        query = session.createQuery("from Adres where uzytkownik.nazwaUzytkownika = '" + SesjaUzytkownika.getInstance().getUzytkownik().getNazwaUzytkownika()+"'");
        adresy = query.getResultList();
        query = session.createQuery("from Adres where uzytkownik = null");
        adresynull = query.getResultList();
        query = session.createQuery("from Zamowienie where uzytkownik.nazwaUzytkownika = '" + SesjaUzytkownika.getInstance().getUzytkownik().getNazwaUzytkownika()+"'");
        zamowienia = query.getResultList();
        query = session.createQuery("from Reklamacja where uzytkownik.nazwaUzytkownika = '" + SesjaUzytkownika.getInstance().getUzytkownik().getNazwaUzytkownika()+"'");
        reklamacje = query.getResultList();
        query = session.createQuery("from StatusReklamacji ");
        sr = query.getResultList();
        session.getTransaction().commit();
        session.close();
    }

    public void wszystkoButton(){
        wezProdukty();
        VBox lista = new VBox();
        int j = produkty.size();
        for(int i=0; i<j; i++){
            try{
                PanelProduktu pp = new PanelProduktu(produkty.get(i));
                lista.getChildren().add(pp);
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
                    PanelProduktu pp = new PanelProduktu(produkty.get(i));
                    lista.getChildren().add(pp);
                }catch(Exception ex){ex.printStackTrace();}
            }
        }
        scroolsklep.setContent(lista);
    }

    public void koszykButton(){
        wezProdukty();
        VBox lista = new VBox();
        int j = koszyki.size();
        for(int i=0; i<j; i++){
            try{
                PanelKoszyk pk = new PanelKoszyk(koszyki.get(i).getProdukt(), koszyki.get(i));
                lista.getChildren().add(pk);
            }catch(Exception ex){ex.printStackTrace();}
        }
        if(j!=0){
            lista.getChildren().add(zamow);
        }
        scroolkoszyk.setContent(lista);
    }

    public void zamowButton(){
        wezProdukty();
        VBox lista = new VBox();
        try{
            ZamowPanel zp = new ZamowPanel();
            lista.getChildren().add(zp);
        }catch(Exception ex){ex.printStackTrace();}
        int j = adresy.size();
        for(int i=0; i<j; i++){
            try{
            AdresPanel ap = new AdresPanel(adresy.get(i));
                lista.getChildren().add(ap);
            }catch(Exception ex){ex.printStackTrace();}
        }
        scroolkoszyk.setContent(lista);

    }

    public void mojezakupyButton(){
        wezProdukty();
        VBox lista = new VBox();
        int j = zamowienia.size();
        for(int i=0; i<j; i++){
            try{
                Zamow2Panel zp = new Zamow2Panel(zamowienia.get(i));
                lista.getChildren().add(zp);
            }catch(Exception ex){ex.printStackTrace();}
        }
        scroolkoszyk.setContent(lista);
    }

    public void freklamacjiButton(){
        VBox formularz = new VBox();
        try{
            FormularzReklamacji fr = new FormularzReklamacji();
            formularz.getChildren().add(fr);
        }catch(Exception ex){ex.printStackTrace();}
        scroolkoszyk.setContent(formularz);
    }

    public void mojereklamacjeButton(){
        wezProdukty();
        VBox lista = new VBox();
        int j = reklamacje.size();
        for(int i=0; i<j; i++){
            try{
                ReklamacjePanel rp = new ReklamacjePanel(reklamacje.get(i));
                lista.getChildren().add(rp);
            }catch(Exception ex){ex.printStackTrace();}
        }
        scroolkoszyk.setContent(lista);
    }

    public void danekontaButton(){
        GridPane formularz = new GridPane();
        try{
            PanelInfoUzytkownika piu = new PanelInfoUzytkownika();
            formularz.getChildren().add(piu);
        }catch(Exception ex){ex.printStackTrace();}
        scroolkonto.setContent(formularz);
    }

    public void listaadresowButton(){
        wezProdukty();
        VBox lista = new VBox();
        int j = adresy.size();
        for(int i=0; i<j; i++){
            try{
                PanelAdresUzytkownika pau = new PanelAdresUzytkownika(adresy.get(i));
                lista.getChildren().add(pau);
            }catch(Exception ex){ex.printStackTrace();}
        }
        lista.getChildren().add(dodajadres);
        scroolkonto.setContent(lista);
    }

    public void dodajadresButton(){
        GridPane formularz = new GridPane();
        try{
            PanelDodajAdres pda = new PanelDodajAdres();
            formularz.getChildren().add(pda);
        }catch(Exception ex){ex.printStackTrace();}
        scroolkonto.setContent(formularz);
    }
}
