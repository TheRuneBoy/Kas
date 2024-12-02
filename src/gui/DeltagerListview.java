package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import models.Deltager;
import storage.Storage;

public class DeltagerListview {

    private ListView<Deltager> listView;
    private ObservableList<Deltager> deltagere;

    public DeltagerListview() {
        this.listView = new ListView<>();
        this.deltagere = FXCollections.observableArrayList();
        listView.setItems(deltagere);
    }

    public ListView<Deltager> getListView() {
        return listView;
    }

    // Opdater deltagerlisten
    public void updateDeltagerList() {
        // Hent de deltagere, der er tilmeldt den valgte konference
        ObservableList<Deltager> deltagerList = FXCollections.observableArrayList(Storage.getSelectedKonference().getDeltagere());
        listView.setItems(deltagerList);  // Opdater ListView
    }
}
