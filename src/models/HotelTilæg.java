package models;

public class HotelTilæg {
    private String navn;
    private int pris;

    public HotelTilæg(String navn, int pris) {
        this.navn = navn;
        this.pris = pris;
    }

    @Override
    public String toString() {
        return navn + '\'' +
                " pris = " + pris;
    }

    public String getNavn() {
        return navn;
    }

    public int getPris() {
        return pris;
    }
}
