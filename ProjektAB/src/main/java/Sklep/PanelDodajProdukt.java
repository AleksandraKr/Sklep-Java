package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;


public class PanelDodajProdukt extends AnchorPane {
    private List<TypProduktu> typP;
    @FXML
    TextField nazwa;
    @FXML
    TextField opis;
    @FXML
    TextField cena;
    @FXML
    TextField ilosc;
    @FXML
    ChoiceBox typ;
    @FXML
    Button dodaj;
    @FXML
    Label blad1;
    @FXML
    Label blad2;
    @FXML
    Label blad3;
    @FXML
    Label blad4;
    @FXML
    Label blad5;

    public PanelDodajProdukt() throws Exception{
        FXMLLoader pdp = new FXMLLoader(getClass().getResource("/fxml/dodajProdukt.fxml"));
        pdp.setRoot(this);
        pdp.setController(this);
        pdp.load();
        dodaj.setOnAction(e->{dodajButton();});
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        TypedQuery query = session.createQuery("from TypProduktu");
        typP = query.getResultList();
        session.getTransaction().commit();
        session.close();
        int j = typP.size();
        for(int i=0; i<j; i++){
            typ.getItems().add(typP.get(i).getNazwaTypP());
        }
    }

    public void dodajButton(){
        boolean spr = true;
        if(nazwa.getText().isEmpty()){
            blad1.setText("Puste!");
            spr = false;
        }
        if(typ.getValue() == null){
            blad2.setText("Puste!");
            spr = false;
        }
        if(cena.getText().isEmpty()){
            blad3.setText("Puste!");
            spr = false;
        }
        if(ilosc.getText().isEmpty()){
            blad4.setText("Puste!");
            spr = false;
        }
        if(opis.getText().isEmpty()){
            blad5.setText("Puste!");
            spr = false;
        }

        if(spr == true){
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Produkt p = new Produkt();
            p.setNazwaProduktu(nazwa.getText());
            p.setCena( Double.parseDouble(cena.getText()));
            p.setIloscSztuk(Integer.parseInt(ilosc.getText()));
            p.setOpisProduktu(opis.getText());
            int j = typP.size();
            for(int i=0; i<j; i++){
                if(typ.getValue().equals(typP.get(i).getNazwaTypP())){
                    p.setTypProduktu(typP.get(i));
                }
            }
            session.save(p);
            session.getTransaction().commit();
            session.close();
        }

    }

}
