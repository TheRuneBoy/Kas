package gui;

import controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import models.*;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;


public class TilmeldingPane extends GridPane {

    private final TextField adresseTextField;
    private final TextField mobilTextField;
    private final TextField firmaNavnTextField;
    private final TextField firmaMobilTextField;
    private DatePicker ankomstDato;
    private DatePicker afrejseDato;
    private final ListView<HotelTilæg> hotelTilægListView;
    private ListView<Konference> konferenceListView;
    private ListView<Deltager> deltagerListView;
    private TextField deltagerTextField;
    private TextField ledsagerTextField;
    private ListView<String> detailsListView;
    private ListView<Hotel> hotelListView;
    private ListView<Udflugt> udflugtListView;
    private TextField totalPris;

    public TilmeldingPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        //Pris beregner
        this.add(new Label("Total Pris"), 1, 12);
        this.totalPris = new TextField();
        this.add(totalPris, 2, 12);


        // Konference ListView
        this.add(new Label("Konference"), 0, 0);
        this.konferenceListView = new ListView<>();
        this.add(this.konferenceListView, 0, 1, 1, 3);
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

        Label lblAnkomst = new Label("Ankomst Dato");
        this.add(lblAnkomst, 1, 4);
        this.ankomstDato = new DatePicker();
        this.add(ankomstDato, 2, 4);

        Label lblAfrejse = new Label("Afrejse Dato");
        this.add(lblAfrejse, 1, 5);
        this.afrejseDato = new DatePicker();
        this.add(afrejseDato, 2, 5);

        // Foredragsholder checkboks
        Label lblForedragsholder = new Label("Foredragsholder");
        this.add(lblForedragsholder, 1, 6);
        CheckBox erForedragsholder = new CheckBox();
        this.add(erForedragsholder, 2, 6);

        // Firma checkboks
        Label lblFirma = new Label("Er Deltager for et Firma?");
        this.add(lblFirma, 1, 7);
        CheckBox erFirma = new CheckBox();
        this.add(erFirma, 2, 7);

        // Firma specifik tekstfelter (kun hvis Firma checkboks er valgt)
        Label lblFirmaNavn = new Label("Firma Navn");
        this.add(lblFirmaNavn, 1, 8);
        this.firmaNavnTextField = new TextField();
        this.add(this.firmaNavnTextField, 2, 8);
        firmaNavnTextField.setDisable(!erFirma.isSelected());

        Label lblFirmaMobil = new Label("Firma Mobil");
        this.add(lblFirmaMobil, 1, 9);
        this.firmaMobilTextField = new TextField();
        this.add(this.firmaMobilTextField, 2, 9);
        firmaMobilTextField.setDisable(!erFirma.isSelected());

        // Ledsager checkboks og tekstfelt
        Label lblHarLedsager = new Label("Har Ledsager");
        this.add(lblHarLedsager, 1, 10);
        CheckBox harLedsager = new CheckBox();
        this.add(harLedsager, 2, 10);

        Label lblLedsagerNavn = new Label("Ledsager Navn");
        this.add(lblLedsagerNavn, 1, 11);
        this.ledsagerTextField = new TextField();
        this.add(ledsagerTextField, 2, 11);
        ledsagerTextField.setDisable(!harLedsager.isSelected());

        // Deltager ListView
        Label lblDeltagerListView = new Label("Tilmeldte Deltagere");
        this.add(lblDeltagerListView, 3, 0);
        this.deltagerListView = new ListView<>();
        this.add(this.deltagerListView, 3, 1, 1, 5);
        this.deltagerListView.setPrefWidth(250.0);
        this.deltagerListView.setPrefHeight(100.0);

        // Deltager Info ListView
        Label lblDetailListView = new Label("Deltager Info");
        this.add(lblDetailListView, 4, 0);
        this.detailsListView = new ListView<>();
        this.add(this.detailsListView, 4, 1, 1, 5);
        this.detailsListView.setPrefWidth(250.0);
        this.detailsListView.setPrefHeight(100.0);

        // Opret Tilmelding Button
        Button opretTilmeldingButton = new Button("Opret Tilmelding");
        this.add(opretTilmeldingButton, 1, 13);
        opretTilmeldingButton.setOnAction(e -> createTilmelding(erForedragsholder.isSelected(), harLedsager.isSelected(), erFirma.isSelected()));

