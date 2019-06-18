package Sklep;
import java.util.HashSet;
import java.util.Set;

public class TypUzytkownika {
    private int idTypU;
    private String nazwaTypU;
    private Set<Uzytkownik> uzytkownicy = new HashSet<Uzytkownik>();

    public int getIdTypU() {
        return idTypU;
    }

    public void setIdTypU(int idTypU) {
        this.idTypU = idTypU;
    }

    public String getNazwaTypU() {
        return nazwaTypU;
    }

    public void setNazwaTypU(String nazwaTypU) {
        this.nazwaTypU = nazwaTypU;
    }

    public Set<Uzytkownik> getUzytkownicy(){ return uzytkownicy; }

    public void setUzytkownicy(Set<Uzytkownik> uzytkownicy){ this.uzytkownicy = uzytkownicy; }
}
