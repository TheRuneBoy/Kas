package models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Konference {
    private String navn;
    private int deltagere;
    private String adresse;
    private LocalDate startDato;
    private LocalDate slutDato;
    private int pris;
    private ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();
    private ArrayList<Hotel> hoteler = new ArrayList<>();
    private ArrayList<Udflugt> udflugter = new ArrayList<>();

    public Konference(String navn, int deltagere, String adresse, LocalDate startDato, LocalDate slutDato, int pris) {
        this.navn = navn;
        this.deltagere = deltagere;
        this.adresse = adresse;
        this.startDato = startDato;
        this.slutDato = slutDato;
        this.pris = pris;
    }

    public int getPris() {
        return pris;
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
    public void addHotel(Hotel hotel){
        if (!hoteler.contains(hotel)){
            hoteler.add(hotel);
        }
    }
    public void removeHotel(Hotel hotel){
        if (hoteler.contains(hotel)){
            hoteler.remove(hotel);
        }
    }
    public Tilmelding createTilmelding(Hotel valgtHotel, boolean foredragsHolder, LocalDate ankomstDato, LocalDate afrejseDato, Deltager deltager, Konference konference, Ledsager ledsager){
        Tilmelding tilmelding = new Tilmelding(valgtHotel, foredragsHolder, ankomstDato, afrejseDato, deltager,this,ledsager);
        tilmeldinger.add(tilmelding);
        return tilmelding;

    }
    public void removeTilmelding(Tilmelding tilmelding){
        if (tilmeldinger.contains(tilmelding)){
            tilmeldinger.remove(tilmelding);
        }
    }

}
