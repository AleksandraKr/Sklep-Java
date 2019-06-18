package Sklep;

import java.util.HashSet;
import java.util.Set;

public class StatusZamowienia {
    private int idStatusZ;
    private String nazwaStatusZ;
    private Set<Zamowienie> zamowienia = new HashSet<Zamowienie>();

    public int getIdStatusZ() {
        return idStatusZ;
    }

    public void setIdStatusZ(int idStatusZ) {
        this.idStatusZ = idStatusZ;
    }

    public String getNazwaStatusZ() {
        return nazwaStatusZ;
    }

    public void setNazwaStatusZ(String nazwaStatusZ) {
        this.nazwaStatusZ = nazwaStatusZ;
    }

    public Set<Zamowienie> getZamowienia(){ return zamowienia; }

    public void setZamowienia(Set<Zamowienie> zamowienia){ this.zamowienia = zamowienia; }
}
