package gui;

import gui.TilmeldingPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Deltager;
import models.Tilmelding;
import storage.Storage;

import java.util.ArrayList;

import static storage.Storage.getTilmeldinger;


public class TilmeldingWindow extends Stage {

    //private static final ArrayList<Tilmelding>tilmeldingList = new ArrayList<>();
    private TilmeldingPane tilmeldingPane;

    public TilmeldingWindow() {

    }

    private void initStorage() {
        //Deltager deltager1 = new Deltager("Alan Way");

        //Tilmelding tilmelding1 = new Tilmelding(deltager1);

        //Storage.addTilmelding(tilmelding1);
    }

    //public static ArrayList<Tilmelding> getTilmeldingList() {
    //return tilmeldingList;
}
