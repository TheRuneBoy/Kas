package controller;

import models.*;
import models.Tilmelding;
import models.Konference;
import storage.Storage;

import java.time.LocalDate;

public class Controller {



    public static Konference opretKonference(String navn, int antalDeltagere, String adresse, LocalDate ankomstDate, LocalDate afrejseDate, int pris) {
        try {
            // Opret en konference
            Konference konference = new Konference(navn, antalDeltagere, adresse, ankomstDate, afrejseDate, pris);
            // Gem konferencen
            Storage.storeKonference(konference);
            return konference;
        } catch (Exception e) {
            System.out.println("Fejl ved oprettelse af konference: " + e.getMessage());
            return null;
        }
    }


    public static Tilmelding opretTilmelding(Hotel valgtHotel, boolean foredragsHolder, LocalDate ankomstDato, LocalDate afrejseDato, Deltager deltager, Konference konference, Ledsager ledsager, Firma firma
    ) {
        Tilmelding tilmelding = konference.createTilmelding(valgtHotel, foredragsHolder, ankomstDato, afrejseDato, deltager, konference, ledsager, firma);
        Storage.storeTilmelding(tilmelding);
        return tilmelding;
    }

    public static Deltager opretDeltager(String navn, String adresse, String mobil, Firma firma){
        Deltager deltager = new Deltager(navn, adresse, mobil);
        Storage.storeDeltager(deltager);
        return deltager;
    }


    public static Udflugt opretUdflugt (String navn, LocalDate dato, int pris){
        Udflugt udflugt = new Udflugt(navn, dato, pris);
        Storage.storeUdflugt(udflugt);
        return udflugt;
    }
    public static Hotel opretHotel(String navn, String adresse, int enkeltPris, int dobbelPris){
        Hotel hotel = new Hotel(navn, adresse, enkeltPris, dobbelPris);
        Storage.storeHotel(hotel);
        return hotel;
    }
    public static HotelTilæg opretHotelTilæg(String navn, int pris){
        HotelTilæg hotelTilæg = new HotelTilæg(navn, pris);
        Storage.storeHoteltilkøb(hotelTilæg);
        return hotelTilæg;
    }
    public static Ledsager opretLedsager(String navn){
        Ledsager ledsager = new Ledsager(navn);
        Storage.storeLedsager(ledsager);
        return ledsager;
    }
    public static Firma opretFirma(String navn, String mobil){
        Firma firma = new Firma(navn, mobil);
        Storage.storeFirma(firma);
        return firma;
    }
}

