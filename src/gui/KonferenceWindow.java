package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Konference;

public class KonferenceWindow extends Stage {
    private Konference konference;

    public KonferenceWindow(String title, Konference konference) {
        this.initStyle(StageStyle.UTILITY);
        this.initModality(Modality.APPLICATION_MODAL);
        this.setResizable(false);
        this.konference = konference;
        this.setTitle(title);
        GridPane pane = new GridPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        this.setScene(scene);

    }

    private void initContent(GridPane pane) {
        pane.setPadding(new Insets(10.0));
        pane.setHgap(10.0);
        pane.setVgap(10.0);
        pane.setGridLinesVisible(false);

    }
}


