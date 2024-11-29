package gui;

import controller.Controller;
import models.*;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import java.time.LocalDate;

public class TilmeldingOgDeltagerOpret {

    public static void opretTilmeldingOgDeltager(Konference selectedKonference, String deltagerNavn,
                                                 CheckBox foredragsholderCheckBox, CheckBox ledsagerCheckBox,
                                                 DatePicker ankomstDatePicker, DatePicker afrejseDatePicker,
                                                 DeltagerListview deltagerListview) {
        // Sørg for at både konference og deltagerens navn er validt
        if (selectedKonference != null) {
            // Get participant details
            String navn = deltagerNavn;  // Brug direkte den String, der er sendt som argument

            if (!navn.isEmpty()) {
                // Opret deltager via Controller
                Deltager newDeltager = Controller.opretDeltager(navn, "", null, null);  // Tomme felter til at starte med

                // Get the other details from the form
                boolean isForedragsholder = foredragsholderCheckBox.isSelected();
                boolean hasLedsager = ledsagerCheckBox.isSelected();
                LocalDate ankomstDato = ankomstDatePicker.getValue();
                LocalDate afrejseDato = afrejseDatePicker.getValue();

                // Create Tilmelding for the participant via Controller
                Controller.opretTilmelding(null, isForedragsholder, ankomstDato, afrejseDato,
                        newDeltager, selectedKonference, hasLedsager ? Controller.opretLedsager("Ledsager") : null);

                // Opdater deltager listen i GUI'en
                deltagerListview.updateDeltagerList();

                // Clear text fields and selections
                // Vi har allerede deltagerNavn som en String, så vi behøver ikke .clear() her
                foredragsholderCheckBox.setSelected(false);
                ledsagerCheckBox.setSelected(false);
                ankomstDatePicker.setValue(null);
                afrejseDatePicker.setValue(null);
            } else {
                // Notify user if fields are empty
                System.out.println("Indtast venligst deltagerens navn.");
            }
        } else {
            System.out.println("Vælg venligst en konference.");
        }
    }
}
