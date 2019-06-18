package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

public class FormularzReklamacji extends AnchorPane{
    private List<Produkt> produkty;
    private List<StatusReklamacji> sr;
    @FXML
    TextField opis;
    @FXML
    TextField nazwaprzedmiotu;
    @FXML
    Label blad;
    @FXML
    Button zatwierdz;

    public FormularzReklamacji() throws Exception{
        FXMLLoader fr = new FXMLLoader(getClass().getResource("/fxml/formularzreklamacji.fxml"));
        fr.setRoot(this);
        fr.setController(this);
        fr.load();
        zatwierdz.setOnAction(e->{zatwierdzButton(); });
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        TypedQuery query = session.createQuery("from Produkt");
        produkty = query.getResultList();
        query = session.createQuery("from StatusReklamacji ");
        sr = query.getResultList();
        session.getTransaction().commit();
        session.close();
    }

    public void zatwierdzButton(){
        int j = produkty.size();
        Produkt p = null;
        boolean spr = false;
        String n = nazwaprzedmiotu.getText();
        for(int i=0; i<j; i++){
            if(n.equals(produkty.get(i).getNazwaProduktu())){
                p = produkty.get(i);
                spr = true;
            }
        }
        if(spr == true){
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Reklamacja r = new Reklamacja();
            r.setDataR(new Date());
            r.setOpisProblemu(opis.getText());
            r.setProdukt(p);
            r.setStatusReklamacji(sr.get(0));
            r.setUzytkownik(SesjaUzytkownika.getInstance().getUzytkownik());
            session.save(r);
            session.getTransaction().commit();
            session.close();
            Parent par = zatwierdz.getParent();
            while(!(par instanceof ScrollPane))
                par = par.getParent();
            ScrollPane sp = (ScrollPane)par;
            VBox testowy = new VBox();
            testowy.setAlignment(Pos.CENTER);
            Label lab = new Label("Twoja reklamacja została wysłana");
            testowy.getChildren().add(lab);
            sp.setContent(testowy);
        }
        else{
            blad.setText("Nie ma takiego przedmiotu w naszej bazie (najepiej skopiować nazwe ze sklepu)");
        }
    }
}
