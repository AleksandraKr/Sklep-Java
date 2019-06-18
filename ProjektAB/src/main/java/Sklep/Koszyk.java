package Sklep;


public class Koszyk {
    private int idKoszyk;
    private Integer ilosc;
    private Produkt produkt;
    private Uzytkownik uzytkownik;

    public int getIdKoszyk() {
        return idKoszyk;
    }

    public void setIdKoszyk(int idKoszyk) { this.idKoszyk = idKoszyk; }

    public Integer getIlosc() {
        return ilosc;
    }

    public void setIlosc(Integer ilosc) {
        this.ilosc = ilosc;
    }

    public Produkt getProdukt() {
        return produkt;
    }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

}
