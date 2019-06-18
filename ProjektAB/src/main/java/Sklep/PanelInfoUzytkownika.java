package Sklep;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class PanelInfoUzytkownika extends AnchorPane {
    @FXML
    Label email;
    @FXML
    Label imie;
    @FXML
    Label nazwisko;

    public PanelInfoUzytkownika() throws Exception{
        FXMLLoader piu = new FXMLLoader(getClass().getResource("/fxml/infoUzytkownik.fxml"));
        piu.setRoot(this);
        piu.setController(this);
        piu.load();
        email.setText(SesjaUzytkownika.getInstance().getUzytkownik().getNazwaUzytkownika());
        imie.setText(SesjaUzytkownika.getInstance().getUzytkownik().getImie());
        nazwisko.setText(SesjaUzytkownika.getInstance().getUzytkownik().getNazwisko());
    }
}
