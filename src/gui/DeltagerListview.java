package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import models.Deltager;
import storage.Storage;

public class DeltagerListview {
    private ListView<Deltager> listView;

    public DeltagerListview() {
        listView = new ListView<>();
        listView.setPrefHeight(200);
        listView.setPrefWidth(200);

        // Initialiser deltagerlisten ved at hente deltagere fra Storage
        updateDeltagerList();
    }

    // Opdater deltagerlisten
    public void updateDeltagerList() {
        ObservableList<Deltager> deltagere = FXCollections.observableArrayList(Storage.getDeltagerer());
        listView.setItems(deltagere);
    }

    public Deltager getSelectedDeltager() {
        return listView.getSelectionModel().getSelectedItem();
    }

    public ListView<Deltager> getListView() {
        return listView;
    }
}
