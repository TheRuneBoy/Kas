package models;

public class Firma {
    private String firmaNavn;
    private String firmaMobil;

    public Firma(String firmaNavnnavn, String firmaMobil){
        this.firmaNavn = firmaNavnnavn;
        this.firmaMobil = firmaMobil;
    }

    public String getFirmaNavn() {
        return firmaNavn;
    }

    public String getFirmaMobil() {
        return firmaMobil;
    }
}
