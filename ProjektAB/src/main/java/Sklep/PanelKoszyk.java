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


public class PanelKoszyk extends AnchorPane {
    private Produkt p;
    private Koszyk k;
    @FXML
    Label nazwaprod;
    @FXML
    Label cenaszt;
    @FXML
    Label iloscprod;
    @FXML
    Button usun;

    public PanelKoszyk(Produkt p, Koszyk k) throws Exception{
        this.p = p;
        this.k = k;
        FXMLLoader pk = new FXMLLoader(getClass().getResource("/fxml/koszyk.fxml"));
        pk.setRoot(this);
        pk.setController(this);
        pk.load();
        nazwaprod.setText(p.getNazwaProduktu());
        cenaszt.setText("Cena za sztuke: " + String.format("%.2f",p.getCena()) + " zł");
        iloscprod.setText("Iloeść sztuk: " + k.getIlosc().toString());
        usun.setOnAction(e->{usunProduktZKoszyka();});
    }

    @FXML
    public void usunProduktZKoszyka(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(k);
        session.getTransaction().commit();
        session.close();
        Parent par = usun.getParent();
        while(!(par instanceof ScrollPane))
            par = par.getParent();
        ScrollPane sp = (ScrollPane)par;
        VBox testowy = new VBox();
        testowy.setAlignment(Pos.CENTER);
        Label lab = new Label("Przedmiot został usunięty z koszyka");
        testowy.getChildren().add(lab);
        sp.setContent(testowy);
    }
}