        //Hotel Listview
        Label lblHotelListView = new Label("Hoteller");
        this.add(lblHotelListView, 3, 6);
        this.hotelListView = new ListView<>();
        this.add(this.hotelListView, 3, 7);
        this.hotelListView.setPrefWidth(100);
        this.hotelListView.setPrefHeight(100);
        this.hotelListView.getItems().setAll(Storage.getHoteller());

        //HotelTilæg Listview
        Label lblHotelTilægListView = new Label("Hotel Tillæg");
        this.add(lblHotelTilægListView, 3, 8);
        this.hotelTilægListView = new ListView<>();
        this.add(this.hotelTilægListView, 3, 9);
        this.hotelTilægListView.setPrefWidth(100);
        this.hotelTilægListView.setPrefHeight(100);
        this.hotelTilægListView.getItems().setAll(Storage.getHotelTilægs());

        //Udlfugt Listview
        Label lblUdflugtListView = new Label("Udflugter");
        this.add(lblUdflugtListView, 4, 6);
        this.udflugtListView = new ListView<>();
        this.add(this.udflugtListView, 4, 7);
        this.udflugtListView.setPrefWidth(100);
        this.udflugtListView.setPrefHeight(100);
        this.udflugtListView.getItems().setAll(Storage.getUdflugter());


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
            deltager.setFirma(firma);
        }

        // Opret Ledsager, hvis valgt
        Ledsager ledsager = null;
        if (harLedsager) {
            String ledsagerNavn = this.ledsagerTextField.getText();
            ledsager = Controller.opretLedsager(ledsagerNavn);
        }

        // Hent de valgte ankomst og afrejse datoer
        LocalDate ankomstDato = this.ankomstDato.getValue();
        LocalDate afrejseDato = this.afrejseDato.getValue();

        if (ankomstDato == null || afrejseDato == null) {
            showAlert("Fejl", "Vælg venligst både ankomst- og afrejsedato.");
            return;
        }

        // Hent de valgte udflugter
        //ArrayList<Udflugt> valgteUdflugter = new ArrayList<>(this.udflugtListView.getSelectionModel().getSelectedItems());

        // Hent det valgte hotel
        Hotel valgtHotel = this.hotelListView.getSelectionModel().getSelectedItem();
