package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.ListCell;
import javafx.util.Callback;
import models.Deltager;

public class TilmeldingInfoLisview {
    private ListView<String> listView;

    public TilmeldingInfoLisview() {
        listView = new ListView<>();
        listView.setPrefHeight(200);
        listView.setPrefWidth(200);

        // Sæt cell factory for at vise deltagerens info som tekst
        listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
            @Override
            public ListCell<String> call(ListView<String> param) {
                return new ListCell<String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item);  // Vi viser deltagerinfo som tekst
                        }
                    }
                };
            }
        });
    }

    // Metode til at sætte deltagerinfo
    public void setDeltagerInfo(ObservableList<String> deltagerInfo) {
        listView.setItems(deltagerInfo);
    }

    public ListView<String> getListView() {
        return listView;
    }
}
