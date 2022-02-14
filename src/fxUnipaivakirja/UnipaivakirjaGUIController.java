package fxUnipaivakirja;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;

/**
 * @author Omistaja
 * @version 10.2.2022
 *
 */
public class UnipaivakirjaGUIController {


    @FXML void handleAvaa() {
        ModalController.showModal(UnipaivakirjaGUIController.class.getResource(
                "aloitusikkuna.fxml"), "Aloitus", null, "");
    }

    @FXML void handleHaku() {
        hae();
    }

    @FXML void handleLopeta() {
        tallenna();
        Platform.exit();
    }

    @FXML void handleOhje() {
        ohje();
    }

    @FXML void handlePoistaKayttaja() {
        ModalController.showModal(UnipaivakirjaGUIController.class.getResource(
                "Poistakayttaja.fxml"), "Poista käyttäjä", null, "");
    }

    @FXML void handlePoistaMerkinta() {
        ModalController.showModal(UnipaivakirjaGUIController.class.getResource(
                "poistaikkuna.fxml"), "Poista merkintä", null, "");
    }

    @FXML void handlePoistu() {
        tallenna();
        Platform.exit();
    }

    @FXML void handleTallenna() {
        tallenna();
    }

    @FXML void handleTietoja() {
        ModalController.showModal(UnipaivakirjaGUIController.class.getResource(
                "Tietoja.fxml"), "Tietoja", null, "");
    }

    @FXML void handleTulosta() {
        ModalController.showModal(UnipaivakirjaGUIController.class.getResource(
                "Tulostus.fxml"), "Tulosta", null, "");
    }

    @FXML void handleUusiKayttaja() {
        ModalController.showModal(UnipaivakirjaGUIController.class.getResource(
                "Uusikayttaja.fxml"), "Lisää uusi käyttäjä", null, "");
    }

    @FXML void handleUusiMerkinta() {
        Dialogs.showMessageDialog("Ei osata vielä lisätä uutta merkintää.");
    }
    
    //------------------------------------------------------------
    
    
    /**
     * Tallentaa muokatut tiedot.
     */
    private void tallenna() {
        Dialogs.showMessageDialog("Tallennetaan, mutta ei toimi vielä!");
    }
    
    
    /**
     * Näytetään harjoitustyöohjelman suunnitelma web-selaimessa.
     */
    private void ohje() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2022k/ht/ansupaav");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }
    
    private void hae() {
        //jee
    }

}