//        if (valgtHotel == null) {
//            showAlert("Fejl", "Vælg venligst et hotel.");
//            return;
//        }

        // Hent de valgte hoteltilæg
        //ObservableList<HotelTilæg> valgteHotelTilægs = this.hotelTilægListView.getSelectionModel().getSelectedItems();

        // Opret Tilmelding via Controller
        Tilmelding tilmelding = Controller.opretTilmelding(
                valgtHotel,
            //    (ArrayList<HotelTilæg>) valgteHotelTilægs,
                erForedragsholder,
                ankomstDato,
                afrejseDato,
                deltager,
                selectedKonference,
                ledsager,
                firma
        );

        // Opdater deltagerlisten
        updateDeltagereListView(selectedKonference);

        showAlert("Succes", "Tilmeldingen blev oprettet.");
    }


    private void updateDeltagereListView(Konference konference) {
        if (konference != null) {
            // Directly assign a new ObservableList if necessary
            ObservableList<Deltager> deltagereList = FXCollections.observableArrayList();

            // Hent deltagere via tilmeldinger, og tilføj deltagerne selv (ikke kun navnet)
            for (Tilmelding tilmelding : Storage.getTilmeldinger()) {
                if (tilmelding.getKonference().equals(konference)) {
                    deltagereList.add(tilmelding.getDeltager()); // Add the Deltager object directly
                }
            }

            // Sorter deltager listen efter navn
            SortedList<Deltager> sortedDeltagere = new SortedList<>(deltagereList, Comparator.comparing(Deltager::getNavn));

            // Sæt den sorterede liste i list view
            deltagerListView.setItems(sortedDeltagere);

            // Opdater hotelListView med hotellerne for den valgte konference
            ObservableList<Hotel> hotellerList = FXCollections.observableArrayList(konference.getHoteller());
            hotelListView.setItems(hotellerList);

            updateUdflugterListView(konference);

            // Opdater visningen af hoteltilæg når et hotel vælges
            hotelListView.getSelectionModel().selectedItemProperty().addListener((obs, oldHotel, newHotel) -> {
                if (newHotel != null) {
                    // Hent hoteltilæggene for det valgte hotel
                    ObservableList<HotelTilæg> hotelTilægs = FXCollections.observableArrayList(newHotel.getHotelTilægs());

                    // Opdater hoteltilæg ListView
                    hotelTilægListView.setItems(hotelTilægs);


                }
            });
        }
    }

    private void chooseHotelAndExtras() {
        // 1. Vælg et hotel
        Hotel valgtHotel = showHotelSelectionDialog();
        if (valgtHotel == null) {
            showAlert("Fejl", "Vælg venligst et hotel.");
            return;
        }

        // 2. Vælg hoteltilæg
        ArrayList<HotelTilæg> valgteHotelTilægs = showHotelTilægSelectionDialog(valgtHotel);
        if (valgteHotelTilægs == null || valgteHotelTilægs.isEmpty()) {
            showAlert("Fejl", "Vælg venligst mindst et hoteltilæg.");
            return;
        }

        // 3. Vælg udflugter
        ArrayList<Udflugt> valgteUdflugter = showUdflugtSelectionDialog();
        if (valgteUdflugter == null || valgteUdflugter.isEmpty()) {
            showAlert("Fejl", "Vælg venligst mindst én udflugt.");
            return;
        }

        // 4. Opdater Tilmeldingen
        updateTilmelding(valgtHotel, valgteHotelTilægs, valgteUdflugter);
    }

    private Hotel showHotelSelectionDialog() {
        // Returner det valgte hotel
        return hotelListView.getSelectionModel().getSelectedItem();  // Eksempel på valg fra en ListView
    }

    private ArrayList<HotelTilæg> showHotelTilægSelectionDialog(Hotel valgtHotel) {
        // Returner de valgte hoteltilæg
        return new ArrayList<>(hotelTilægListView.getSelectionModel().getSelectedItems());  // Eksempel
    }

    private ArrayList<Udflugt> showUdflugtSelectionDialog() {
        // Returner de valgte udflugter
        return new ArrayList<>(udflugtListView.getSelectionModel().getSelectedItems());  // Eksempel
    }

    private void updateTilmelding(Hotel valgtHotel, ArrayList<HotelTilæg> valgteHotelTilægs, ArrayList<Udflugt> valgteUdflugter) {
    }


    private void updateDeltagerInfo(Deltager deltager) {
        detailsListView.getItems().clear();
        detailsListView.getItems().add("Navn: " + deltager.getNavn());
        detailsListView.getItems().add("Adresse: " + deltager.getAdresse());
        detailsListView.getItems().add("Mobil: " + deltager.getMobil());

        // Tjek om deltageren er foredragsholder
        Tilmelding tilmelding = findTilmeldingForDeltager(deltager);
        if (tilmelding != null && tilmelding.getDeltager().equals(deltager)) {
            detailsListView.getItems().add("Foredragsholder: " + (tilmelding.isForedragsHolder() ? "Ja" : "Nej"));
        }

        // Tilføj ankomst og afrejse dato
        detailsListView.getItems().add("Ankomst Dato: " + tilmelding.getAnkomstDato());
        detailsListView.getItems().add("Afrejse Dato: " + tilmelding.getAfrejseDato());

        // Hvis deltageren er tilknyttet et firma, vis firmaoplysninger
        if (tilmelding.getDeltager().getFirma() != null) {
            detailsListView.getItems().add("Firma Navn: " + tilmelding.getDeltager().getFirma().getFirmaNavn());
            detailsListView.getItems().add("Firma Mobil: " + tilmelding.getDeltager().getFirma().getFirmaMobil());
        }

        // Hvis deltageren har en ledsager, vis ledsagerens navn
        if (tilmelding.getLedsager() != null) {
            detailsListView.getItems().add("Ledsager Navn: " + tilmelding.getLedsager().getNavn());
        }

        // Tilføj hotel til info
        if (tilmelding.getValgtHotel() != null) {
            detailsListView.getItems().add("Hotel: " + tilmelding.getValgtHotel().getNavn());
        } else {
            detailsListView.getItems().add("Hotel: Intet Hotel valgt");
        }
    }


    private void updateUdflugterListView(Konference konference) {
        if (konference != null) {
            ObservableList<Udflugt> udflugterList = FXCollections.observableArrayList(konference.getUdflugter());
            udflugtListView.setItems(udflugterList);
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
