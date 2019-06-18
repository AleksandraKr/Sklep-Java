package Sklep;


import java.util.HashSet;
import java.util.Set;

public class TypProduktu {
    private int idTypP;
    private String nazwaTypP;
    private Set<Produkt> produkt = new HashSet<Produkt>();

    public int getIdTypP() {
        return idTypP;
    }

    public void setIdTypP(int idTypP) {
        this.idTypP = idTypP;
    }

    public String getNazwaTypP() {
        return nazwaTypP;
    }

    public void setNazwaTypP(String nazwaTypP) {
        this.nazwaTypP = nazwaTypP;
    }

    public Set<Produkt> getProdukt(){ return produkt; }

    public void setProdukt(Set<Produkt> produkt){ this.produkt = produkt; }

}
