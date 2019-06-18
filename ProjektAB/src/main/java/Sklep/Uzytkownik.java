package Sklep;


import java.util.HashSet;
import java.util.Set;

public class Uzytkownik {
    private String nazwaUzytkownika;
    private String haslo;
    private String imie;
    private String nazwisko;
    private TypUzytkownika typUzytkownika;
    private Set<Zamowienie> zamowienia = new HashSet<Zamowienie>();
    private Set<Koszyk> koszyk = new HashSet<Koszyk>();
    private Set<Reklamacja> reklamacje = new HashSet<Reklamacja>();
    private Set<Adres> adres = new HashSet<Adres>();

    public String getNazwaUzytkownika() {
        return nazwaUzytkownika;
    }

    public void setNazwaUzytkownika(String nazwaUzytkownika) {
        this.nazwaUzytkownika = nazwaUzytkownika;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public TypUzytkownika getTypUzytkownika() {
        return typUzytkownika;
    }

    public void setTypUzytkownika(TypUzytkownika typUzytkownika) {
        this.typUzytkownika = typUzytkownika;
    }

    public Set<Zamowienie> getZamowienia(){ return zamowienia; }

    public void setZamowienia(Set<Zamowienie> zamowienia){ this.zamowienia = zamowienia; }

    public Set<Koszyk> getKoszyk(){ return koszyk; }

    public void setKoszyk(Set<Koszyk> koszyk){ this.koszyk = koszyk; }

    public Set<Reklamacja> getReklamacje(){ return reklamacje; }

    public void setReklamacje(Set<Reklamacja> reklamacje){ this.reklamacje = reklamacje; }

    public Set<Adres> getAdres(){ return adres; }

    public void setAdres(Set<Adres> adres){ this.adres = adres; }
}
