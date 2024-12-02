package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import models.Konference;
import models.Udflugt;
import storage.Storage;

import java.time.LocalDate;

public class KonferencePane extends GridPane {

    private TextField navnTextField;
    private TextField adresseTextField;
    private DatePicker startDatePicker;
    private DatePicker slutDatePicker;
    private TextField maxAntalDeltagereTextField;
    private TextField prisTextField;
    private TextField udflugtTextField;
    private ListView<Udflugt> udflugtListView;
    private ListView<Konference> konferenceListView;
    private Button addKonferenceButton;

    public KonferencePane() {
        this.setPadding(new Insets(20.0));
        this.setHgap(20.0);
        this.setVgap(10.0);
        this.setGridLinesVisible(false);

        this.navnTextField = new TextField();
        this.adresseTextField = new TextField();
        this.startDatePicker = new DatePicker();
        this.slutDatePicker = new DatePicker();
        this.maxAntalDeltagereTextField = new TextField();
        this.prisTextField = new TextField();
        this.udflugtTextField = new TextField();

        //Label for the title "Konference"
        this.add(new Label("Konference"), 2, 0);
        this.konferenceListView = new ListView<>();
        this.konferenceListView.setPrefWidth(100.0);
        this.konferenceListView.setPrefHeight(100.0);
        konferenceListView.getItems().setAll(Storage.getKonferencer());
        System.out.println("Opdaterer konferenceListView med " + Storage.getKonferencer().size() + " konferencer.");
        ChangeListener<Konference> listener = (ov, oldKonference, newKonference) -> {
            this.selectedKonferenceChanged(newKonference);
        };
        this.konferenceListView.getSelectionModel().selectedItemProperty().addListener(listener);
        this.add(konferenceListView, 2, 1, 5, 4);

        //Label and textField for "Navn"
        Label lblNavn = new Label("Navn");
        this.add(lblNavn, 0, 0);
        this.add(this.navnTextField, 1, 0);

        //Label and textField for "Adresse"
        Label lblAdresse = new Label("Adresse");
        this.add(lblAdresse, 0, 1);
        this.add(this.adresseTextField, 1, 1);

        //Label and textField for "Startdato"
        Label lblStart = new Label("Startdato");
        this.add(lblStart, 0, 2);
        this.add(this.startDatePicker, 1, 2);

        //Label and textField for "Slutdato"
        Label lblSlut = new Label("Slutdato");
        this.add(lblSlut, 0, 3);
        this.add(this.slutDatePicker, 1, 3);

        //Label and textField for "Max Antal Deltagere"
        Label lblAntal = new Label("Max Antal Deltagere");
        this.add(lblAntal, 0, 4);
        this.add(this.maxAntalDeltagereTextField, 1, 4);


        Label lblPris = new Label("Pris");
        this.add(lblPris, 0, 5);
        this.add(this.prisTextField, 1, 5);


        Label lblUdflugt = new Label("Udflugt");
        this.add(lblUdflugt, 0, 6);
        this.add(this.udflugtTextField, 1, 6);


        // Buttons for Create and Update
        addKonferenceButton = new Button("Create Konference");
        addKonferenceButton.setOnAction(event -> this.createKonferenceAction());
        this.add(addKonferenceButton, 0, 7);
    }

    private void selectedKonferenceChanged(Konference newKonference) {
        this.updateController(newKonference);
    }

    public void updateController(Konference konference) {
    }

    private void createKonferenceAction() {
        // Hent data fra tekstfelterne
        String navn = navnTextField.getText().trim();
        String adresse = adresseTextField.getText().trim();
        LocalDate ankomstDate = startDatePicker.getValue();
        LocalDate afrejseDate = slutDatePicker.getValue();
        String maxAntalDeltagere = maxAntalDeltagereTextField.getText().trim();
        String prisStr = prisTextField.getText().trim();

        // Valider input
        if (navn.isEmpty() || adresse.isEmpty() || ankomstDate == null || afrejseDate == null || maxAntalDeltagere.isEmpty() || prisStr.isEmpty()) {
            System.out.println("Alle felter skal udfyldes.");
            return;
        }

        try {
            // Konverter pris til int
            int pris = Integer.parseInt(prisStr);
            int antalDeltagere = Integer.parseInt(maxAntalDeltagere);
            // Opret konference
            Konference konference = Controller.opretKonference(navn, antalDeltagere, adresse, ankomstDate, afrejseDate, pris);

            // Opdater ListView for konferencer
            konferenceListView.getItems().setAll(Storage.getKonferencer());

            // Tøm tekstfelterne efter oprettelse
            navnTextField.clear();
            adresseTextField.clear();
            startDatePicker.setValue(null);
            slutDatePicker.setValue(null);
            maxAntalDeltagereTextField.clear();
            prisTextField.clear();
        } catch (NumberFormatException e) {
            System.out.println("Fejl i input: Prisen eller antal deltagere er ikke et gyldigt tal.");
        } catch (Exception e) {
            System.out.println("Fejl i input: " + e.getMessage());
        }
    }
}
