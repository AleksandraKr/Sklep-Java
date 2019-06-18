package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ReklamacjePanel extends AnchorPane {
    private Reklamacja r;
    @FXML
    Label nrreklamacji;
    @FXML
    Label opis;
    @FXML
    Label rekprzedmiot;
    @FXML
    Label status;

    public ReklamacjePanel(Reklamacja r) throws Exception{
        this.r = r;
        FXMLLoader rp = new FXMLLoader(getClass().getResource("/fxml/reklamacja.fxml"));
        rp.setRoot(this);
        rp.setController(this);
        rp.load();
        nrreklamacji.setText("Reklamacja nr: " + r.getIdReklamacja() +" z dnia " + r.getDataR().toString().substring(0,10));
        opis.setText(r.getOpisProblemu());
        rekprzedmiot.setText(r.getProdukt().getNazwaProduktu());
        status.setText(r.getStatusReklamacji().getNazwaStatusR());
    }
}
