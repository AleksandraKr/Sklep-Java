package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class PanelProduktPracownik extends AnchorPane {
    private Produkt p;
    @FXML
    Label nazwaprod;
    @FXML
    Label opisprod;
    @FXML
    Label sztprod;
    @FXML
    Label cenaprod;

    public PanelProduktPracownik(Produkt p) throws Exception{
        this.p = p;
        FXMLLoader ppp = new FXMLLoader(getClass().getResource("/fxml/produktPracownik.fxml"));
        ppp.setRoot(this);
        ppp.setController(this);
        ppp.load();

        nazwaprod.setText(p.getNazwaProduktu());
        opisprod.setText(p.getOpisProduktu());
        if(p.getIloscSztuk() == 0)
            sztprod.setText("Produkt chwilowo niedostępny");
        else
            sztprod.setText("Ilość szt: " + p.getIloscSztuk().toString());
        cenaprod.setText(String.format("%.2f",p.getCena()) + " zł");
    }

}
