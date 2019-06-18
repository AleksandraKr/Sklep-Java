package Sklep;

import java.util.HashSet;
import java.util.Set;

public class Produkt {
    private int idProdukt;
    private String nazwaProduktu;
    private Double cena;
    private Integer iloscSztuk;
    private String opisProduktu;
    private TypProduktu typProduktu;
    private Set<Koszyk> koszyk = new HashSet<Koszyk>();
    private Set<ProduktZamowienie> produktZamowienie = new HashSet<ProduktZamowienie>();
    private Set<Reklamacja> reklamacje = new HashSet<Reklamacja>();

    public int getIdProdukt() {
        return idProdukt;
    }

    public void setIdProdukt(int idProdukt) {
        this.idProdukt = idProdukt;
    }

    public String getNazwaProduktu() {
        return nazwaProduktu;
    }

    public void setNazwaProduktu(String nazwaProduktu) {
        this.nazwaProduktu = nazwaProduktu;
    }

    public Double getCena() {
        return cena;
    }

    public void setCena(Double cena) {
        this.cena = cena;
    }

    public Integer getIloscSztuk() {
        return iloscSztuk;
    }

    public void setIloscSztuk(Integer iloscSztuk) {
        this.iloscSztuk = iloscSztuk;
    }

    public String getOpisProduktu() { return opisProduktu; }

    public void setOpisProduktu(String opisProduktu) { this.opisProduktu = opisProduktu; }

    public TypProduktu getTypProduktu() { return typProduktu; }

    public void setTypProduktu(TypProduktu typProduktu) { this.typProduktu = typProduktu; }

    public Set<Koszyk> getKoszyk(){ return koszyk; }

    public void setKoszyk(Set<Koszyk> koszyk){ this.koszyk = koszyk; }

    public Set<ProduktZamowienie> getProduktZamowienie(){ return produktZamowienie; }

    public void setProduktZamowienie(Set<ProduktZamowienie> produktZamowienie){ this.produktZamowienie = produktZamowienie; }

    public Set<Reklamacja> getReklamacje(){ return reklamacje; }

    public void setReklamacje(Set<Reklamacja> reklamacje){ this.reklamacje = reklamacje; }
}
