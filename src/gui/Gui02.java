package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Deltager;
import models.Konference;
import storage.Storage;

import java.time.LocalDate;

public class Gui02 extends Application {

    private KonferenceListview konferenceListView;
    private DeltagerListview deltagerListview;
    private TilmeldingInfoLisview tilmeldingInfoLisview;

    @Override
    public void start(Stage firstStage) throws Exception {
        firstStage.setTitle("KAS");
        GridPane pane = new GridPane();
        this.initContent(pane);  // Kald initContent metoden

        Scene scene = new Scene(pane);
        firstStage.setScene(scene);
        firstStage.show();
    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        // Label for Conference List
        Label konferenceListe = new Label("Konference");
        pane.add(konferenceListe, 0, 0);
        konferenceListView = new KonferenceListview();
        pane.add(konferenceListView.getListView(), 0, 1);

        // Pre-create some conferences and store them
        Konference konference1 = new Konference("Konference 1", 100, "Adresse 1", LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 3), 500);
        Storage.storeKonference(konference1);
        konferenceListView.updateListView();

        // DeltagerListview
        Label deltagerListe = new Label("Deltager");
        pane.add(deltagerListe, 1, 0);
        deltagerListview = new DeltagerListview();
        pane.add(deltagerListview.getListView(), 1, 1);

        // Tilmelding Info Listview
        tilmeldingInfoLisview = new TilmeldingInfoLisview();
        pane.add(tilmeldingInfoLisview.getListView(), 2, 1);

        // Hent og opdater deltagerinfo når en deltager vælges
        deltagerListview.getListView().getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                ObservableList<String> deltagerInfo = getDeltagerInfo(newValue);  // Hent deltagerens info
                tilmeldingInfoLisview.setDeltagerInfo(deltagerInfo);  // Opdater info-listen
            }
        });

        // Formular for deltager
        Label fuldeNavn = new Label("Fulde Navn");
        pane.add(fuldeNavn, 1, 2);
        TextField deltagerNavn = new TextField();
        pane.add(deltagerNavn, 1, 3);

        CheckBox foredragsholderCheckBox = new CheckBox("Foredragsholder");
        pane.add(foredragsholderCheckBox, 1, 4);

        CheckBox ledsagerCheckBox = new CheckBox("Ledsager");
        pane.add(ledsagerCheckBox, 1, 5);

        DatePicker ankomstDatePicker = new DatePicker();
        pane.add(ankomstDatePicker, 1, 6);

        DatePicker afrejseDatePicker = new DatePicker();
        pane.add(afrejseDatePicker, 1, 7);

        // Opret Deltager Button
        Button opretDeltagerButton = new Button("Opret Deltager");
        pane.add(opretDeltagerButton, 1, 8);

        opretDeltagerButton.setOnAction(e -> {
            // Hent valgt konference
            Konference selectedKonference = konferenceListView.getSelectedKonference();
            if (selectedKonference != null) {
                // Brug TilmeldingOgDeltagerOpret-klassens metode til at oprette deltager og tilmelding
                TilmeldingOgDeltagerOpret.opretTilmeldingOgDeltager(selectedKonference, deltagerNavn.getText(),
                        foredragsholderCheckBox, ledsagerCheckBox,
                        ankomstDatePicker, afrejseDatePicker,
                        deltagerListview);
            } else {
                System.out.println("Vælg venligst en konference.");
            }
        });
    }

    // Metode til at hente deltagerens info som en ObservableList
    private ObservableList<String> getDeltagerInfo(Deltager deltager) {
        ObservableList<String> info = FXCollections.observableArrayList();
        info.add("Navn: " + deltager.getNavn());
        info.add("Foredragsholder: " + (deltager.isForedragsholder() ? "Ja" : "Nej"));
        info.add("Ledsager: " + (deltager.getLedsager() != null ? deltager.getLedsager().getNavn() : "Ingen"));
        return info;
    }
}
