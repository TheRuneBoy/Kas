package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.*;
import storage.Storage;

import java.time.LocalDate;

public class TilmeldingPane extends GridPane {

    private final TextField adresseTextField;
    private final TextField mobilTextField;
    private final TextField firmaNavnTextField;
    private final TextField firmaMobilTextField;
    private ListView<Konference> konferenceListView;
    private ListView<Deltager> deltagerListView;
    private TextField deltagerTextField;
    private TextField konferenceTextField;
    private TextField ledsagerTextField;
    private ListView<String> detailsListView;

    public TilmeldingPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        // Konference ListView
        this.add(new Label("Konference"), 0, 0);
        this.konferenceListView = new ListView<>();
        this.add(this.konferenceListView, 0, 1, 1, 5);
        this.konferenceListView.setPrefWidth(200.0);
        this.konferenceListView.setPrefHeight(200.0);
        this.konferenceListView.getItems().setAll(Storage.getKonferencer());
        Storage.addKonferenceChangeListener(() -> {
            this.konferenceListView.getItems().setAll(Storage.getKonferencer());
        });

        // Deltager detaljer
        Label lblDeltager = new Label("Deltager Navn");
        this.add(lblDeltager, 1, 1);
        this.deltagerTextField = new TextField();
        this.add(this.deltagerTextField, 2, 1);

        Label lblAdresse = new Label("Adresse");
        this.add(lblAdresse, 1, 2);
        this.adresseTextField = new TextField();
        this.add(adresseTextField, 2, 2);

        Label lblMobil = new Label("Mobil");
        this.add(lblMobil, 1, 3);
        this.mobilTextField = new TextField();
        this.add(mobilTextField, 2, 3);

        // Foredragsholder checkboks
        Label lblForedragsholder = new Label("Foredragsholder");
        this.add(lblForedragsholder, 1, 4);
        CheckBox erForedragsholder = new CheckBox();
        this.add(erForedragsholder, 2, 4);

        // Firma checkboks
        Label lblFirma = new Label("Er Deltager for et Firma?");
        this.add(lblFirma, 1, 5);
        CheckBox erFirma = new CheckBox();
        this.add(erFirma, 2, 5);

        // Firma specifik tekstfelter (kun hvis Firma checkboks er valgt)
        Label lblFirmaNavn = new Label("Firma Navn");
        this.add(lblFirmaNavn, 1, 6);
        this.firmaNavnTextField = new TextField();
        this.add(this.firmaNavnTextField, 2, 6);
        firmaNavnTextField.setDisable(!erFirma.isSelected());

        Label lblFirmaMobil = new Label("Firma Mobil");
        this.add(lblFirmaMobil, 1, 7);
        this.firmaMobilTextField = new TextField();
        this.add(this.firmaMobilTextField, 2, 7);
        firmaMobilTextField.setDisable(!erFirma.isSelected());

        // Ledsager checkboks og tekstfelt
        Label lblHarLedsager = new Label("Har Ledsager");
        this.add(lblHarLedsager, 1, 8);
        CheckBox harLedsager = new CheckBox();
        this.add(harLedsager, 2, 8);

        Label lblLedsagerNavn = new Label("Ledsager Navn");
        this.add(lblLedsagerNavn, 1, 9);
        this.ledsagerTextField = new TextField();
        this.add(ledsagerTextField, 2, 9);
        ledsagerTextField.setDisable(!harLedsager.isSelected());

        // Deltager ListView
        Label lblDeltagerListView = new Label("Tilmeldte Deltagere");
        this.add(lblDeltagerListView, 3, 0);
        this.deltagerListView = new ListView<>();
        this.add(this.deltagerListView, 3, 1, 1, 5);
        this.deltagerListView.setPrefWidth(250.0);
        this.deltagerListView.setPrefHeight(200.0);

        // Deltager Info ListView
        Label lblDetailListView = new Label("Deltager Info");
        this.add(lblDetailListView, 4, 0);
        this.detailsListView = new ListView<>();
        this.add(this.detailsListView, 4, 1, 1, 5);
        this.detailsListView.setPrefWidth(250.0);
        this.detailsListView.setPrefHeight(200.0);

        // Opret Tilmelding Button
        Button opretTilmeldingButton = new Button("Opret Tilmelding");
        this.add(opretTilmeldingButton, 1, 10);
        opretTilmeldingButton.setOnAction(e -> createTilmelding(erForedragsholder.isSelected(), harLedsager.isSelected(), erFirma.isSelected()));

        // Aktiver/deaktiver Firma felter baseret på checkboks
        erFirma.selectedProperty().addListener((obs, oldValue, newValue) -> {
            firmaNavnTextField.setDisable(!newValue);
            firmaMobilTextField.setDisable(!newValue);
        });

        // Aktiver/deaktiver Ledsager felt
        harLedsager.selectedProperty().addListener((obs, oldValue, newValue) -> {
            ledsagerTextField.setDisable(!newValue);
        });

