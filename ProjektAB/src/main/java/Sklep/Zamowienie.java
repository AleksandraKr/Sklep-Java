package Sklep;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Zamowienie {
    private int idZamowienie;
    private Uzytkownik uzytkownik;
    private StatusZamowienia statusZamowienia;
    private Date dataZ;
    private Adres adres;
    private Double koszt;
    private Set<ProduktZamowienie> produktZamowienie = new HashSet<ProduktZamowienie>();

    public int getIdZamowienie() {
        return idZamowienie;
    }

    public void setIdZamowienie(int idZamowienie) {
        this.idZamowienie = idZamowienie;
    }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public StatusZamowienia getStatusZamowienia() { return statusZamowienia; }

    public void setStatusZamowienia(StatusZamowienia statusZamowienia) {
        this.statusZamowienia = statusZamowienia;
    }

    public Date getDataZ() {
        return dataZ;
    }

    public void setDataZ(Date dataZ) { this.dataZ = dataZ; }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) { this.adres = adres; }

    public Double getKoszt() {
        return koszt;
    }

    public void setKoszt(Double koszt) { this.koszt = koszt; }

    public Set<ProduktZamowienie> getProduktZamowienie(){ return produktZamowienie; }

    public void setProduktZamowienie(Set<ProduktZamowienie> produktZamowienie){ this.produktZamowienie = produktZamowienie; }
}
