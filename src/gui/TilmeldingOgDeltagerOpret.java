package gui;

import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import models.Deltager;
import models.Konference;
import models.Ledsager;
import models.Tilmelding;

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
                // Opret deltager
                Deltager newDeltager = new Deltager(navn, "", "", null);  // Tomme felter til at starte med

                // Get the other details from the form
                boolean isForedragsholder = foredragsholderCheckBox.isSelected();
                boolean hasLedsager = ledsagerCheckBox.isSelected();
                LocalDate ankomstDato = ankomstDatePicker.getValue();
                LocalDate afrejseDato = afrejseDatePicker.getValue();

                // Opret ledsager, hvis nødvendigt
                Ledsager ledsager = hasLedsager ? new Ledsager("Ledsager Navn") : null;

                // Create Tilmelding for the participant
                Tilmelding tilmelding = selectedKonference.createTilmelding(
                        null, isForedragsholder, ankomstDato, afrejseDato, newDeltager, selectedKonference, ledsager);

                // **Opdater deltager listen**: Tilføj deltageren til konference
                selectedKonference.addDeltager(newDeltager);  // Tilføj deltager til den valgte konference

                // Opdater deltagerlisten i ListView
                deltagerListview.updateDeltagerList();  // Opdater ListView med den nyeste deltager

                // Clear input fields efter oprettelse
                deltagerNavn = "";  // Clear the name input field
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
