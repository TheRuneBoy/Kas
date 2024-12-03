package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Tilmelding {
    private boolean foredragsHolder;
    private LocalDate ankomstDato;
    private LocalDate afrejseDato;
    Konference konference;
    Deltager deltager;
    Hotel valgtHotel;
    private ArrayList<HotelTilæg> hotelTilkøbs = new ArrayList<>();
    Ledsager ledsager;
    private ArrayList<Udflugt> valgteUdflugter = new ArrayList<>();
    int antaldage;

    public Tilmelding(boolean foredragsHolder, LocalDate ankomstDato, LocalDate afrejseDato, Deltager deltager, Konference konference, Ledsager ledsager, Firma firma) {
        this.foredragsHolder = foredragsHolder;
        this.ankomstDato = ankomstDato;
        this.afrejseDato = afrejseDato;
        this.deltager = deltager;  // Deltageren gemmes i tilmeldingen
        this.konference = konference;
        this.ledsager = ledsager;
        if (ankomstDato != null && afrejseDato != null) {
            this.antaldage = (int) ChronoUnit.DAYS.between(ankomstDato, afrejseDato) + 1;
        } else {
            this.antaldage = 0; // Default to 0 if dates are missing
        }
    }

    public boolean isForedragsHolder() {
        return foredragsHolder;
    }

    public Ledsager getLedsager() {
        return ledsager;
    }
    public void addhotelTilæg(HotelTilæg hotelTilæg){
        if (!hotelTilkøbs.contains(hotelTilæg)) {
            hotelTilkøbs.add(hotelTilæg);
        }
    }

    public Hotel getValgtHotel() {
        return valgtHotel;
    }

    public void setLedsager(Ledsager ledsager) {
        if (this.ledsager != ledsager) {
            this.ledsager = ledsager;
        }
    }

    public Deltager getDeltager() {
        return deltager;
    }

    public Konference getKonference() {
        return konference;
    }
    public void addUdflugt(Udflugt udflugt) {
        if (udflugt != null && !valgteUdflugter.contains(udflugt)) {
            valgteUdflugter.add(udflugt);
        }
    }


    public int beregnKonferencePris() {
        if (!foredragsHolder) {
            int konferencePris = 0;


            konferencePris = konference.getPris() * antaldage;

            return konferencePris;
        } else return 0;
    }

    public int beregnUdflgtsPris() {
        int udflugtsPris = 0;
        for (Udflugt udflugt : valgteUdflugter) {
            udflugtsPris += udflugt.getPris();
        }
        return udflugtsPris;

    }

    public int beregnHotelPris() {
        if (valgtHotel == null) {
            return 0;
        }
        int hotelPris;
        if (ledsager != null) {
            hotelPris = valgtHotel.getDobbelPris() * (antaldage-1);
        } else {
            hotelPris = valgtHotel.getEnkeltPris() * (antaldage-1);
        }
        return hotelPris;
    }

    public int beregnHotelTilkøbPris() {
        int hotelTilkøbsPris = 0;
        for (HotelTilæg hotelTilkøb : hotelTilkøbs) {
            hotelTilkøbsPris += hotelTilkøb.getPris();
        }

        return hotelTilkøbsPris * (antaldage-1);
    }


    public int BeregnTotalPris() {

       if (valgtHotel==null) {
           return beregnKonferencePris() + beregnUdflgtsPris();
       }
        return beregnKonferencePris() + beregnHotelPris() + beregnHotelTilkøbPris() + beregnUdflgtsPris();

    }
}
