package Sklep;

import java.util.Objects;

public class ProduktZamowienie {
    private int idPz;
    private int iloscP;
    private Produkt produkt;
    private Zamowienie zamowienie;

    public int getIdPz() {
        return idPz;
    }

    public void setIdPz(int idPz) {
        this.idPz = idPz;
    }

    public int getIloscP() {
        return iloscP;
    }

    public void setIloscP(int iloscP) {
        this.iloscP = iloscP;
    }

    public Produkt getProdukt() { return produkt; }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public Zamowienie getZamowienie() { return zamowienie; }

    public void setZamowienie(Zamowienie zamowienie) {
        this.zamowienie = zamowienie;
    }
}
