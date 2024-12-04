package storage;

import controller.Controller;
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

    //Opret Hoteller og HotelTilæg
    static {
        Hotel hotelHvidSvane = Controller.opretHotel("Den Hvide Svane", "Hotelvej 1", 1050, 1250);
        Hotel hotelPhønix = Controller.opretHotel("Høtel Phønix", "Hotelvej 2", 700, 800);
        Hotel hotelTusind = Controller.opretHotel("Pension Tusindfryd", "Hotelvej 3", 500, 600);

        HotelTilæg tilægWifiHvid = Controller.opretHotelTilæg("WIFI", 50);
        HotelTilæg tilægBad = Controller.opretHotelTilæg("Bad", 200);
        HotelTilæg tilægWifi = Controller.opretHotelTilæg("WIFI", 75);
        HotelTilæg tilægMorgenmad = Controller.opretHotelTilæg("Morgenmad", 100 );

        //Tilføj HotelTilæg til hoteller
        Controller.tilføjHotelTilægTilHotel(hotelHvidSvane, tilægWifiHvid);
        Controller.tilføjHotelTilægTilHotel(hotelPhønix, tilægBad);
        Controller.tilføjHotelTilægTilHotel(hotelPhønix, tilægWifi);
        Controller.tilføjHotelTilægTilHotel(hotelTusind, tilægMorgenmad);
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

    public static ArrayList<Firma> getFirmaer() {
        return new ArrayList<>(firmaer);
    }

    public static void storeFirma(Firma firma) {
        firmaer.add(firma);
    }


    public static void addKonferenceChangeListener(Runnable listener) {
        konferenceChangeListeners.add(listener);
    }

    private static void initStorage() {
        Konference konference1 = new Konference("Himmel og Hav", 2000, "Vej vej", LocalDate.of(2024, 12, 25), LocalDate.of(2024, 12, 26), 1500);
        Deltager deltager1 = new Deltager("Allan", "Allanvej", "20304050");
        Deltager deltager2 = new Deltager("Arne", "Arnevej", "10304500");
        Deltager deltager3 = new Deltager("Bjarne", "Bjarnevej", "23434535");
        Deltager deltager5 = new Deltager("Klaus", "Vejvej", "405930203");
        Tilmelding tilmeldingAllan = new Tilmelding(false, LocalDate.of(2024, 12, 25), LocalDate.of(2024, 12, 26), deltager1, konference1, null, null);
        Tilmelding tilmeldingArne = new Tilmelding(true, LocalDate.of(2024, 12, 25), LocalDate.of(2024, 12, 26), deltager2, konference1, null, null);
        Ledsager ledsager1 = new Ledsager("Mette");
        Tilmelding tilmeldingBjarne = new Tilmelding(true, LocalDate.of(2024, 12, 25), LocalDate.of(2024, 12, 26), deltager3, konference1, ledsager1, null);

    }
}

