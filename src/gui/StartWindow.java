package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class StartWindow extends Application {
    public StartWindow() {
    }

    public void start(Stage stage) {
        stage.setTitle("KAS");
        BorderPane pane = new BorderPane();
        this.initContent(pane);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        this.initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        Tab tabKonference = new Tab("Konference");
        tabKonference.setClosable(false); //prevent closing the tab
        tabPane.getTabs().add(tabKonference);
        KonferencePane konferencePane = new KonferencePane();
        tabKonference.setContent(konferencePane);

        Tab tabTilmelding = new Tab("Tilmelding");
        tabTilmelding.setClosable(false);
        tabPane.getTabs().add(tabTilmelding);
        TilmeldingPane tilmeldingPane = new TilmeldingPane();
        tabTilmelding.setContent(tilmeldingPane);
    }

}

