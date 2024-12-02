package gui;


import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import models.Tilmelding;
import storage.Storage;

import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class TilmeldingPane extends GridPane {

    private ListView<Tilmelding> tilmeldingListView;
    private TextField deltagerTextField;
    private TextField konferenceTextField;
    private TextField ledsagerTextField;
    private TextField hotelTextField;
    private ListView<String> detailsListView;

    public TilmeldingPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);
        //this.add(new Label("Tilmelding"), 0, 0);
        this.tilmeldingListView = new ListView<>();
//        this.add(this.tilmeldingListView, 0, 1, 1, 5);
        this.tilmeldingListView.setPrefWidth(200.0);
        this.tilmeldingListView.setPrefHeight(200.0);
        this.tilmeldingListView.getItems().setAll(Storage.getTilmeldinger());
        this.tilmeldingListView.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> selectedTilmeldingChanged(newValue)));



        Label lblDeltager = new Label("Deltager");
        this.add(lblDeltager, 0, 0);
        this.deltagerTextField = new TextField();
        this.add(this.deltagerTextField, 1, 0);
        this.deltagerTextField.setEditable(false);

        Label lblKonference = new Label("Konference");
        this.add(lblKonference, 0, 1);
        this.konferenceTextField = new TextField();
        this.add(this.konferenceTextField, 1, 1);
        this.konferenceTextField.setEditable(false);

        Label lblLedsager = new Label("Ledsager");
        this.add(lblLedsager, 0, 2);
        this.ledsagerTextField = new TextField("Alam Atan");
        this.add(this.ledsagerTextField, 1, 2);
        this.ledsagerTextField.setEditable(false);

        Label lblHotel = new Label("Hotel");
        this.add(lblHotel, 0, 3);
        this.hotelTextField = new TextField();
        this.add(this.hotelTextField, 1, 3);
        this.hotelTextField.setEditable(false);

        // Add ListView for Details
        this.detailsListView = new ListView<>();
        this.add(this.detailsListView, 2, 0, 1, 5); // Span multiple rows
        this.detailsListView.setPrefWidth(250.0);
        this.detailsListView.setPrefHeight(200.0);

        //Button to show lists
//        Button showDeltagerButton = new Button("Show Deltager");
//        this.add(showDeltagerButton, 0, 4);
//        showDeltagerButton.setOnAction(event -> showDeltager());

        Button openHotelButton = new Button("Hotel Info");
        this.add(openHotelButton, 0, 5);
        openHotelButton.setOnAction(event -> openHotelInfo());

    }

    private void showDeltager() {
        detailsListView.getItems().clear();
        for (Tilmelding tilmelding : Storage.getTilmeldinger()) {
            detailsListView.getItems().add(tilmelding.getDeltager().getNavn());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void selectedTilmeldingChanged(Tilmelding newTilmelding) {
        if (newTilmelding != null) {
            //Update text fields with details
            this.deltagerTextField.setText(newTilmelding.getDeltager().getNavn());
            this.konferenceTextField.setText(newTilmelding.getKonference().getNavn());
            if (newTilmelding.getLedsager() != null) {
                this.ledsagerTextField.setText(newTilmelding.getLedsager().getNavn());
            } else {
                this.ledsagerTextField.clear();
            }
            if (newTilmelding.getValgtHotel() != null) {
                //this.hotelTextField.setText(newTilmelding.getValghotel().);
            } else {
                this.hotelTextField.clear();
            }
            //Update Listview with details
            this.detailsListView.getItems().clear();
            this.detailsListView.getItems().add(("Deltager :" + newTilmelding.getDeltager().getNavn()));
            if(newTilmelding.getLedsager() != null) {
                this.detailsListView.getItems().add("Ledsager: " + newTilmelding.getLedsager().getNavn());
            } else {
                this.detailsListView.getItems().add("No Ledsager.");
            }
            if(newTilmelding.getValgtHotel() !=null) {
                //this.detailsListView.getItems().add("Hotel: " + newTilmelding.getValghotel().getNavn);
            } else {
                this.detailsListView.getItems().add("No Hotel selcted. ");
            }
        } else {
            //Clear all fields if no Tilmelding selected
            this.deltagerTextField.clear();
            this.konferenceTextField.clear();
            this.ledsagerTextField.clear();
            this.hotelTextField.clear();
            this.detailsListView.getItems().clear();
        }
    }
    public void updateController(Tilmelding tilmelding) {
    }

    private void openHotelInfo() {
        Stage hotelStage = new Stage();
        hotelStage.setTitle("Hotel Information");
        HotelInfo hotelInfo = new HotelInfo();
        Scene scene = new Scene(hotelInfo,400,300);
        hotelStage.setScene(scene);
        hotelStage.show();
    }

}


