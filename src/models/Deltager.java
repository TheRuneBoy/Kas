package models;

public class Deltager {
    private String navn;
    private String adresse;
    private String mobil;
    Firma firma;

    public Deltager(String navn, String adresse, String mobil, Firma firma) {
        this.navn = navn;
        this.adresse = adresse;
        this.mobil = mobil;
        this.firma = firma;
    }
    public Firma getFirma(){
        return firma;
    }
    public void setFirma(Firma firma){
        if (this.firma != firma){
            this.firma=firma;
        }
    }


}
