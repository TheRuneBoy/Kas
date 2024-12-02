package gui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class HotelInfo extends GridPane {

    public HotelInfo() {
        this.setPadding(new Insets(20));
        this.setHgap(10);
        this.setVgap(10);

        // Add components for the Hotel Pane
        Label label = new Label("Hotel Info");
        this.add(label, 0, 0);

        Button closeButton = new Button("Close");
        this.add(closeButton, 0, 1);

        closeButton.setOnAction(event -> {
            // Close the current window
            this.getScene().getWindow().hide();
        });
    }
}