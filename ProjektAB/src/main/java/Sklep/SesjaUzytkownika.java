package Sklep;

public class SesjaUzytkownika {
    private static SesjaUzytkownika instance;

    private Uzytkownik uzytkownik;

    private SesjaUzytkownika(Uzytkownik uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public static SesjaUzytkownika getInstance() {
        return instance;
    }

    public static void setInstance(Uzytkownik uzytkownik){instance = new SesjaUzytkownika(uzytkownik);}

    public Uzytkownik getUzytkownik() {
        return uzytkownik;
    }

    public void cleanSesjaUzytkownika() {
        uzytkownik = null;// or null
        instance = null;
    }
}
