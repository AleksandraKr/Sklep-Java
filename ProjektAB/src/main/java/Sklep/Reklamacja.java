package Sklep;

import java.util.Date;

public class Reklamacja {
    private int idReklamacja;
    private String opisProblemu;
    private StatusReklamacji statusReklamacji;
    private Produkt produkt;
    private Date dataR;
    private Uzytkownik uzytkownik;

    public int getIdReklamacja() {
        return idReklamacja;
    }

    public void setIdReklamacja(int idReklamacja) {
        this.idReklamacja = idReklamacja;
    }

    public String getOpisProblemu() {
        return opisProblemu;
    }

    public void setOpisProblemu(String opisProblemu) {
        this.opisProblemu = opisProblemu;
    }

    public StatusReklamacji getStatusReklamacji(){ return  statusReklamacji; }

    public void setStatusReklamacji(StatusReklamacji statusReklamacji){ this.statusReklamacji = statusReklamacji; }

    public Produkt getProdukt() { return produkt; }

    public void setProdukt(Produkt produkt) {
        this.produkt = produkt;
    }

    public Date getDataR() {
        return dataR;
    }

    public void setDataR(Date dataR) { this.dataR = dataR; }

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

}
