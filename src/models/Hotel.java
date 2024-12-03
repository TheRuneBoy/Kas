package models;

import java.util.ArrayList;

public class Hotel {
    private String navn;

    private String adresse;
    private int enkeltPris;
    private int dobbelPris;
    private ArrayList<HotelTilæg> hotelTilægs = new ArrayList<>();

    public Hotel(String navn, String adresse, int enkeltPris, int dobbelPris) {
        this.navn = navn;
        this.adresse = adresse;
        this.enkeltPris = enkeltPris;
        this.dobbelPris = dobbelPris;
    }

    public int getEnkeltPris() {
        return enkeltPris;
    }
    public int getDobbelPris(){
        return dobbelPris;
    }

    public void addHotelTilæg(HotelTilæg hotelTilæg) {
        if (!hotelTilægs.contains(hotelTilæg)) {
            hotelTilægs.add(hotelTilæg);
        }
    }

    public void removeHotelTilæg(HotelTilæg hotelTilæg) {
        if (hotelTilægs.contains(hotelTilæg)) {
            hotelTilægs.remove(hotelTilæg);
        }
    }

    public String getNavn() {
    return null;
    }
}
