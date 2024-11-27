package models;

public class HotelTilæg {
    private String navn;
    private int pris;

    public HotelTilæg(String navn, int pris) {
        this.navn = navn;
        this.pris = pris;
    }

    public int getPris() {
        return pris;
    }
}
