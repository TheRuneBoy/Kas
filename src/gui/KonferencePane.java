package gui;

import controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import models.Deltager;
import models.Konference;
import models.Tilmelding;
import storage.Storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class KonferencePane extends GridPane {

    private final ListView<String> deltagerListView;
    private TextField navnTextField;
    private TextField adresseTextField;
    private DatePicker startDatePicker;
    private DatePicker slutDatePicker;
    private TextField maxAntalDeltagereTextField;
    private TextField prisTextField;
    private TextField udflugtTextField;
    private ListView<Konference> konferenceListView;
    private ListView<String> konferenceInfo;
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
        this.add(new Label("Konference"), 2, 0, 2, 1);
        this.konferenceListView = new ListView<>();
        this.konferenceListView.setPrefWidth(200.0);
        this.konferenceListView.setPrefHeight(200.0);
        konferenceListView.getItems().setAll(Storage.getKonferencer());
        System.out.println("Opdaterer konferenceListView med " + Storage.getKonferencer().size() + " konferencer.");
        ChangeListener<Konference> listener = (ov, oldKonference, newKonference) -> {
            this.selectedKonferenceChanged(newKonference);
        };
        this.konferenceListView.getSelectionModel().selectedItemProperty().addListener(listener);
        this.add(konferenceListView, 2, 1, 2, 4);

        //Label for the title "Konference"
        this.add(new Label("Konference Info"), 5, 0);
        this.konferenceInfo = new ListView<>();
        this.konferenceInfo.setPrefWidth(200.0);
        this.konferenceInfo.setPrefHeight(200.0);
        konferenceListView.getItems().setAll(Storage.getKonferencer());
        this.konferenceListView.getSelectionModel().selectedItemProperty().addListener(listener);
        this.add(konferenceInfo, 5, 1, 2, 4);

        // Deltager ListView
        Label lblDeltagerListView = new Label("Tilmeldte Deltagere");
        this.add(lblDeltagerListView, 8, 0);
        this.deltagerListView = new ListView<>();
        this.add(this.deltagerListView, 8, 1, 2, 4);
        this.deltagerListView.setPrefWidth(250.0);
        this.deltagerListView.setPrefHeight(100.0);

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

    private void selectedKonferenceChanged(Konference newKonference) {
        if (newKonference != null) {
            // Opdater UI elementer med data fra den valgte konference
            navnTextField.setText(newKonference.getNavn());
            adresseTextField.setText(newKonference.getAdresse());
            startDatePicker.setValue(newKonference.getStartDato());
            slutDatePicker.setValue(newKonference.getSlutDato());
            maxAntalDeltagereTextField.setText(String.valueOf(newKonference.getMaxAntalDeltagere()));
            prisTextField.setText(String.valueOf(newKonference.getPris()));

            // Opret en liste af strenge, der repræsenterer konferencens attributter
            List<String> konferenceAttributter = new ArrayList<>();
            konferenceAttributter.add("Navn: " + newKonference.getNavn());
            konferenceAttributter.add("Adresse: " + newKonference.getAdresse());
            konferenceAttributter.add("Startdato: " + newKonference.getStartDato().toString());
            konferenceAttributter.add("Slutdato: " + newKonference.getSlutDato().toString());
            konferenceAttributter.add("Max antal deltagere: " + newKonference.getMaxAntalDeltagere());
            konferenceAttributter.add("Pris: " + newKonference.getPris());

            // Opdater konferenceInfo ListView med de relevante attributter
            konferenceInfo.getItems().setAll(konferenceAttributter);

            // Hent tilmeldinger for den valgte konference
            List<Tilmelding> tilmeldingerForKonference = new ArrayList<>();
            for (Tilmelding tilmelding : Storage.getTilmeldinger()) {
                if (tilmelding.getKonference().equals(newKonference)) {
                    tilmeldingerForKonference.add(tilmelding);
                }
            }

            // Opret en liste til at vise deltagere og ledsagere
            List<String> deltagereOgLedsagere = new ArrayList<>();
            for (Tilmelding tilmelding : tilmeldingerForKonference) {
                Deltager deltager = tilmelding.getDeltager();
                // Add the participant's name
                String deltagerInfo = deltager.getNavn();

                // If there is a companion, add the companion's name
                if (tilmelding.getLedsager() != null) {
                    deltagerInfo += " (Ledsager: " + tilmelding.getLedsager().getNavn() + ")";
                }

                // Add the participant and companion info to the list
                deltagereOgLedsagere.add(deltagerInfo);
            }

            // Update the ListView with participants and companions
            deltagerListView.getItems().setAll(deltagereOgLedsagere);
        }
    }
}

