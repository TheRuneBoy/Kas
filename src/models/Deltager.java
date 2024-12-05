package models;

public class Deltager {
    private String navn;
    private String adresse;
    private String mobil;
    private Firma firma;
    private boolean isForedragsholder;
    private boolean hasLedsager;
    private Ledsager ledsager;
    private Tilmelding tilmelding;

    public Deltager(String navn, String adresse, String mobil) {
        this.navn = navn;
        this.adresse = adresse;
        this.mobil = mobil;
        this.firma = firma;
    }

    @Override
    public String toString() {

        return "Navn: " + navn;
    }

    public Firma getFirma() {
        return firma;
    }

    public void setFirma(Firma firma) {
        if (this.firma != firma) {
            this.firma = firma;
        }
    }

    public String getNavn() {
        return navn;
    }

    public Ledsager getLedsager() {
        return ledsager;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getMobil() {
        return mobil;
    }

    public boolean isHasLedsager() {
        return hasLedsager;
    }

    public boolean isForedragsholder() {
        return isForedragsholder;
    }
}
