package storage;

import javafx.collections.ObservableList;
import models.*;

import java.util.ArrayList;

public class Storage {

    //Deltager
    private static ArrayList<Deltager> deltagerer = new ArrayList<>();
    private static Konference selectedKonference;

    public static ArrayList<Deltager> getDeltagerer() {
        return new  ArrayList<>(deltagerer);
    }//det metod return nye kope og beskytte originale


    public static void storeDeltager(Deltager deltager) {
        deltagerer.add(deltager);
    }

    //Hotel
    private static ArrayList<Hotel> hoteller = new ArrayList<>();

    public static ArrayList<Hotel> getHoteller() {
        return new ArrayList<>(hoteller);
    }

    public static void storeHotel(Hotel hotel) {
        hoteller.add(hotel);
    }

    //Udflugt
    private static ArrayList<Udflugt> udflugter = new ArrayList<>();

    public static ArrayList<Udflugt> getUdflugter() {
        return new ArrayList<>(udflugter);
    }

    public static void storeUdflugt(Udflugt udflugt) {
        udflugter.add(udflugt);
    }

    //Ledsager
    private static ArrayList<Ledsager> ledsagerer = new ArrayList<>();

    public static ArrayList<Ledsager> getLedsagerer() {
        return new ArrayList<>(ledsagerer);
    }

    public static void storeLedsager(Ledsager ledsager) {
        ledsagerer.add(ledsager);
    }

    //Tilmelding
    private static ArrayList<Tilmelding> tilmeldinger = new ArrayList<>();

    public static ArrayList<Tilmelding> getTilmeldinger() {
        return new ArrayList<>(tilmeldinger);
    }

    public static void storeTilmelding(Tilmelding tilmelding) {
        tilmeldinger.add(tilmelding);
    }

    //Hoteltilkøb
    private static ArrayList<HotelTilæg> hotelTilægs = new ArrayList<>();

    public static ArrayList<HotelTilæg> getHotelTilægs() {
        return new ArrayList<>(hotelTilægs);
    }

    public static void storeHoteltilkøb(HotelTilæg hotelTilæg) {
        hotelTilægs.add(hotelTilæg);
    }

    //Konference
    private static ArrayList<Konference> konferencer = new ArrayList<>();

    public static ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }

    public static void storeKonference(Konference konference) {
        konferencer.add(konference);
    }

    private static ArrayList<Firma> firmaer = new ArrayList<>();

    public static ArrayList<Firma> getFirmaer(){
        return new ArrayList<>(firmaer);
    }
    public static void storeFirma(Firma firma){
        firmaer.add(firma);
    }

    // Metode til at få den valgte konference
    public static Konference getSelectedKonference() {
        return selectedKonference;
    }

    // Metode til at sætte den valgte konference
    public static void setSelectedKonference(Konference konference) {
        selectedKonference = konference;
    }
}
