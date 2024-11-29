package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import models.Konference;
import storage.Storage;

public class KonferenceListview {
    private ListView<Konference> listView;

    public KonferenceListview() {
        listView = new ListView<>();
        listView.setPrefHeight(200);
        listView.setPrefWidth(200);

        // Hent konferencer fra Storage og opdater ListView
        updateListView();
    }

    // Opdater ListView med konferencer fra Storage
    public void updateListView() {
        ObservableList<Konference> konferencer = FXCollections.observableArrayList(Storage.getKonferencer());
        listView.setItems(konferencer);
    }

    public ListView<Konference> getListView() {
        return listView;
    }

    public Konference getSelectedKonference() {
        return listView.getSelectionModel().getSelectedItem();  // Returner den valgte konference
    }
}
