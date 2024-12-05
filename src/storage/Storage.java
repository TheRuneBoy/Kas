package storage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class Storage {

    // Deltager
    private static ObservableList<Deltager> deltagerer = FXCollections.observableArrayList();

    public static ObservableList<Deltager> getDeltagerer() {
        return deltagerer;
    }

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

    public static void storeHotelTilæg(HotelTilæg hotelTilæg) {
        hotelTilægs.add(hotelTilæg);
    }

    // Konference
    private static ObservableList<Konference> konferencer = FXCollections.observableArrayList();
    private static ObservableList<Runnable> konferenceChangeListeners = FXCollections.observableArrayList();

    static {
        konferencer.add(new Konference("Hav og Himmel", 2000, "Odense Universitet", LocalDate.of(2024, 12, 16), LocalDate.of(2024, 12, 18), 1500));
    }

    public static ObservableList<Konference> getKonferencer() {
        return konferencer;
    }

    public static void storeKonference(Konference konference) {
        konferencer.add(konference);
        for (Runnable listener : konferenceChangeListeners) {
            listener.run();
        }
    }

    private static ArrayList<Firma> firmaer = new ArrayList<>();

    public static ArrayList<Firma> getFirmaer(){
        return new ArrayList<>(firmaer);
    }
    public static void storeFirma(Firma firma){
        firmaer.add(firma);
    }


    public static void addKonferenceChangeListener(Runnable listener) {
        konferenceChangeListeners.add(listener);
    }

}
