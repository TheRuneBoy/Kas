package gui;

import controller.Controller;
import javafx.application.Application;
import models.*;

import java.time.LocalDate;
import java.util.ArrayList;

public class AppTabs {
    public static void main(String[] args) {
        initStorage();
        Application.launch(StartWindow.class);
    }
    private static void initStorage() {
        Konference konference1 = Controller.opretKonference("Himmel og Hav", 2000, "Vej vej", LocalDate.of(2024, 12, 25), LocalDate.of(2024, 12, 26), 1500);
        Deltager deltager1 = Controller.opretDeltager("Allan", "Allanvej", "20304050", null);
        Deltager deltager2 = Controller.opretDeltager("Arne", "Arnevej", "10304500", null);
        Deltager deltager3 = Controller.opretDeltager("Bjarne", "Bjarnevej", "23434535", null);
        Deltager deltager4 = Controller.opretDeltager("Klaus", "Vejvej", "405930203", null);

        Hotel hotelHvidSvane = Controller.opretHotel("Den Hvide Svane", "Hotelvej 1", 1050, 1250);
        Hotel hotelPhønix = Controller.opretHotel("Høtel Phønix", "Hotelvej 2", 700, 800);
        Hotel hotelTusind = Controller.opretHotel("Pension Tusindfryd", "Hotelvej 3", 500, 600);

        //Tilføj hotellerne til konferencen
        konference1.addHotel(hotelHvidSvane);
        konference1.addHotel(hotelPhønix);
        konference1.addHotel(hotelTusind);

        HotelTilæg tilægWifiHvid = Controller.opretHotelTilæg("WIFI", 50);
        HotelTilæg tilægBad = Controller.opretHotelTilæg("Bad", 200);
        HotelTilæg tilægWifi = Controller.opretHotelTilæg("WIFI", 75);
        HotelTilæg tilægMorgenmad = Controller.opretHotelTilæg("Morgenmad", 100 );

        //Tilføj hoteltilæg til hotellet
        hotelHvidSvane.addHotelTilæg(tilægWifiHvid);
        hotelPhønix.addHotelTilæg(tilægBad);
        hotelPhønix.addHotelTilæg(tilægWifi);
        hotelTusind.addHotelTilæg(tilægMorgenmad);

        //Opret udflugter
        Udflugt udflugt1 = Controller.opretUdflugt("Byrundtur, Odense", LocalDate.of(2024,12,18), 125);
        Udflugt udflugt2 = Controller.opretUdflugt("Egeskov", LocalDate.of(2024, 12, 19), 75);
        Udflugt udflugt3 = Controller.opretUdflugt("Trapholt Museum, Kolding", LocalDate.of(2024, 12, 20), 200);
        //Opret Arraylist valgte udflugter
        ArrayList<Udflugt> valgteUdfugter = new ArrayList<>();
        valgteUdfugter.add(udflugt1);
        valgteUdfugter.add(udflugt3);

        //Tilføj udflugter til konferencen
        konference1.addUdflugt(udflugt1);
        konference1.addUdflugt(udflugt2);
        konference1.addUdflugt(udflugt3);

        Tilmelding tilmeldingAllan = Controller.opretTilmelding(hotelHvidSvane, false, LocalDate.of(2024, 12, 25), LocalDate.of(2024, 12, 26), deltager1, konference1, null, null);
        Tilmelding tilmeldingArne = Controller.opretTilmelding(hotelHvidSvane, true, LocalDate.of(2024, 12, 25), LocalDate.of(2024, 12, 26), deltager2, konference1, null, null);
        Ledsager ledsager1 = Controller.opretLedsager("Mette");
        ArrayList<HotelTilæg> hotelTilægListe = new ArrayList<>();
        hotelTilægListe.add(tilægWifiHvid);
        Tilmelding tilmeldingBjarne = Controller.opretTilmelding(hotelHvidSvane, true, LocalDate.of(2024, 12, 25), LocalDate.of(2024, 12, 26), deltager3, konference1, ledsager1, null);

        tilmeldingBjarne.beregnKonferencePris();
        tilmeldingBjarne.beregnHotelPris();
        tilmeldingBjarne.beregnHotelTilkøbPris();
        tilmeldingBjarne.beregnUdflgtsPris();
        tilmeldingBjarne.BeregnTotalPris();
    }
}
