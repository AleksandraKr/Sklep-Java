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

public class PanelAdresUzytkownika extends AnchorPane {
    private Adres a;
    @FXML
    Label daneadresu;
    @FXML
    Button usun;

    public PanelAdresUzytkownika(Adres a) throws Exception{
        this.a = a;
        FXMLLoader ap = new FXMLLoader(getClass().getResource("/fxml/adresUzytkownika.fxml"));
        ap.setRoot(this);
        ap.setController(this);
        ap.load();
        String s = a.getUlica() + "/" + a.getNrMieszkania().toString() + ", " + a.getMiasto() + " " +a.getKodPocztowy().toString();
        daneadresu.setText(s);
        usun.setOnAction(e->{usunButton();});
    }

    public void usunButton(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        a.setUzytkownik(null);
        session.update(a);
        session.getTransaction().commit();
        session.close();
        Parent par = usun.getParent();
        while(!(par instanceof ScrollPane))
            par = par.getParent();
        ScrollPane sp = (ScrollPane)par;
        VBox testowy = new VBox();
        testowy.setAlignment(Pos.CENTER);
        Label lab = new Label("Usunięto adres");
        testowy.getChildren().add(lab);
        sp.setContent(testowy);
    }
}
