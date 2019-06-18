package Sklep;


import java.util.HashSet;
import java.util.Set;

public class StatusReklamacji {
    private int idStatusR;
    private String nazwaStatusR;
    private Set<Reklamacja> reklamacje = new HashSet<Reklamacja>();

    public int getIdStatusR() {
        return idStatusR;
    }

    public void setIdStatusR(int idStatusR) {
        this.idStatusR = idStatusR;
    }

    public String getNazwaStatusR() {
        return nazwaStatusR;
    }

    public void setNazwaStatusR(String nazwaStatusR) {
        this.nazwaStatusR = nazwaStatusR;
    }

    public Set<Reklamacja> getReklamacje(){ return reklamacje; }

    public void setReklamacje(Set<Reklamacja> reklamacje){ this.reklamacje = reklamacje; }
}