        // Opdater deltagerne når en konference vælges
        konferenceListView.getSelectionModel().selectedItemProperty().addListener((obs, oldKonference, newKonference) -> {
            if (newKonference != null) {
                updateDeltagereListView(newKonference);
            }
        });

        // Vis deltagerens information når en deltager vælges
        deltagerListView.getSelectionModel().selectedItemProperty().addListener((obs, oldDeltager, newDeltager) -> {
            if (newDeltager != null) {
                updateDeltagerInfo(newDeltager);
            }
        });
    }

    private void createTilmelding(boolean erForedragsholder, boolean harLedsager, boolean erFirma) {
        Konference selectedKonference = this.konferenceListView.getSelectionModel().getSelectedItem();
        if (selectedKonference == null) {
            showAlert("Fejl", "Vælg venligst en konference.");
            return;
        }

        String deltagerNavn = this.deltagerTextField.getText();
        String adresse = this.adresseTextField.getText();
        String mobil = this.mobilTextField.getText();

        if (deltagerNavn.isEmpty() || adresse.isEmpty() || mobil.isEmpty()) {
            showAlert("Fejl", "Udfyld venligst alle deltageroplysninger.");
            return;
        }

        // Opret Deltager
        Deltager deltager = Controller.opretDeltager(deltagerNavn, adresse, mobil, null);

        // Opret Firma, hvis valgt
        Firma firma = null;
        if (erFirma) {
            String firmaNavn = this.firmaNavnTextField.getText();
            String firmaMobil = this.firmaMobilTextField.getText();
            if (firmaNavn.isEmpty() || firmaMobil.isEmpty()) {
                showAlert("Fejl", "Udfyld venligst firmaoplysninger.");
                return;
            }
            firma = Controller.opretFirma(firmaNavn, firmaMobil);
        }

        // Opret Ledsager, hvis valgt
        Ledsager ledsager = null;
        if (harLedsager) {
            String ledsagerNavn = this.ledsagerTextField.getText();
            ledsager = Controller.opretLedsager(ledsagerNavn);
        }

        // Opret Tilmelding via Controller
        Hotel valgtHotel = null;  // Erstat med det valgte hotel
        LocalDate ankomstDato = LocalDate.now(); // Erstat med valgte ankomstdato
        LocalDate afrejseDato = LocalDate.now().plusDays(1); // Erstat med valgte afrejse dato
        Tilmelding tilmelding = Controller.opretTilmelding(valgtHotel, erForedragsholder, ankomstDato, afrejseDato, deltager, selectedKonference, ledsager, firma);

        // Opdater deltagerlisten
        updateDeltagereListView(selectedKonference);

        showAlert("Succes", "Tilmeldingen blev oprettet.");
    }


    private void updateDeltagereListView(Konference konference) {
        if (konference != null) {
            deltagerListView.getItems().clear();
            // Hent deltagere via tilmeldinger, og tilføj deltagerne selv (ikke kun navnet)
            for (Tilmelding tilmelding : Storage.getTilmeldinger()) {
                if (tilmelding.getKonference().equals(konference)) {
                    deltagerListView.getItems().add(tilmelding.getDeltager()); // Add the Deltager object directly
                }
            }
        }
    }


    private void updateDeltagerInfo(Deltager deltager) {
        detailsListView.getItems().clear();
        detailsListView.getItems().add("Navn: " + deltager.getNavn());
        detailsListView.getItems().add("Adresse: " + deltager.getAdresse());
        detailsListView.getItems().add("Mobil: " + deltager.getMobil());

        // Tjek om deltageren er foredragsholder og vis det
        Tilmelding tilmelding = findTilmeldingForDeltager(deltager);
        if (tilmelding != null && tilmelding.getDeltager().equals(deltager)) {
            detailsListView.getItems().add("Foredragsholder: " + (tilmelding.isForedragsHolder() ? "Ja" : "Nej"));
        }

        // Hvis deltageren er tilknyttet et firma, vis firmaoplysninger
        if (deltager.getFirma() != null) {
            Firma firma = deltager.getFirma();
            detailsListView.getItems().add("Firma Navn: " + firma.getFirmaNavn());
            detailsListView.getItems().add("Firma Mobil: " + firma.getFirmaMobil());
        }

        // Hvis deltageren har en ledsager, vis ledsagerens navn
        if (deltager.getLedsager() != null) {
            detailsListView.getItems().add("Ledsager Navn: " + deltager.getLedsager().getNavn());
        }
    }



    // Hjælpe metode til at finde en tilmelding for en deltager
    private Tilmelding findTilmeldingForDeltager(Deltager deltager) {
        for (Tilmelding tilmelding : Storage.getTilmeldinger()) {
            if (tilmelding.getDeltager().equals(deltager)) {
                return tilmelding;
            }
        }
        return null;
    }


    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
