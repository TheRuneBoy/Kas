package models;

import java.time.LocalDate;
import java.util.ArrayList;

public class Udflugt {
    private String navn;
    private LocalDate dato;
    private int pris;
    private ArrayList<Ledsager> ledsagers = new ArrayList<>();

    public Udflugt(String navn, LocalDate dato, int pris) {
        this.navn = navn;
        this.dato = dato;
        this.pris = pris;
    }

    public int getPris() {
        return pris;
    }
}
