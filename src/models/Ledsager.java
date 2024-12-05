package models;

public class Ledsager {
    private String navn;
    private Deltager deltager;

    public Ledsager(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void setDeltager(Deltager deltager) {
        this.deltager = deltager;
    }
}
