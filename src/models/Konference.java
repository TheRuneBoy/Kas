package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Konference {
    private String navn;
    private int antalDeltagere;
    private String adresse;
    private LocalDate startDato;
    private LocalDate slutDato;
    private int pris;
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private ArrayList<Hotel> hoteller = new ArrayList<>();
    private ArrayList<Udflugt> udflugter = new ArrayList<>();
    private List<Deltager> deltagere;

    public String getNavn() {
        return navn;
    }

    public String getAdresse() {
        return adresse;
    }

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    public ArrayList<Tilmelding> getTilmeldinger() {
        return tilmeldinger;
    }

    public ArrayList<Hotel> getHoteller() {
        return hoteller;
    }

    public ArrayList<Udflugt> getUdflugter() {
        return udflugter;
    }

    public Konference(String navn, int antalDeltagere, String adresse, LocalDate startDato, LocalDate slutDato, int pris) {
        this.navn = navn;
        this.antalDeltagere = antalDeltagere;
        this.adresse = adresse;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.pris = pris;
        this.deltagere = new ArrayList<>();
    }

    @Override
    public String toString() {
        return navn;
    }



    public int getPris() {
        return pris;
    }


    public ArrayList<Deltager> getAntalDeltagere() {
        ArrayList<Deltager> deltagere = new ArrayList<>();
        for (Tilmelding tilmelding : tilmeldinger) {
            deltagere.add(tilmelding.getDeltager()); // Henter deltageren fra tilmeldingen
        }
        return deltagere;
    }

    public void addUdflugt(Udflugt udflugt) {
        if (!udflugter.contains(udflugt)) {
            udflugter.add(udflugt);
        }
    }

    public void removeUdflugt(Udflugt udflugt) {
        if (udflugter.contains(udflugt)) {
            udflugter.remove(udflugt);
        }
    }

    public void addHotel(Hotel hotel) {
        if (!hoteller.contains(hotel)) {
            hoteller.add(hotel);
        }
    }

    public void removeHotel(Hotel hotel) {
        if (hoteller.contains(hotel)) {
            hoteller.remove(hotel);
        }
    }

    public Tilmelding createTilmelding(Hotel valgtHotel, ArrayList<Udflugt> valgteUdflugter, boolean foredragsHolder, LocalDate ankomstDato, LocalDate afrejseDato, Deltager deltager, Konference konference, Ledsager ledsager, Firma firma) {
        Tilmelding tilmelding = new Tilmelding(valgtHotel, valgteUdflugter, foredragsHolder, ankomstDato, afrejseDato, deltager, this, ledsager, firma);
        tilmeldinger.add(tilmelding);
        return tilmelding;

    }

    public void removeTilmelding(Tilmelding tilmelding) {
        if (tilmeldinger.contains(tilmelding)) {
            tilmeldinger.remove(tilmelding);
        }
    }

    public void addTilmelding(Tilmelding nyTilmelding) {
        if (!tilmeldinger.contains(nyTilmelding)){
            tilmeldinger.add(nyTilmelding);
        }
    }

    public void addDeltager(Deltager deltager) {
        this.deltagere.add(deltager);
    }

    public int getMaxAntalDeltagere() {
        return this.antalDeltagere;
    }
}
